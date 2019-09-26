package examples

/**
 * Describe Variance, Covariance and Contravariance
 */

interface Framework {
    val versionDefault: Int
        get() = 1

    fun version(): String = "v.$versionDefault"
}

class Spring(val version: Int) : Framework {

    override val versionDefault: Int
        get() = version

    override fun toString(): String {
        return "${this::class.simpleName}:$versionDefault"
    }
}

class Ratpack : Framework

fun variance() {
    val spring = Spring(4)
    val ratpack = Ratpack()
    var framework: Framework = spring
    framework = ratpack
    println(framework)
}

fun covariance() {
    val springList: List<Spring> = listOf(Spring(5), Spring(3))
    val frameworkList: List<Framework> = springList  //type relation is preserved (Frameworks <-- examples.Spring)
    println(frameworkList)
}

interface Compare<in T : Framework> {
    fun compare(first: T, second: T): Int
}

fun contravariance() {

    val frameworkCompare: Compare<Framework> = object : Compare<Framework> {
        override fun compare(first: Framework, second: Framework): Int {
            return first.versionDefault - second.versionDefault
        }
    }
    val springCompare: Compare<Spring> = frameworkCompare

    println(springCompare.compare(Spring(1), Spring(2)))

    val frameworkComparator: Comparator<Framework> =
            Comparator<Framework> { first, second -> first.versionDefault - second.versionDefault }

    val sortedWith = listOf(Spring(5), Ratpack()).sortedWith(frameworkComparator)
    println(sortedWith)
}

fun main() {
    variance()
    covariance()
    contravariance()
}
