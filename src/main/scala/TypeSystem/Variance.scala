package TypeSystem

object Variance extends App {

  trait Animal
  class Dog extends Animal
  class Cat extends Animal
  class Crocodile extends Animal

  /**What is Variance?
   * Problem of "Inheritance" - Type substituion of Generics
   */

  class Cage[T]
  // If Cat extends Animal - Should I Cage[Cat] extends Cage[Animal]
  //Yes - Covariance

  class CCage[+T]
  val ccage: CCage[Animal] = new CCage[Cat]


  //Invariacne
//  class ICage[T]
//  val ICage: ICage[Animal] = new ICage[Cat]


  // Contravariance
  class XCage[-T]
  val xcage: XCage[Cat] = new XCage[Animal] // Valid Substitution -- Replace specific cage of cats with general cage of animals
  // If cage can contain a cat then it can contain an Animal

  class InvariantCage[T](animal: T)

  //Covariance and Covariant Positions
  class CovarianceCage[+T](animal: T)  //Covarant Position -- IN this position the compiler accepts a filed in the covariant type

}
