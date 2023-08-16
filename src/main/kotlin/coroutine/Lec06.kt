package coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
  launch {
    delay(600L)
    printWithThread("A")
  }

  launch {
    delay(500L)
    throw IllegalArgumentException("코루틴 실패!")
  }
}