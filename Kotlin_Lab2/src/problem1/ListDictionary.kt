package problem1

// ● Implement the IDictionary interface in a ListDictionary class that stores the
// words in a mutable list as shown in Fig. 1. ListDictionary should be a singleton!
object ListDictionary: IDictionary {
    private var words = mutableListOf<String>("Dog", "Cat")

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