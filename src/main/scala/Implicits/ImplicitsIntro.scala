package Implicits

object ImplicitsIntro extends App {

  val pair = "Daniel" -> "555" // Over here arrow assoc is an implicit class that turns "Daniel" into a type of arrow assoc
  val intPair = 12 -> 5

  case class Person(name: String){
    def greet = s"Hi my name is ${name}"
  }

  //Only one implicit can match --

  implicit def fromStringToPerson(str: String): Person = Person(str)
  println("Peter".greet) // Implicitly added greet to string using the implicit method

  /**
   * If we add the following code as well:
   *   implicit def fromStringToA(str: String): A = new A
   *   Then "Peter".greet won't compile, as there are two implicit methods that are convert strings to other types
   */


  /*
  Implicit Parameters
   */
  def increment(x: Int)(implicit amount: Int) = x + amount
  implicit val defaultAmount = 10

  increment(2) //Amount Implicitly passed


}
