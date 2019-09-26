package examples

import kotlin.system.measureNanoTime

/**
 * Performance - beware that nulls are automatically wrapped
 */
class PrimitiveCollections {

    val array = Array(1000) { it + 1 }

    val arrayPrimitive = IntArray(1000) { it + 1 }

    val list = List(1000) { it + 1 }
}

fun main() {
    repeat(100) {
        val primitiveCollections = PrimitiveCollections()

        val array = measureNanoTime {
            primitiveCollections.array.forEach { it.dec() }
        }

        val list = measureNanoTime {
            primitiveCollections.list.forEach { it.dec() }
        }

        val arrayPrimitive = measureNanoTime {
            primitiveCollections.arrayPrimitive.forEach { it.dec() }
        }

        println("Results: $array")
        println("Results: $list")
        println("Results: $arrayPrimitive")
        println("-------------------------")
    }
}