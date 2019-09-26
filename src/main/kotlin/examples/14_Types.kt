package examples

import kotlin.random.Random
import kotlin.reflect.KClass

/**
 * General types: Any, Unit,Nothing
 *
 * Type inference (deduction)
 *
 * Null types - see:
 * https://medium.com/@elizarov/null-is-your-friend-not-a-mistake-b63ff1751dd5
 * https://medium.com/@elizarov/dealing-with-absence-of-value-307b80534903
 */

/**
 * Any - root of the object hierarchy (all Kotlin classes have Any as supertype) - same as java.lang.Object in Java.
 * Unit - (object - singleton) indicate that called function has only side effects and do not return anything interesting
 * Void - do not use it in Kotlin - Java type (wrapper for primitive type void)
 * Nothing - (cannot be instantiated) represent "a value that never exists"
 */

// -- Any type --
/**
 * Any - root of the object hierarchy (all Kotlin classes have Any as supertype) - same as java.lang.Object in Java.
 *
 * equals/hashcode/toString methods (= defined for all Kotlin classes), (no wait/notify  (Thread sync.))
 */
fun anyExample() {
    var any: Any = "val"
    println(any)
    any = 1.4
    println(any)

    // null is in Java type-system considered to be member of every type - top type ("x" or null are valid String )

    // null is part of the Kotlin type system but null still cannot carry type information

    var anyNullable: Any? = null
    var stringNullable = anyNullable as String?
    var intNullable = stringNullable as Int?

    anyNullable = 1
    anyNullable = "1"

    stringNullable = "1"

    intNullable = 1
}
// -- Any type --


// -- Unit type --
/**
 * Unit - (object - singleton) indicate that called function has only side effects and do not return anything interesting
 * Void - do not use it in Kotlin - Java type (wrapper for primitive type void)
 */

interface Meter<T> {
    fun measure(): T
}

class IntResultMeter : Meter<Int> {
    override fun measure(): Int {
        return Random.nextInt()
    }
}

class NoJavaResultMeter : Meter<Void?> {
    override fun measure(): Void? {
        println("do nothing by Void")
        return null
    }
}

class NoResultMeter : Meter<Unit> {
    override fun measure() {
        println("do nothing by Unit")
    }
}

fun unitExample() {
//    fun printMeterValue(meter: Meter<*>) {
//        println("Measure: ${meter.measure()}")
//    }

//    fun printMeterValue(meter: Meter<out Any?>) {
//        println("Measure: ${meter.measure()}")
//    }

    fun <T> printMeterValue(meter: Meter<T>) {
        println("Measure: ${meter.measure()}")
    }

    printMeterValue(IntResultMeter())
    printMeterValue(NoResultMeter())
    printMeterValue(NoJavaResultMeter())
}
// -- Unit type --


// -- Nothing type --
/**
 * Nothing - (cannot be instantiated) represent "a value that never exists"
 */

fun fail(message: String): Unit {
    //warns unreachable code
//    failReturnNothing(message)
    throw IllegalStateException(message)
    println("Unreachable After Throw")
}

fun failReturnNothing(message: String): Nothing {
    //not warns unreachable code
//    fail(message)
    throw IllegalStateException(message)
    println("Unreachable After Throw")
}

fun nothingExample() {
//    val receiptNumber: Number? = null
    val receiptNumber: Number? = 1
    // specific return type in case of nothing
    val any = receiptNumber ?: fail("foo")
    // number cannot be null
    val number = receiptNumber ?: failReturnNothing("foo")

    // no information for compiler to determinate more specific type
    val nothingType = null
    val nothingList = listOf(null)

    println(receiptNumber.javaClass.kotlin)
    println(receiptNumber::class) //  class literal
    println("Is null Nothing: ${nothingType is Nothing}")
    println("Is null Nothing?: ${nothingType is Nothing?}")

}
// -- Nothing type --

fun main() {
    val templ = Template<String>()
    templ.fooWithType(mutableListOf(1), Int::class.java)
    templ.fooWithTypeKotlin(mutableListOf(1), Int::class)
    templ.fooWithTypeReifiedKotlin(mutableListOf(1))

    anyExample()

    unitExample()

    nothingExample()

    val f: FunctionTypeAlias<String> = { it.length } //String::length

    println(f("hello"))
}


// --  Typealiases --

typealias IntMeterSet = Set<Meter<Int>>
typealias GenericMeterSet<T> = Set<Meter<T>>

typealias FunctionTypeAlias<T> = (T) -> Int


// -- Reified type parameters --

/**
 * Java Generics - parametrized tyoes are not stored in bytecode (= type erasure)
 * - generics are enforced (and known only) during compile-tim
 */

class Template<T> {
    fun typedArg(t: T) = t

    // same signature - after type erasure the signature is the same
//    fun foo(l: List<Any>) = Unit
//    fun foo(l: List<String>) = Unit

    fun <V> fooWithType(l: MutableList<V>, clazz: Class<V>): MutableList<V> {
        if (clazz.isAssignableFrom(Int::class.java)) { //class reference used
//            l.add(clazz.newInstance())
            println("Int $l")
        } else if (clazz.isAssignableFrom(String::class.java)) {
            println("String $l")
        }
        return l
    }

    fun <V : Any> fooWithTypeKotlin(l: MutableList<V>, clazz: KClass<V>) {
        when (clazz) {
            Int::class -> println("Int $l")
            String::class -> println("String $l")
        }
    }

    /**
     * Because function is inlined we do not have to use extra class parameter - function is inlined and generic params are replaced with actual Class
     */
    inline fun <reified V> fooWithTypeReifiedKotlin(l: MutableList<V>) {
        when (V::class) {
            Int::class -> println("Int $l")
            String::class -> println("String $l")
        }
    }
}

fun example() {
    val templ = Template<String>()
    val typedArg = templ.typedArg("")
}

inline fun <reified T : Any> foo() = T::class.java










