package examples

/**
 * Delegation
 */

enum class AnimalKind {
    INSECT,
    MAMMAL,
    FISH
}

interface Animal {
    fun makeSound()
    fun classification(): AnimalKind

}

class DomesticatedDog(val soundPrinter: (String) -> (Unit)) : Animal {

    override fun classification() = AnimalKind.MAMMAL

    override fun makeSound() {
        //soundPrinter.invoke("bark")
        soundPrinter("bark")
    }
}

class Labradoodle(b: Animal) : Animal by b {
//    override fun makeSound() {
//        println("wuff bark wuff")
//    }
}

fun main() {
    // s -> println(s) ...  println(it)
    val b = DomesticatedDog(::println)
    Labradoodle(b).makeSound()
    println(Labradoodle(b).classification())
}