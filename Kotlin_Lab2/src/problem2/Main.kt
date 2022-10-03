package problem2

//  ●   Define an extension function that prints the monogram of a String containing the
//      firstname and lastname. Example: John Smith → JS
//      Loops are forbidden! Try to use split, map and joinToString!
fun String.printsMonogram() {
    println(
        this.split(" ")
            .map { it.subSequence(0,1) }
            .joinToString (separator = "") { it }
    )
}

// ●  Define a compact extension function that returns the elements of a strings’ list joined by
//    a given separator!
//    Example: (list: [“apple”, “pear”, “melon”]; separator: # ) → ”apple#pear#melon”
fun List<String>.joinedByGivenSeparator(delim: String) : String
        = this.joinToString (separator = delim) { it }

// ●  Define a compact extension function for a strings’ list that returns the longest string!
//    Example: Longest [apple, pear, strawberry, melon] = strawberry
fun List<String>.longest() : String = this.maxOrNull()!!

fun main() {
    val myString= "John Smith"
    myString.printsMonogram()


    val list = listOf<String>("apple", "pear", "melon")
    println(list.joinedByGivenSeparator("#"))

    val list2 = listOf<String>("apple", "pear", "strawberry", "melon")
    println(list2.longest())
}