package ObjectOrientedProgramming

object OOSyntacticSugar extends App {
  class Person(val name: String, favoriteMovie: String){
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person) = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name what the heck"
    def isAlive: Boolean = true
    def apply(): String = s"My name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  val andy = new Person("Andy", "Fight Club")
  println(mary likes "inception")
  println(mary + andy ) //Infix Notation
  println(!mary)
//  println(mary unary_!)
//  print(mary isAlive) //Postfix notation
  println(mary.apply())

  print(mary()) // calls the apply method
}
