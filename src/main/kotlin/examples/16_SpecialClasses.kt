package examples

/**
 * Example Data, Enum, Sealed class
 */

// enums

enum class EnumClassSimple { ONE, TWO, SIX }

interface Def {
    fun defautality(): Int
}

/**
 * Can implement interface, be anonymous etc.
 */
enum class EnumClassComplex(var desc: String) : Def {
    DEFAULT("default value") {
        override fun defautality() = Int.MAX_VALUE
    },
    NON_DEFAULT("non_default value") {
        override fun defautality(): Int = Int.MIN_VALUE
    }
    ;

    abstract override fun defautality(): Int
}

// enums


// data classes

/**
 * Compiler process only primary const. properties to genrate functions (equals, toString, hashCode, copy, componentx)
 */
data class Person(val name: String) {
    var age: Int = 0
}

data class WithMoreConstructors constructor(var time: String) {

    constructor(sec: Int) : this((sec * sec).toString() + "sec")
}

// data classes


// sealed classes

/**
 * Restricted hierarchies - inside Sealed class as nesteded classes or same file
 */
sealed class ComputationResult
class Good(val origComp: ComputationResult, val desc: String) : ComputationResult()
data class Bad(val badDesc: String) : ComputationResult()
object NotValidResult : ComputationResult()

fun eval(comp: ComputationResult): Boolean = when(comp) {
    is Good -> {
        println("Processing good result with desc: ${comp.desc}")
        comp.desc.length > 1
    }
    is Bad -> eval(Good(comp, comp.badDesc))
    NotValidResult -> false
    // the `else` clause is not required because we've covered all the cases
}


// sealed classes

fun main() {
    EnumClassSimple.ONE

    val values = EnumClassSimple.values()

    val valueOf = EnumClassSimple.valueOf("ONE")

    val nonDefault = EnumClassComplex.NON_DEFAULT

    println(nonDefault.desc)

    nonDefault.desc = "asdasdas"

    println(nonDefault.desc)

    println(EnumClassComplex.NON_DEFAULT.desc)

    println(Person("Nixon").apply { age = 56 })
    println(WithMoreConstructors(2))

    println(eval(Bad("yxxx")))

    println(eval(NotValidResult))
}