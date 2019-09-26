package examples

import com.beust.klaxon.Klaxon
import com.beust.klaxon.json
import java.io.StringReader

/**
 * Examples of variables vs. read only variables, constants, automatic casting, raw String literals, String templating, DSL, nullability
 */
//primitive type (String, Number, Boolean) known at compile time (can be used for example in annotations)
const val CONSTANT = 1.2

fun main() {
    var variable: String

    //read-only variable
    val value: String

    //not possible to do that - defined type (not-null)
//    value = null

    val initializedValue = "Value"  //type inferred

    //not possible to do that - value already assigned
//        initializedValue = "foo"

    val anyType: Any = 1

    if (anyType is String) {
        anyType.length   //automatic cast to String
    }

    if (anyType !is String) {
        println("not string")
    } else {
        anyType.length   //automatic cast to String
    }

    /**
     * raw String literal (no escaping (in normal string - escaping is backslash), can contain newlines etc.) example with String templating
     */
    val stringLiteral = """
        {
            "fooProperty":"$initializedValue",
            "fooNode": {
                "fooArray":[1,2,${initializedValue.length}]
            }
        }
    """

    val parsedJson = Klaxon().parseJsonObject(StringReader(stringLiteral))

    val elementInArray = parsedJson.obj("fooNode")?.array<Int>("fooArray")?.get(2)

    println(elementInArray)

    //json DSL
    val generatedJson = json {
        obj("fooProperty" to initializedValue,
                "fooNode" to obj(
                        "fooArray" to array(1, 2, initializedValue.length)
                ),
                "anotherArray" to array(array(1, 2), array("a", "b")),
                "objectArray" to array(obj("foo" to 1), obj("bool" to  false))
        )
    }
    println(generatedJson.toJsonString(true))

/*  nullable  - mention also boxing on Number and Boolean types + preserving equality vs. not preserving identity   */

    var nullableVariable: String?
    nullableVariable = null

//    val nonNullable = nullableVariable!!   // Throw NPE
    //elvis operator
    val nonNullable = nullableVariable ?: "Foo"


    /*  safe call operator + traversing */
    println(nullableVariable?.length?.unaryMinus())

    //execute operation on null
    val nullableArray: Collection<Int?> = setOf(1, null, 2)

    nullableArray.forEach { it.let(::println) }
    nullableArray.filterNotNull().forEach(::println)
}