package ConcurrrentProgramming

import java.util.concurrent.Executors

object JavaParallelProgramming extends App {

    // JVM Threads -- Communication, Creation and Manipulation

  // Thread is an instane of a class -- it takes instance of runnable

  /**
   * Runnable Interface:
   * interface Runnable{
   *  public void run()
   * }
   */
  val runnable = new Runnable {
    override def run(): Unit = println("Running in parallel...")
  }

  val thread = new Thread(runnable)


  thread.start() // Starting a thread will create a jvm thread -- Runs on top of Jvm Thread

  // Start gives the signal to the JVM to start a JVM Thread that runs ontop of os thread

//  runnable.run() //This runs it in the main thread and not on a separate thread

  thread.join() // Blocks until thread finishes running

  val threadHello = new Thread(() => (1 to 5).foreach(_ => println("Hello")))
  val goodByeThread = new Thread(() => (1 to 5).foreach(_ => println("Goodbye")))

//  threadHello.start()
//  goodByeThread.start() // Different runs will produce different results when two threads are running simultaenously


  // Executors
  // Threads are very expensive - Need to resuse them -- So we create thread pools

  val pool = Executors.newFixedThreadPool(10) // Java Library

//  pool.execute(() => {println("Something in the thread pool!")}) // Gets executed by one of the 10 threads in the pool
//  pool.execute(() => {
//    Thread.sleep(1000)
//    println("Done after 1 second!")
//  })
//
//  pool.execute(() => {
//    Thread.sleep(1000)
//    println("Almost done")
//    Thread.sleep(1000)
//    println("Done after 2 seconds")
//  })



  // Shutdownd all the threads
//  pool.shutdown()
//  pool.execute(() => {println("Should not appear")}) // Throws exception in the main thread -- Not accepted

//  pool.shutdownNow() // It will shut down sleeping threads -- Sleep interrupted exception - Interrupts the running threads

//  println(pool.isShutdown) // Will return true -- even if the actions submitted to the pool are still running - before the actions gets executed -- Means it does not accept new actions

  //Executors has many thread pool options


  def runInParallel = {
    var x = 0
    val thread1 = new Thread(() => x = 1)
    val thread2 = new Thread(() => x = 2)

    thread1.start()
    thread2.start()
    println(x)
  }

//  for(_ <- 1 to 10000) runInParallel

  // This is called a Race conditions

  class BankAccount(var amount: Int){
    override def toString: String = "" + amount
  }

  def buy(account: BankAccount, thing: String, price: Int) = {
    account.amount -= price
  }

//  for(_ <- 1 to 10000){
//    val account = new BankAccount(50000)
//    val thread1 = new Thread(() => {
//      buy(account, "shoes", 3000)
//    })
//    val thread2 = new Thread(() => {
//      buy(account, "iphone12", 4000)
//    })
//
//    thread1.start()
//    thread2.start()
//    Thread.sleep(10)
//
//    if(account.amount != 43000) println("Ahha: " + account.amount)
//
//    println()
//  }


  // Two paths to avoid race conditions

  //1. Synchronized

  def buySafe(account: BankAccount, thing: String, price: Int) = {
    account.synchronized {
      // No 2 threads can evaluate this at the same time
      account.amount -= price
      println("Ive bought: " + thing)
      println("My account is now: " + account.amount)
    }
  }


  for (_ <- 1 to 10000) {
    val account = new BankAccount(50000)
    val thread1 = new Thread(() => {
      buySafe(account, "shoes", 3000)
    })
    val thread2 = new Thread(() => {
      buySafe(account, "iphone12", 4000)
    })

    thread1.start()
    thread2.start()
    Thread.sleep(10)

    if (account.amount != 43000) println("Ahha: " + account.amount)

    println()
  }


  //Option 2: Use @Volatile less powerful than synchronized









}
