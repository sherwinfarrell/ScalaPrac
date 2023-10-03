package Implicits

import java.util.Date
import javax.sql.rowset.serial.SerialException

object TypeClasses extends App {
  /**
   * What is a typeclass?
   * Typeclass is a trait, that takes a type and descripes what operations can be applied to that type.
   * For instance: Ordering trait is a type class, and the implicit orderings that we defined are typeclass instances.
   */

  trait HTMLWritable {
    def toHtml: String
  }

  case class User(name: String, age: Int, email: String) extends HTMLWritable {
    override def toHtml: String =
      s"""  <div>
         |    ${name} (${age} years old) <a href=${email}/>
         |  </div>
         |""".stripMargin

  }

  User("John", 32, "john@abc.com").toHtml

  /**
   * The above code has a big disadvantage, it only works for the types we write.
   * For java standard types or other types it won't work.
   * This is only one implementation
   *
   */


  // Option 2 - Use Pattern Matching
  object HtmlSerializerPM{
    def serializeToHtml(value: Any) = value match {
      case User(n, a, e) =>
      case value: Date =>
      case _ =>
    }
  }


  /**
   * Disadvantages for Pattern Matching -
   * 1. Lost Type Safety
   * 2. Need to Modify code, if something chaneges
   * 3. This is still one implementation for each type - Implementation can differ for same types
   */


  /**
   * Solution
   */

  trait HtmlSerializer[T]{
    def serialize(value: T): String
  }

  object UserSerializer extends HtmlSerializer[User] {
    def serialize(value: User): String = s"<div>${value.name} - ${value.age} - ${value.email} </div>"
  }


  val john = User("John", 32, "john@email.com")

  println(UserSerializer.serialize(john))


  /**
   * Advantages:
   *  - We can define serializers for other types
   *  - We can define multiple serializers for the same type
   */

  import java.util.Date
  object DateSerializer extends HtmlSerializer[Date]{
    override def serialize(value: Date): String = s"<div>${value.toString}</div>"
  }


  object ParitalUserSerializer extends HtmlSerializer[User]{
    override def serialize(value: User): String = s"<div>${value.name}</div>"
  }

  /**
   * This HtmlSerializer is a type class
   * Explanation: A type class specifies a set of operations, in this serialize, that can be applied to a given type.
   * Anyone that extends HtmlSerializer has to provide that functionality.
   * Type Class Instances: All the implementers of a type class are called type class instances.
   *
   * It describes a collection of properties or methods that a type must have in order to belong to that specific type class.
   * For instance: If it is known a type belongs to the Ordering type class, then it is known hat instances of that type have the ability to compare
   * values and tell that one is less than the other.
   */


  /*
            Type Class Template
   */


  trait MyTypeClassTemplate[T]{
    def action(value: T): String
  }

  object MyTypeClassTemplate{
    def apply[T](implicit serializer: MyTypeClassTemplate[T]) = serializer
  }



  /*
          Exercise
          - Implement an equal type class that has a method equal that compares to values.
   */

  trait Equal[T]{
    def equal(a: T, b:T):Boolean
  }


  implicit object IntEquality extends Equal[Int]{
    override def equal(a: Int, b:Int): Boolean = if(a==b) true else false
  }

  implicit object UserEquality extends Equal[User]{
    override def equal(a: User, b: User): Boolean = if((a.name == b.name) && (a.age == b.age)) true else false
  }


  println(IntEquality.equal(1, 2))
  println(UserEquality.equal(User("John", 32, "john@email.com"), User("John", 32, "john@email.com")))


  /**
   * Type Classes with Implicits
   */


  User("John", 32, "john@email.com")

  object HtmlSerializer {
    def serialize[T](value: T)(implicit serializer: HtmlSerializer[T]) =
      serializer.serialize(value)

    def apply[T](implicit serializer: HtmlSerializer[T]) = serializer
  }


  implicit object IntSerializer extends HtmlSerializer[Int] {
    override def serialize(value: Int): String = s"<div>${value}</div>"
  }

  println(HtmlSerializer.serialize(10)(IntSerializer))

  println(HtmlSerializer.serialize(10))


  //This has access to the entire type class interface - with all the methods inside of it not just serialize
  println(HtmlSerializer[Int].serialize(10))

  //


  /**
   * Implement the type class pattern for the Equality TC
   */

  object Equal{
    def apply[T](implicit instance: Equal[T]) = instance
  }



    println(Equal[Int].equal(10, 20))

    println(Equal[User].equal(User("John", 32, "john@email.com"), User("John", 32, "john@email.com")))



  //This is called - Ad-Hoc Polymorphism



  // Part 3
  implicit class HTMLEnrichment[T](value: T){
    def toHtml(implicit serializer: HtmlSerializer[T]): String = serializer.serialize(value)
  }

//  println(john.toHtml(UserSerializer)) // new HtmlEnrichement[User](john).toHtml(UserSerializer)
  println(john.toHtml) // With implicit UserSerializer
  println(2.toHtml)

}
