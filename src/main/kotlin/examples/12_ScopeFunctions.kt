package examples

/**
 * Scope functions
 */

data class TestFunctionClass(var foo: String)

fun main() {

    val testFunctionClass = TestFunctionClass("foo")
    
    /**
     * a) Temporary scope for variables (with(obj) {obj.first, obj.sec fun(obj.first,obj.sec)}) and ignoring result
     * b) Do something with receiver value(s) -  constructing new value (same like ^ but with result)
     */
    val with = with(testFunctionClass) {
        println(toString())
        this.foo + ".processed"
    }
    println("With $with")

    /**
     * Same as With but it's invoked as let
     * - used for computation + return value
     */
    val run = testFunctionClass.run {
        println(toString())
        toString()
        this.foo + ".processed"
    }
    println("Run $run")

    /**
     * Non-extension run - execute blocks of several statemens at once
     */
    testFunctionClass.run {
        println(toString())
        println(toString() + "1")
    }

    /**
     * Use for:
     * a) safe execution - ?.let {} -> execute only in case that receiver is not-null
     * b) you don't want to expose single local variable to scope getVariable.let { variable -> process(variable) }
     * c) work with properties of receiver in non-nullable manner
     */
    val let = testFunctionClass.let {
        println(it.toString())
        it.foo + ".processed"
    }
    println("Let $let")

    /**
     * Not accessing any function of receiver and want to return same receiver - often use for initialization of receiver
     */
    val apply = testFunctionClass.apply {
        println("applying" + toString())
        foo += ".processed"
    }
    println("Apply $apply")

    /**
     * Use if not
     * a) accessing/modifying receiver parameters
     * &
     * b) want to return same receiver
     * - use for additional actions (which will be executed before for example assigning parameter - like validations
     * - common is ot use different instances in this scope
     *
     * Use it on actions which needs receiver but do not alter it (removal of also do not change program logic).
     */
    val also = testFunctionClass.also {
        println("alsoing" + it.toString())
        it.foo += ".processed"
    }
    println("Also $also")
}



