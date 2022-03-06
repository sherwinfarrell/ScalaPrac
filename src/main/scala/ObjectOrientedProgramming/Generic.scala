package ObjectOrientedProgramming

object Generic extends App{

  /*
  Classes and Traits can be type parametised
   */
  class MyList[A] {

  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  /*
  Objects CANNOT BE TYPE PARAMETIZED
   */
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfInts = MyList.empty[Int]

  //Variance Problem
  class Animal
  class Dog extends Animal
  class Cat extends Animal

  //Can list of cat extend List of Animals? Yes - Covariance
  class CovarianceList[+A]
  val animalList:CovarianceList[Animal] = new CovarianceList[Cat]
  //Can animalList add dog instance? -- Hard Question -- Method add will have to have the following signature def add[B>:A](element:B): MyList[B]

  //NO - Invariant List
  class InvariantList[A]
//  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] -- This won't work -- Compilier error

  //3 - Contravariant List
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal] //This option doesn't make sense for a list

  class ContravariantTrainer[-A]
  val contravariantTrainer: ContravariantTrainer[Cat] = new ContravariantTrainer[Animal]
  // Here for this case it makes sense as a trainer of animal can also train a cat


  //4 - Bounded Types

  //Upper Bounded type
  class Cage[A <: Animal](animal: A) //Cage only accepted types that are subtypes of animal
  val cage = new Cage(new Dog)

  //Lower Bounded Type
  class Cage2[A >: Cat](cat: A)
  val cage2 = new Cage2(new Cat)
  val cage3 = new Cage2(new Animal)
//  val cage4 = new Cage2(new Dog) // This will throw a compile error




}
