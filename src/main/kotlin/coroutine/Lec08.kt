package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture


fun main(): Unit = runBlocking {
  val result: Int? = withTimeoutOrNull(1_000L) {
    delay(1_500L)
    10 + 20
  }
  printWithThread(result)
}


fun lec08Example4(): Unit = runBlocking {
  printWithThread("START")
  printWithThread(calculateResult())
  printWithThread("END")
}

suspend fun calculateResult(): Int = withContext(Dispatchers.Default) {
  val num1 = async {
    delay(1_000L)
    10
  }

  val num2 = async {
    delay(1_000L)
    20
  }

  num1.await() + num2.await()
}

fun lec08Example3(): Unit = runBlocking {
  val result1 = call1()
  val result2 = call2(result1)

  printWithThread(result2)
}

suspend fun call1(): Int {
  return CoroutineScope(Dispatchers.Default).async {
    Thread.sleep(1_000L)
    100
  }.await()
}

suspend fun call2(num: Int): Int {
  return CompletableFuture.supplyAsync {
    Thread.sleep(1_000L)
    100
  }.await()
}


fun lec08Example2(): Unit = runBlocking {
  launch {
    a()
    b()
  }

  launch {
    c()
  }
}

suspend fun a() {
  printWithThread("A")
}

suspend fun b() {
  printWithThread("B")
}

suspend fun c() {
  printWithThread("C")
}


fun lec08Example1(): Unit = runBlocking {
  launch {
    delay(100L)
  }
}