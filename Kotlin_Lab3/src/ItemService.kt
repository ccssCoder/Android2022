
// ●    ItemService - business logic - allows
//      random selection of a given number of
//      questions
class ItemService(private val itemRepository: ItemRepository) {

    fun selectRandomItems(number: Int): List<Item> {
        val randomizedItems = mutableListOf<Item>()

        try {
            var cycleVariable = 0
            while (cycleVariable < number) {
                randomizedItems.add(itemRepository.randomItem())
                cycleVariable += 1
            }
        } catch (e: Exception) {
            //println(e.message)
            println("Don't have that many questions, but you can have what we have c(: (${randomizedItems.size})")
        }

        return randomizedItems
    }
}
