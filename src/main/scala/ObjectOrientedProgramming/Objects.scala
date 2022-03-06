package ObjectOrientedProgramming

//Objects are use for class level functionality
// SCALA DOES NOT HAVE CLASS LEVE (STATIC) FUNCTIONALITY
object Objects extends App {

   object Person{
     val N_EYES = 2
     def canFly: Boolean = false
     def from(mother: Person, father: Person):Person = new Person("Bobbie")

     def apply(mother: Person, father: Person):Person = new Person("Bobbie")

   }

  class Person(val name: String){
    //Instance level functionality
  }

  //Class Person and Object Person are companions as they reside in the same scope and one provides instance level functionality
  // and the other class level functionality

  println(Person.N_EYES)
  println("Can a person fly: " + Person.canFly)


  //Scala object is singleton instance
  //Object is its own type and is only instance

  val marry = Person
  val john = Person

  val andy = new Person("Andy")
  val betty = new Person("Betty")

    println("Comparing to vals instantiated with Object Person " + (marry == john)) // true
  println("Comparing to vals instantiated with Class Person " + (andy == betty)) // false

  val bobbie = Person.from(andy, betty) // Can create a new method that creates a person instance
  val anotherBobbie = Person(andy, betty) // Or use and apply method and then don't need to call the object just paranthesis
}
