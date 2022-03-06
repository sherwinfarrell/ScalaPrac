package ObjectOrientedProgramming

object AnnonymousClasses extends App {
    abstract class Animal{
      def eat:Unit
    }

    //Anonymous class
   val animal = new Animal {
     override def eat: Unit = println("NOm NOm")
   }
   println(animal.getClass)


  class Person(name:String){
    def sayHi: Unit = println(s"Hi my name is ${name}")
  }
  val jim = new Person("Jim"){
    override def sayHi: Unit = println(s"Hi my name is Jim")
  }
}
