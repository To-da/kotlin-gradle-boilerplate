package examples

/**
 *    equality (equals vs == (structural  equality) vs === (referential  equality))
 *    in case of nullable objects
 */

fun main() {
    val nullable: String?  = "foo"
    val notNullable: String = "foo"

    println(nullable == notNullable)
    println(nullable.equals(notNullable))
    println(nullable === notNullable)

    val boxedInt: Int?  = 1
    val notBoxedInt: Int = 1

    println(boxedInt == notBoxedInt)
    println(boxedInt?.equals(notBoxedInt))
    println(boxedInt === notBoxedInt)

    val integer: Int  = 1
    val boxedNullableInt: Int? = integer

    println(integer == boxedNullableInt)
    println(integer === boxedNullableInt)

    val a: Int = 1
    println(a === a) // Prints 'true'
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) //false
    println(boxedA == anotherBoxedA) //true  for equality
}
