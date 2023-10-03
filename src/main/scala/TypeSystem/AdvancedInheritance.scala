package TypeSystem

object AdvancedInheritance extends App {

  // Convenience


  trait Writer[T]{
    def write(value: T): Unit
  }

  trait Closeable{
    def close(status:Int): Unit
  }

  trait GenericStream[T]{
    def foreach(f: T => Unit): Unit
  }

  def processStream[T](stream: GenericStream[T] with Writer[T] with Closeable) : Unit = {   // Stream is it's own type
    stream.foreach(println)
    stream.close(0)
  }


  // Diamon Problem

  trait Animal { def name: String}
  trait Lion extends Animal {
    override def name: String = "Lion"
  }
  trait Tiger extends Animal{
    override def name: String = "Tiger"
  }


  class Mutant extends Lion with Tiger {
    //override def name: String = "Alien" // Compiler Doesn't care because it overrides name, however it compiles even if we remove this override
  }

  val m = new Mutant()
  println(m.name)

  /**
   *   Why does the compiler print Tiger?
   *   Because Mutant extends Animal with {override def name: String = "Lion"}
   *   with extends Animal with {override def name:String = "Tiger"}
   *
   *   Last override always gets picked whenever we have Diamond inheritance!
   */

  /*
              The Super Problem  ( Type Linerization )
   */

  trait Cold {
    def print = println("Cold")
  }

  trait Green extends Cold {
    override def print: Unit = {
      println("Green")
      super.print
    }
  }

  trait Blue extends Cold {
    override def print: Unit = {
      println("Blue")
      super.print
    }
  }


  class Red {
    def print = println("Red")
  }


  class White extends Red with Green with Blue {
    override def print(): Unit = {
      println("White")
      super.print
    }
  }



  val color = new White // What will this print to the console? -- Print White and Red (Wrong)

  //It prints - White, Blue, Green, Cold

  /**
   * Cold = AnyRef with <Cold>
   *   Green
   *        = Cold With <Green>
   *        = AnyRef With <Cold> with <Green>
   *   Blue
   *        = Cold With <Blue>
 *          = AnyRef With <Cold> with <Blue>
   *
   *   Red
   *        = AnyRef with <Red>
   *
   *
   *    White  = Red with Green with Blue with <White>
   *            = AnyRef with <Red>
   *              
   */






}
