package coroutine

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
  val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    printWithThread("예외")
    throw throwable
  }

  val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
    throw IllegalArgumentException()
  }

  delay(1_000L)
}















fun lec05Example5(): Unit = runBlocking {
  val job = launch {
    try {
      throw IllegalArgumentException()
    } catch (e: IllegalArgumentException) {
      printWithThread("정상 종료")
    }
  }
}


fun lec05Example4(): Unit = runBlocking {
  val job = async(SupervisorJob()) {
    throw IllegalArgumentException()
  }

  delay(1_000L)
  job.await()
}


fun lec05Example3(): Unit = runBlocking {
  val job = CoroutineScope(Dispatchers.Default).async {
    throw IllegalArgumentException()
  }

  delay(1_000L)
  job.await()
}


fun lec05Example2(): Unit = runBlocking {
  val job = CoroutineScope(Dispatchers.Default).launch {
    throw IllegalArgumentException()
  }

  delay(1_000L)
}


fun lec05Example1(): Unit = runBlocking {
  val job1 = CoroutineScope(Dispatchers.Default).launch {
    delay(1_000L)
    printWithThread("Job 1")
  }

  val job2 = CoroutineScope(Dispatchers.Default).launch {
    delay(1_000L)
    printWithThread("Job 2")
  }
}