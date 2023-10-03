package ConcurrrentProgramming

object ThreadCommunication extends App {

  /*
   The Producer-consumer problem

   producer -> [x] -> consumer // Producer and consumer threads, producer sets a value in a container and consumer gets the value
   // Consumer has to wait for the producer to finish the job of setting the value in the contanier --or else in the there is no action to process
   // Run actions in a certain order even the threads are not ordered.
   */


  class SimpleContainer{
    private var value: Int = 0

    def isEmpty: Boolean = value == 0

    def get: Int = {  // Consuming method
      val result = value
      value = 0
      result
    }

    def set(newValue: Int) = value = newValue // Producer method
  }

  def naiveProdCons(): Unit = {
    val container = new SimpleContainer
    val consumer = new Thread(() => {
      println("Consumer is Waiting...") // Busy waiting: Busy waiting is wasting resources
      while(container.isEmpty){
        println("Consumer still waiting...")
      }

      println("Consumer: I have consumed: " + container.get)
    })

    val producer = new Thread(() => {
      println("[Producer] computing...")
      Thread.sleep(500)
      val value = 42
      println("[Producer] I have produced: " + value)
      container.set(value)
    })

    consumer.start()
    producer.start()
  }

//  naiveProdCons()




  // Smart Producer - Consumer

  def smartProdCons(): Unit = {
    val container = new SimpleContainer
    val consumer = new Thread(() => {
        println("[Consumer] Waiting...")
        container.synchronized{
          container.wait()
          println("[Consumer] I have consumer: " + container.get)
        }
    })

    val producer = new Thread(() => {
      println("[Producer] Hard at work...")
      Thread.sleep(2000)
      val value = 42
      container.synchronized{
        println("[Producer] I'm producing " + value)
        container.set(value)
        container.notify()
      }
    })

    consumer.start()
    producer.start()

  }

  smartProdCons()



}
