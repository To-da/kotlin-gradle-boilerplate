package examples

/**
 * example of Interface, constants in  interface, destructuralization and anonymous class by objects
 */

interface ObjectInterface {

    val foo: String



    fun fooOperator()

    fun fooWithDefaultOperator() = "FooResult"

    companion object {

        //compile time constant
        const val CONSTANT = 1

        val EnumClass = ObjectInterface.EnumClass.DEFAULT
    }

    enum class EnumClass {
        DEFAULT {
            override fun defautality() = Int.MAX_VALUE
        };

        abstract fun defautality(): Int
    }
}

fun main() {
    ObjectInterface.CONSTANT

    val value = object : ObjectInterface {

        override fun fooOperator() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override val foo = "Foo"

        val x = Pair(1, "A")
    }

    val (number, string) = value.x

    number to string
}
