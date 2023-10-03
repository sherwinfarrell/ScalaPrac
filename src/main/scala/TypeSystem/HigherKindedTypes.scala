package TypeSystem

object HigherKindedTypes extends App {

  trait HigherKindedType[F[_]]

  trait MyList[T]{
    def flatMap[B](f: T => B): MyList[B]
  }

  trait MyOption[T]{
    def flatMap[B](f: T => B): MyOption[B]
  }


  //Combine and Multiple -- List(1,2) * List('a', 'b') => List(1a, ab, 2a, 2b)

  def multiply[A, B](listA: List[A], listB: List[B]): List[(A, B)] = {
    for{
      a <- listA
      b <- listB
    } yield (a,b)
  }


  def multiply[A, B](listA: Option[A], listB: Option[B]): Option[(A, B)] = {
    for {
      a <- listA
      b <- listB
    } yield (a, b)
  }


  // Use higher kinded types to combine these different methods for different types in to one

  trait Monad[F[_], A]{
    def flatMap[B](f: A=> F[B]): F[B]
    def map[B](f: A => B): F[B]
  }

//  class MonadList extends Monad[List, Int] {
//    override def flatMap[B](f: Int => List[B]): List[B] = ???
//  }



  def multiply[F[_], A, B](ma: Monad[F, A], mb: Monad[F, B]): F[(A, B)] =
    for{
      a <- ma
      b <- mb
    } yield (a, b)

  println(multiply(List(1,2,3), List(3,4,5)))

}
