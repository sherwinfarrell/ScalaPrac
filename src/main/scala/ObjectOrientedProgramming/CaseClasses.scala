package ObjectOrientedProgramming

object CaseClasses
 extends  App {

  /**
   * For normal classes we have to implement all sorts of things - Companion Object, standard methods like
   * Serializing, Pretty Printed, Hash Code, toString
   * Case class do all of that in built
   */

  // Class parameters are promoted to fields
  case class Person(name:String, age: Int)
  val jim = Person("Jim", 23)
  println(jim.name)

  // Sensible to string - Prints the class with the parameters nicely to the stdout unlike a normal class
  println(jim.toString)

  class AnotherTestPerson
  val testPerson = new AnotherTestPerson
  println(testPerson.toString)

  //Equals and HashCode implemented out of the box
  val jim2 = new Person("Jim", 23)
  println(jim == jim2)

  //CCs have a handy copy method
  val jim3 = jim.copy()
  val jim4 = jim.copy(age = 45)
  val jim5 = jim.copy(name = "notJIm", age = 34)

  //Companion objects are automatically created -- no new keyword used

  //Case classes are serializable -- AKka play
  //case classes have extractor patters -- used heavily in pattern object


  /**
   * Case Object
   */
  case object  UnitedKingdom{
    def name: String =   "UK"
  }

}
