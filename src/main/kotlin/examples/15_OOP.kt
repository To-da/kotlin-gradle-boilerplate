package examples

/**
 * Example of Interfaces, Abstract class, Inheritance
 */

interface Processor {

    fun process(): Boolean
}

abstract class AbstractProcessor(val name: String) : Processor {

    override fun process(): Boolean {
        return try {
            println("$name is doing processing")
            doProcess()
        } catch (e: RuntimeException) {
            e.printStackTrace()
            catchException(e)
        } finally {
            println("Processing done")
        }
    }

    abstract fun doProcess(): Boolean

    /**
     * All closed/final by default
     */
    open fun catchException(e: RuntimeException) = false
}

/**
 * Using constructor
 */
class ComplexProcessor(foo: String) : AbstractProcessor(foo/*"foo"*/) {

    override fun doProcess(): Boolean {
        return false
    }
}

fun main() {
    ComplexProcessor("Jax").process()
}



// Diamond

open class Cat {
    open fun makeSound() {
        println("Meow")
    }
}

interface Dog {

    /**
     *  interface members - 'open' by default
     */
    fun makeSound() {
        println("Wuff")
    }
}

class DomesticAnimal() : Cat(), Dog {
    // The compiler requires makeSound() to be overridden:
    override fun makeSound() {
        super<Cat>.makeSound() // call to Cat.draw()
        super<Dog>.makeSound() // call to Dog.draw()
    }
}