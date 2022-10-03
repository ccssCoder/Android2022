package problem3

import java.util.*
import kotlin.random.Random
import kotlin.Comparable

// ●    Define a data class Date. The default constructor initializes the year, month and the day
//      with the current date.
data class Date (
    val year : Int  = Calendar.getInstance().get(Calendar.YEAR),
    val month : Int = Calendar.getInstance().get(Calendar.MONTH)+1, // in java month starts from 0 not from 1 so for december 11+1 = 12
    val day : Int   = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
) : Comparable<Date> {
    override fun compareTo(other: Date) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> day - other.day
    }
}


// ●    Define an extension function that checks whether the year of the date is a leap year.
fun Date.isLeapYear(): Boolean {
    return (year % 4 == 0) && (year % 100 != 0) && (year % 400 == 0)
}

// ●    Define an extension function that checks whether the date is a valid one!
fun Date.isValid(): Boolean {
    if (year < 0 || year > 3000) return false
    if (month < 1 || month > 12) return false
    if (day < 1 || day > 31) return false
    if (month == 2) {
        if (isLeapYear()) {
            return day <= 29
        }
    }
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        return day <= 30
    }
    return true
}

// ●    Main: Generate random dates. Check the validity of the generated date. Valid dates are
//      stored in a list, while invalid ones are printed to the standard output. Repeat the
//      generation process until 10 valid dates are generated.
fun rand(start: Int, end: Int): Int {
    require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
    return Random(System.nanoTime()).nextInt(end - start + 1) + start
}

fun main() {
    val validDates = mutableListOf<Date>()

    var d: Date
    while(validDates.size < 10) {
        d = Date(
            rand(0, 3000),
            rand(-50, 50),
            rand(-50, 50),
        )
        if(d.isValid()) {
            validDates.add(d)
        } else {
            println(d)
        }
    }

    // ●    Print the list. Use forEach in order to print each element to a new line.
    println()
    println("Generated random list of valid dates:")
    validDates.forEach{ println(it) }

    // ●    Sort the list by defining a natural ordering for the Date class (implement the
    //      Comparable<Date> interface!) Print the sorted list.
    validDates.sort()

    // ●    Reverse the sorted list, then print it.
    println()
    println("Sorted(Comparable interface) and reversed list:")
    validDates.reverse()
    validDates.forEach{ println(it) }

    // ●    Sort the list by using a custom ordering (use the Comparator<Date> interface!). Print
    //      the sorted list.
    println()
    println("Sorted(Comparator interface) custom comparator which only check month and days:")
    var dateComparator = DateComparator()
    validDates.sortWith(dateComparator)
    validDates.forEach{ println(it) }
}

// This comparator doesn't watch years, only compare by months & days
class DateComparator : Comparator<Date> {
    override fun compare(d1: Date, d2: Date) : Int {
        if( d1.month == d2.month){
            return d1.day - d2.day
        }
        return d1.month - d2.month
    }
}
