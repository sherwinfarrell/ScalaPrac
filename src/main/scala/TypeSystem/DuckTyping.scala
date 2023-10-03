package TypeSystem

object DuckTyping extends App {

  // Structural Types and Compile Time Duck Typing

  type JavaCloseable  = java.io.Closeable
  class HipsterCloseable{
    def close(): Unit = println("Closing..")
    def closeSilently(): Unit = println("Closes Silently")

  }

//  def closeQuietly(closeable: JavaCloseable | HipsterCloseable) -- SOlutino to this down

  type UnifiedCloseable = {
    def close(): Unit
  } // Structural Type -- Can have values and variables inside

  def closeQuietly(closeable: UnifiedCloseable): Unit = closeable.close()

  closeQuietly(new JavaCloseable{
    override def close(): Unit = ???
  })

  closeQuietly(new HipsterCloseable)


  // Even these two things are completely unrelated  - they just match the type structure that this method requires

  // Type Refinement


  //AdvancedCloseable = JavaCloseable + closeSilently Method -- Type Enrichment or Type Refinement
  type AdvancedCloseable = JavaCloseable{
      def closeSilently(): Unit
  }

  class AdvancedJavaCloseable extends JavaCloseable{
    override def close(): Unit =  println("Java Closes")
    def closeSilently(): Unit = println("Java Closes Silently")
  }


  def closeShh(advancedCloseable: AdvancedCloseable): Unit = advancedCloseable.closeSilently()

  closeShh(new AdvancedJavaCloseable)  // Compiler does type checking to make sure that his new object contains the additional method
  // closeShh(new HipsterCloseable) -- This won't work - As hipstercloseable does not extend JavaCloseable -- even though it has all the methods




  // Using structural types as standalone types
  def altClose(closeable: {def close(): Unit}): Unit = closeable.close()


  // Type checking -- Duck Typing


  type SoundMaker = {
    def makeSound(): Unit
  }

  class Dog {
    def makeSound(): Unit = println("Bark!")
  }

  class Cat {
    def makeSound(): Unit = println("Meow!")
  }


  val dog: SoundMaker = new Dog
  val cat: SoundMaker = new Cat
  // These two instantiations work because the class conforms to the structure of the structural type
  // This is also called Static Duck Typing
  //Caveat: This is based on Relection

}
