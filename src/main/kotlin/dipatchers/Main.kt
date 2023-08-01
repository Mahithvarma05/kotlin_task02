package dipatchers

import kotlinx.coroutines.*

import kotlin.system.measureTimeMillis

fun main()= runBlocking {  //Thread:main
     println("main  program starts: ${Thread.currentThread().name}")
     val time= measureTimeMillis {
         val m1= async { msg1() }
         val m2 = async { msg2() }
         val m3=async(start = CoroutineStart.LAZY){
             println("not in use")
             msg3()
         }
         println(" ${m1.await()+m2.await()}")

         launch {
             println("launch 1 :${Thread.currentThread().name}")
             delay(1000)
             println("launch 1 :${Thread.currentThread().name}") //both run on main
         }
         launch(Dispatchers.Default){
             println("launch 2 :${Thread.currentThread().name}")
             delay(1000)
             println("launch 2 after delay:${Thread.currentThread().name}")
         }
         launch(Dispatchers.Unconfined){
             println("launch 3 :${Thread.currentThread().name}") //main
             delay(1000)
             println("launch 3 after delay :${Thread.currentThread().name}")
         }
         launch(coroutineContext) {
             println("launch 4 :${Thread.currentThread().name}")
             delay(1000)
             println("launch 4 after delay :${Thread.currentThread().name}") //both run on main inherits parent coroutine
         }

     }
    println("time taken $time")
 }
suspend fun msg1():String{
    delay(1000)
    return "hello "
}
suspend fun msg2():String{
    delay(1000)
    return "Mahith"
}
suspend fun msg3():String{
    delay(1000)
    println("this shouldn't print")
    return "didn't use"
}