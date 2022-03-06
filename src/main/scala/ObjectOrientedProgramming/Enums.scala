package ObjectOrientedProgramming

object Enums extends App {

  object Fingers extends Enumeration {
    type Finger = Value

    val Thumb, Index, Middle, Ring, Little = Value
  }
}
