package examples

import org.slf4j.LoggerFactory

/**
 * Documentation of your class
 */
object MainRunnable {
    private val log = LoggerFactory.getLogger(MainRunnable::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        log.info("Some Log Message!")
        println("Some Random Message!")
    }
}
