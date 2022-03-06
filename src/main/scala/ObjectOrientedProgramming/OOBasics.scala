 object OOBasics extends App {
    val person = new Person("Andy", 35)
    println(person.age)
    println(person.a)
   person.greet("Robert")
   person.greet
 }

// class Person(name: String, age:Int) class parameters are not fields

// class Person(val name: String, val age: Int)


 class Person(val name: String, val age: Int = 20){ // Default parameter
    val a = 3 // These are fields that can be accessed from the class object unless you declare them as private or protected
   println(a)

   //method since it is defined inside a class
   def greet(name: String): Unit = println(s"${this.name} says Hi ${name}") // Need to use the this keyword here to differentiate

   //method overloading
   def greet:Unit = println(s"Hi: I am ${name}") // THis is implied here

   //Multiple constructor or constructor overloading
   def this(name: String) = this(name, 0) // Auxiliary constructors have to call another constructor
   def this() = this("John Doe") // Auxiliary constructor calls the secondary constructor -- Instead we can use default parameters
 }

 class Writer(firstName: String, lastName: String, val year: Int){
   def fullName: String = firstName + " " + lastName
 }

 class Novel(name:String, year: Int, author: Writer){
   def authorAge = year - author.year
   def isWrittenBy(author: Writer) = author == this.author
   def copy(newYear: Int) = new Novel(name, newYear, author)
 }

 class Counter(val count: Int) {
   def inc = {
     println("Incrementing")
     new Counter(count +1) } //Immutability
   def dec = {
     println("Decrementing")
     new Counter(count -1 )}
   def inc(n: Int): Counter = {
     if(n<=0) this else inc.inc(n - 1) // inc => Counter(count + ) inc.inc => Counter(count + 1).inc(n -1) == Counter(count + 1) and Counter(count + 1)
   }
   def dec(n: Int): Counter = {
     if(n<=0) this else dec.dec(n - 1)
   }
 }