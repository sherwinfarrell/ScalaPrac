package Implicits

object OrganisingImplicits extends App {

  /**
   * In scala we use a trait called ordering that used implicit value
   * Sorted needs an implicit Ordering[A] value
   * Scala looks for this implicit Ordering value in Scala.Predef
   * AND Scala.Predef is automatically imported
   */

    println(List(1,4,5,2,3).sorted)


    /**
     *  If an implicit ordering is defined like the one below:
     *  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
     * It will take precedence over imported implicit
     */


    /*
     Implicit Parameters:
      - val/var
      - objects
      - Access Methods - def with no paranthesis
     */


    // Exercise:
    case class Person(name: String, age: Int)
    val persons = List(Person("Stever", 30), Person("Aime", 22), Person("John", 66))
//    implicit val personOrders: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo( b.name) < 0)

//    println(persons.sorted)


    /**
     * Implicit Scope
     *  - Normal Scope - Highest Priority  == Local Scope ( Written in current file)
     *  - Imported Scope
     *  - Companion Objects of All Types involved in method signature (For Instance) -
     *         def sorted[B>:A](implicit ord: Ordering[B]): List[B] then the types are -
     *         - List
     *         - Ordering
     *         - All other types involved - A, B or any supertype
     *
     *  Hence the implicit can be defined in Person companion object as well:
     *  object Persion {
     *      implicit val personOrders: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo( b.name) < 0)
     *  }
     */


    /**
     * Best Practises -
     * - If there is a single possible value
     * - And can edit the code for that type
     * Define the implicit in the Companion Object
     *
     * - If there are multiple possible values
     * - And can edit the code for that type
     * Then the define the implicit eslewhere or in other objects and import the implicit for the type you need
     */


    object AlphabeticOrdering{
        implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a,b) => a.name.compareTo(b.name) <0)
    }

    object AgeOrdering {
        implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a,b) => a.age < b.age)
    }

    import AgeOrdering._
    println(persons.sorted)


    /*
        Exercise - Add three orderings
        - totalPrice - Most Used ( 50%)
        - unitCount - Some of team (25%)
        - unitPrice - 25%
     */

    case class Purchase(nUnits: Int, unitPrice: Double)

    object Purchase{
        implicit  val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a,b) => a.nUnits * a.unitPrice < b.nUnits * b.unitPrice)
    }

    object unitOrdering{
        implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
    }

    object unitPriceOrdering{
        implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.unitPrice < _.unitPrice)
    }





}
