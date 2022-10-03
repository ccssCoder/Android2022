package problem1

import kotlin.collections.HashSet

// ● Implement IDictionary interface in a HashSetDictionary class that stores the
// words in a HashSet collection. HashSetDictionary should also be a singleton!
object HashSetDictionary : IDictionary {
    private val words = HashSet<String>()

    override fun add(str: String): Boolean {
        if(str.isNotEmpty()) {
            words.add(str)
            return true
        }
        return false
    }

    override fun find(str: String): Boolean {
        return words.contains(str)
    }

    override fun size(): Int {
        return words.size
    }
}
