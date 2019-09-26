package examples

import java.util.stream.Stream
import kotlin.streams.asStream
import kotlin.system.measureTimeMillis

fun main() {
    //by default immutable
    val stringList = listOf("one", "two", "one")
    val stringSet = setOf("one", "two", "three")

    //list ->  access index
    val secondFromList = stringList[1]   //stringSet[1] - not possible

    //map
    val numbersMap = mutableMapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    numbersMap["key2"] = 3


    //coping
    numbersMap.toList().toSet().toMutableList()

    //cool functions - create map from list
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.capitalize() })


    //operations (extension functions)
    val plus = stringList + stringSet
    val plus2 = stringSet + stringList
    println("$plus vs. $plus2")


    //ranges
    for (i in 1..4) print(i)
    for (i in 4 downTo 1) print(i)
    for (i in 8 downTo 1 step 2) print(i)
    //also own ranges can be implemented (object with comparable interface)

    // Sequences  https://typealias.com/guides/when-to-use-sequences/

    val sequenceOf = sequenceOf(1, 2, 3)

    val sequence = generateSequence(1) { it + 1 }.take(5000000)
    val list = sequence.toList()

    println("\nPerf comparison:")

    println("List Map Filter Sum\t\t\t " + measureTimeMillis {
        list.map { it * 2 }.filter { it % 3 == 0 }.sum()
    })
    println("Sequence Map Filter Sum\t\t " + measureTimeMillis {
        sequence.map { it * 2 }.filter { it % 3 == 0 }.sum()
    })

    println("List Map Filter Average\t\t " + measureTimeMillis {
        list.map { it * 2 }.filter { it % 3 == 0 }.average()
    })
    println("Sequence Map Filter Average\t " + measureTimeMillis {
        sequence.map { it * 2 }.filter { it % 3 == 0 }.average()
    })

    println("List Map Filter Count\t\t " + measureTimeMillis {
        list.map { it * 2 }.filter { it % 3 == 0 }.count()
    })
    println("Sequence Map Filter Count\t " + measureTimeMillis {
        sequence.map { it * 2 }.filter { it % 3 == 0 }.count()
    })
    println("List Map Filter List\t\t " + measureTimeMillis {
        list.map { it * 2 }.filter { it % 3 == 0 }
    })
    println("Sequence Map Filter List\t " + measureTimeMillis {
        sequence.map { it * 2 }.filter { it % 3 == 0 }.toList()
    })

    val str: Stream<Int> = stream {
        yield(0)
    }

    zipCollections()

    slicing()
}

//    coroutine sequence
fun <T> stream(block: suspend SequenceScope<T>.() -> Unit) = sequence(block).asStream()

// associating to map - last added, preserve iteration order of collection

// associate by - items become value
fun associateBy(c: Collection<Int>) = c.associateBy { it.toString() }

// associate with - items become keys
fun associateWith(c: Collection<Int>) = c.associateWith { it.toString() }

// by + with
fun associate(c: Collection<Int>) = c.associate { it.toString() to it.toLong() }

// zip
fun zipCollections() {
    val col1 = listOf(1, 2, 3)
    val col2 = setOf(3, 4, 5, 6)

    val zipped = col1.zip(col2)

    val zipped2 = col2.zip(col1)

    println("Zip: $zipped ")
    println("Zip2: $zipped2 ")
    println("Zip withNext: ${col1.zipWithNext()} ")
}

fun slicing() {
    val col1 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val chunked = col1.chunked(2) //{ it.toSortedSet() }

    val windowed = col1.windowed(4, 2)


    println("Chunked: $windowed ")
    println("Windowed: $windowed ")
}


