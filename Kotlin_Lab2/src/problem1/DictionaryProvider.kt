package problem1

object DictionaryProvider {
    fun createDictionary(type: DictionaryType) : IDictionary {
        when(type) {
            DictionaryType.ARRAY_LIST   -> {
                println("ArrayList-Dictionary instance is created!")
                return ListDictionary
            }
            DictionaryType.TREE_SET     -> {
                println("TreeSet-Dictionary instance is created!")
                return TreeSetDictionary
            }
            DictionaryType.HASH_SET     -> {
                println("HashSet-Dictionary instance is created!")
                return HashSetDictionary
            }
        }
    }
}