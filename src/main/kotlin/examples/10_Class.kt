package examples

/**
 * constructors, property + accessors (get/set)  etc.
 */

// Example is how to define Class with single property (from simple  to verbose)
// Properties & Fields (https://kotlinlang.org/docs/reference/properties.html)

/**
 * Primary constructor - part of the class header
 * - syntax for defining properties: fields with accessors - var vs val)
 */
class DefineClassDefault(val property: String)

/**
 * In case of visibility (private) modifiers or Annotation - explicit primary constructor (by default constructors are public)
 */
class DefineClassExplicitConstructor private constructor(_property: String) {
    val property = _property
}

class DefineClassWithInitializer(_property: String) {
    var property: String

    /**
     * Primary constructors cannot contain any code - can be replaced by initializer blocks
     * (you have to have strong reason to use them)
     */
    init {
        println("Init of $_property")
        property = _property
    }
}

/**
 * More constructors often doesn't make sense - because of default values on args
 * Field vs property
 */
class DefineClassMoreConstructors constructor(/*val*/ _property: String = "foo") {
    var property = _property

    init {
        println("First init block: $property")
        property = _property.length.toString()
    }

    constructor() : this("foofun")

    init {
        println("Second  init block: $property")
    }
}

fun testDefineClass() {
    DefineClassDefault("fork").property
//    DefineClassDefault("fork").property = 1

//    DefineClassExplicitConstructor("x").property

    val defineClassWithInitializer = DefineClassWithInitializer("f")
    defineClassWithInitializer.property = "z"
    println(defineClassWithInitializer.property)

    DefineClassMoreConstructors().property
}

/**
 * Properties works and how to rewrite accessors
 */
class FooWithProps(property: String, property2: Int, property3: String = "x") {
    var property = property
        private set
        get() = "$field !"

    /**
     * internal modifier/visibility - everyone who sees declaring class sees this member
     */
    internal var property2 = property2
        set(value) {
            println("computing $field += $value")
            field += value
        }
        get() {
            println("computing $field / 2")
            return field / 2 //we have to return right type
        }

    val property3 = property3
    //private set   setter not allowed for val

    override fun toString(): String {
        return "FooWithProps($property , $property2)"
    }
}

fun main() {
    testDefineClass()

    
    val fooWithProps = FooWithProps("1", 1)

    //  fooWithProps.property = 1    //setter is private

    println(fooWithProps.property)
    println(fooWithProps.property2)
    fooWithProps.property2 = 4
    println(fooWithProps.property2)
    println(fooWithProps.property3)
}

//data class
data class Foo5(val string: String)

fun copyExaple() {
    val foo = Foo5("s")
    val copy = foo.copy()
    val copy2 = foo.copy(string = "foo2")

    println("$foo $copy $copy2")
}



