package coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


fun main(): Unit = runBlocking {
  val time = measureTimeMillis {
    val job1 = async(start = CoroutineStart.LAZY) { apiCall1() }
    val job2 = async(start = CoroutineStart.LAZY) { apiCall2() }

    job1.start()
    job2.start()
    printWithThread(job1.await() + job2.await())
  }

  printWithThread("소요 시간 : $time ms")
}

suspend fun apiCall1(): Int {
  delay(1_000L)
  return 1
}

suspend fun apiCall2(): Int {
  delay(1_000L)
  return 2
}






fun example5(): Unit = runBlocking {
  val job = async {
    3 + 5
  }
  val eight = job.await() // await : async의 결과를 가져오는 함수
  printWithThread(eight)
}


fun example4(): Unit = runBlocking {
  val job1 = launch {
    delay(1_000)
    printWithThread("Job 1")
  }
  job1.join()

  val job2 = launch {
    delay(1_000L)
    printWithThread("Job 2")
  }
}


fun example3(): Unit = runBlocking {
  val job = launch {
    (1..5).forEach {
      printWithThread(it)
      delay(500)
    }
  }

  delay(1_000L)
  job.cancel()
}

fun example2(): Unit = runBlocking {
  val job = launch(start = CoroutineStart.LAZY) {
    printWithThread("Hello launch")
  }

  delay(1_000L)
  job.start()
}

fun example1() {
  runBlocking {
    printWithThread("START")
    launch {
      delay(2_000L) // yield()
      printWithThread("LAUNCH END")
    }
  }

  printWithThread("END")
}