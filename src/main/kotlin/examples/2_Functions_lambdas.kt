package examples

import kotlin.random.Random
import kotlin.random.nextUInt

/**
 * function declarations, lambda syntax, high order functions, extension functions, operators overloading, scope functions (Standard.kt), infix functions
 */

fun main() {
    fun function() {
    }

    fun functionWithReturnType(x: Int): Unit {
    }

    //function expression
    fun functionExpression() = "foo"

    //destructuring declarations (also from Pair, Map, Data class, Custom)
    //pair
    val pair = 1.to("First")
    val (intval, stringval) = pair

    //data class
    data class DescExaple(val intVal: Int, val stringVal: String)
    val (intvaldc, stringvaldc) = DescExaple(1, "first")

    //map  (with example of infix function to)
    val map = mapOf(1 to "first", 2 to "second")
    for ((key, value) in map) {
        println("key:  $key")
        println("value: $value")
    }

    //destructuring in lambdas + underscore for unused variable
    map.mapValues { (_, value) -> "$value  of map"}.forEach(::println)

    //how destructuring  works - convention - operator componentN

    
    //lambdas - syntax - always curly braces  , small piece of behavior, can be stored in variable, more often directly passed to function

    List(10){ Random.nextInt()}

    //using "it" keyword
    val lambda: (UInt) -> String
    fun lambdaAsArg(lambda: ((UInt) -> String)) {
        println(lambda(Random.nextUInt()))
    }

    lambda = { integer -> integer.toString()}
//    lambda = { it.toString()}
//    lambda = Int::toString

    lambdaAsArg(lambda)

    //defining lambda  directly
    lambdaAsArg { it.dec().toString()}

    fun lambdaAsArgWithParam(defaultvalue:UInt, lambda: ((UInt) -> String)) {
        println(lambda(Random.nextUInt()))
    }

    //outside of arg parenthesis - works only for one lambda argument (convention)
    lambdaAsArgWithParam(UInt.MAX_VALUE) { it.dec().toString()}

    lambdaAsArgWithParam(UInt.MAX_VALUE, lambda)



    //high order functions
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    fun length(s: String) = s.length
    fun isOdd(x: Int) = x % 2 != 0

    val oddLength = compose(::isOdd, ::length)
    println(listOf("a", "ab", "abc").filter(oddLength))


    //extension functions
    fun String.hello(): String {
        return "hello ${this.capitalize()} !"
    }

    println("toda".hello())

    //scope functions (standard library - Standard.kt)  -  let/also/run/apply + with   https://kotlinlang.org/docs/reference/scope-functions.html

    data class FooClass(var foo:String)

    FooClass("f").foo = "x"

    //operators overloading - see Control flow
}