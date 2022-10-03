package problem1

// ● Define an IDictionary interface for an English dictionary containing the following
// operations: add a word, find a word and get the size of the dictionary.
interface IDictionary{
    fun add(str : String) : Boolean
    fun find(str : String) : Boolean
    fun size() : Int

    // ● The dictionary’s name is a constant and should be defined using a companion object in
    // the interface.
    companion object{
        const val name : String = "Angol Szotar"
    }
}