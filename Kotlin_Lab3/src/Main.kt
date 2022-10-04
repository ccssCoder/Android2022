fun main() {
    var itemRepository = ItemRepository()
    var itemService = ItemService(itemRepository)
    var itemController = ItemController(itemService)

    itemController.quiz(11)
}