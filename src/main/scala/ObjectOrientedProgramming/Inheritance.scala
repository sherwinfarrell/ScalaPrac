package ObjectOrientedProgramming

object Inheritance extends  App {

  //Scala allows only Single Class Inheritance -- You can add a another class to the extends method
  class Animal{
    val creatureType = "Wild Creature"
    def eat = println("Nom Nom Nom")
  }

  class Cat extends Animal{
    def crunch = {
      eat
      println("Crunch")
    }
  }

  val cat = new Cat
//  cat.eat if eat method in Animal is Public
  cat.crunch


//  class Person(name: String, age: Int)
//
//  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) // JVM rule is that it needs to call the constructor of the parent class

  /** This is also valid if there is constructor in the parent class
   * that takes in only one of the values to create the class
   */
  class Person(name: String, age: Int){
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  /**
   * Overriding
   */
//    class Dog extends  Animal{
//      override val creatureType = "Domestic"
//      override def eat: Unit = println("crunch, crunch") // No privacy modifier
//    }
//
//    val dog = new Dog
//    dog.eat
//    println(dog.creatureType)



      //Directly overriding the creature type in the class parameter
      class Dog(override val creatureType: String) extends  Animal{
        override def eat: Unit ={
            super.eat // Can call the method implementation in the parent class using super
            println("crunch, crunch") // No privacy modifier
        }
      }

      val dog = new Dog("Domestic")
      dog.eat
      println(dog.creatureType)


  /**
   * Polymorphism
   */
   val unknownAnimal: Animal = new Dog("Domestic")
    unknownAnimal.eat


  /** Preventing Overriding
   *  Use Final before any method in the super class to prevent it from being overridin in the child class
   *  Use Final on the Entire Class - this prevents any class to extend that class that is defined with a final keyword
   *  Seal class - Extensions of that class can only take place in the the file in which the class was defined -Prevent extensions in other files
   */
}
