package ObjectOrientedProgramming

object AbstractDataTypes extends App {

  /**
   * Abstract data types are used in situations where you want to leave some fields or methods un-implemented
   */

  // Abstract classes cannot be instantiated
   abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  //  val animal = new Animal this will throw a compile error

  class Dog extends Animal{
    override val creatureType: String = "Canine"

    override def eat: Unit = println("Crunch Crunch") //Override keyword not required for methods
  }


  /**
   * Traits
   */

  trait Carnivore{
    def eat(animal: Animal): Unit
    def preferredMeal
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    val creatureType = "Croc"

    override def preferredMeal: Unit = println("Meat")
    def eat: Unit = println("Nom NOm NOm")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  /**
   * Traits vs Abstract Classes
   * Abstract classes can both abstract and non abstract variables or methods but so can traits
   * 1 - Traits do not have constructor parameters
   * 2 - You can only extend one class but you can extend multiple traits
   * 3 - Traits are behaviour but abstract class is a type of thing
   */

  /**
   * Nothing is a subtype of any type in scala
   * Null is a subtype of AnyRef
   */


  abstract  class MyList{
    /*
    head = First element in the list
    tail = return the last element in the list
    isEmpty = is this list empty
    add(int) = add element to the list
    toString = a string representation of a list
     */

    def head:Int
    def tail:MyList
    def isEmpty: Boolean
    def add(element:Int):MyList
    def printElements:String

    override def toString:String = "[" + printElements + "]"

  }

   // Objects can extend classes
   object Empty extends MyList{
     def head:Int = throw new NoSuchElementException
     def tail:MyList = throw new NoSuchElementException
     def isEmpty: Boolean = true
     def add(element:Int):MyList = new Cons(element, Empty)

     override def printElements: String = ""
   }

  class Cons(h:Int,  t: MyList) extends MyList{
    def head:Int = h
    def tail:MyList = t
    def isEmpty: Boolean = false
    def add(element:Int):MyList = new Cons(element, this)

    override def printElements: String = if(t.isEmpty) "" + h else h + " " + t.printElements
  }

//  val list = new Cons(1, Empty)
    val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
    println(list.head)
    println(list.tail.head)
    println(list.add(3).head)

    //Polymorphic call
    println(list.toString)
  }
