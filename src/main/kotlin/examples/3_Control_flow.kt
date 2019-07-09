package examples

/**
 * TODO description
 */

fun main() {
    val a: Number = 1
    val b: Number = 2.3

    var c: String

    if (a < b) {
        c = "foo"
    }

    //---------//

    if (a == b) c = "foo2"

    //---------//

    c = if (a < b) "is" else "not"

    //---------//

    c = if (b < a) {
        print("first")
        "is"
    } else {
        print("sec")
        "not"
    }

    //---------//
}

operator fun Number.compareTo(number: Number): Int {
    return when (number) {
        is Float -> number.compareTo(this.toFloat())
        is Int -> number.compareTo(this.toInt())
        is Double -> number.compareTo(this.toDouble())
        else -> throw IllegalArgumentException("Unexpected numeric type ${number::class}") //class  literal syntax (return Kclass)
    }
}

sealed class SealedClass 
class First : SealedClass()
class Second : SealedClass()
class Third : SealedClass()


fun test(sealedClass: SealedClass) {
    val x = when (sealedClass) {
        is First -> println("first")
        is Second -> println("second")
        is Third ->  println("third")
//        else -> ("nothing")
        }
}

