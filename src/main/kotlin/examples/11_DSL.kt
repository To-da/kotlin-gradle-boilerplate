package examples

/**
 * TODO description
 */

abstract class Printer(private val textToPrint: String) {

    init {
        println(textToPrint)
    }

    override fun toString(): String {
        return textToPrint
    }
}

class Outside(text: String): Printer(text)

class Inside(text: String): Printer(text)

fun Outside.inside(insideText: String) {
    Inside("$insideText in $this")
}

fun Outside.inside(insideText: String, init: Inside.() -> Unit): Inside = Inside("$insideText in $this").apply(init)

fun outside(outsideText: String, init: Outside.() -> Unit): Outside = Outside(outsideText).apply(init)

fun main() {
    outside("Outside text") {
        inside("Inside text") {
            inside("Inside Inside text")
        }
        inside("Next Inside text")
    }
}