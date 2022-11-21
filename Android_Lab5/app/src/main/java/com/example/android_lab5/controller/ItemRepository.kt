
// ●    ItemRepository - data access layer
//      with some CRUD operations; for
//      simplicity initialize items here
class ItemRepository() {
    private val items = mutableListOf<Item>()

    init {
        createDummyList()
    }

    fun randomItem(): Item {
        items.shuffle()

        val selected: Item = items[0]
        items.removeAt(0)

        return selected
    }

    fun save(item: Item) {
        items.add(item)
    }

    fun size(): Int {
        return items.size
    }

    fun getAllQuestions(): MutableList<Item> {
        return items
    }

    private fun createDummyList() {
        save(Item(
                "Which collection search with most efficency?",
                listOf("Set", "List", "Same efficency for all", "Map"),
                3
            )
        )
        save(Item("How can u refer for the parent class?",
                listOf("this", "->", "super", "parent class name"),
                2
            )
        )
        save(Item("What are U stand for in CRUD?",
                listOf("Uninstall", "Update", "Uppercase", "Under"),
                1
            )
        )
        save(Item("How does Version control helps software development?",
                listOf("Not helping", "Helps developers to work on same project", "Useless", "I don t know"),
                1
            )
        )
        save(Item("What is gitflow?",
                listOf("No idea", "Batman knows", "Developing different features in different branch", "I don t know"),
                2
            )
        )
        save(Item("What is jdk?",
                listOf("It's a special tool to interpret code", "It's a compiling tool", "Server side programming language", "I don t know"),
                1
            )
        )
        save(Item("What is gradle used for?",
            listOf("Compiling", "Version control", "Dependency manager", "IDK"),
            2
            )
        )
        save(Item("What is maven used for?",
            listOf("Dependency manager", "Compiling", "Version control", "IDK"),
            0
            )
        )
        save(Item("What's the keyword for defining a function in kotlin?",
            listOf("void", "function", "fun", "method"),
            2
            )
        )
        save(Item("What's the purpose of RecyclerView?",
            listOf("It s a simple ScrollList", "o.O", "It helps to show lot of items on a list", "Say what?"),
            2
            )
        )
        save(Item("Where can I not get context?",
            listOf("Fragment", "ContentProvider", "Activity", "View"),
            1
            )
        )
        save(Item("What s the purpose of the ViewModel?",
            listOf("To hold and share data between Fragments", "To help dependencies", "Helps solving logging", "It s a design pattern"),
            0
            )
        )
        save(Item("what s necessary prefix to call a function from a coroutine?",
            listOf("static", "final", "abstract", "suspend"),
            3
            )
        )
    }
}
