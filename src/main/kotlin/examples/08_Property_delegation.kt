package examples

import kotlin.properties.Delegates

/**
 * listeners get notified about changes to this property;
 */

class Jedi {

    init {
        println("Creating jedi instance")
    }

    var name: String by Delegates.observable("empty") { _, old, new ->
        println("$old -> $new")
    }
}



fun main() {
    val jedi = Jedi()
    jedi.name = "Anakin Skywalker"
    jedi.name = "Darth Vader"

    lazyDelegatedProperty { Jedi()/*.apply { name = "lazy jedi" }*/ }
}

fun lazyDelegatedProperty(computeJedi: () -> Jedi) {
    val memoizedJedi by lazy(computeJedi)

    if (false && memoizedJedi.name != "empty") {
        println("My name is ${memoizedJedi.name}")
    }
}