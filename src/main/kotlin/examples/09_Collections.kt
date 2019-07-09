package examples

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


//todo sequences
}
