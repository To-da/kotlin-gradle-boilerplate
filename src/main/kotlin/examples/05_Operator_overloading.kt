package examples

import java.util.*
import kotlin.math.pow

/**
 * TODO show invoke() overloading - Class as a function
 * https://proandroiddev.com/til-about-operator-overloading-in-kotlin-and-the-invoke-operator-da3686ef4694
 */

/**
 * vararg - (Java 5 - Float... numbers)
 */
class Vector(vararg val numbers: Float) {

    fun size() = numbers.size

    override fun toString(): String {
        return "Vector of ${Arrays.toString(numbers)}"
    }
}

operator fun Vector.plus(vector: Vector): Vector {
    if (this.size() == vector.size()) {
        val floatArray = this.numbers.mapIndexed { index, number -> vector.numbers[index] + number }.toFloatArray()
        //spread operator  (vararg java))
        return Vector(*floatArray)
    } else {
        throw IllegalArgumentException("sizes of vectors are not the same: this: ${this.size()} vs. arg: ${vector.size()}")
    }
}

/**
 *  Class representing some action
 */
class VectorPrettyPrintAction {

    private fun prettyPrint(vector:Vector, limit: Int = Int.MAX_VALUE) {
        vector.numbers.take(limit).forEach { println("|$it|") }.apply { println() }
    }

    operator fun invoke(vector: Vector) {
        prettyPrint(vector)
    }

    operator fun invoke(vector: Vector, limit: Int) {
        prettyPrint(vector, limit)
    }
}


fun main() {
    println(Vector(1.2f, 2.5f, 3.4f) + Vector(3.4f, -2.5f, 12.1f))

    val vector = Vector(1f,2f, 3f)

    println(vector.plus(vector))

    val vectorPrintAction = VectorPrettyPrintAction()

    vectorPrintAction(vector)
    vectorPrintAction(vector, 2)

    //Already in collections
    val a = listOf(1, 2, 3)
    val b = setOf(4, 5, 6)

    val x = a + b + 7 + List(6) { it.toDouble()/*.also { i -> println("b $i") }*/.pow(it).toInt()}  //use List constructor

    println(x)
}
