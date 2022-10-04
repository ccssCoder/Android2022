// ● ItemController - quiz method
//      ○ Selects a given number of items
//      ○ Performs the quiz:
class ItemController(private val itemService: ItemService) {
    fun quiz(nr: Int) {
        var selectedQuestions = itemService.selectRandomItems(nr)
        var questionsReceived = selectedQuestions.size
        var correctAnswers = 0
        var word: String?

//      ■ Shows the questions one by one to the user
//      and requests a response.
        selectedQuestions.forEachIndexed {
            index, element -> println("[${index + 1}] ${element.question}")
            element.answers.forEachIndexed{
                index, answer -> println("${index + 1}. $answer")
            }

            word = readLine()
            if( word.equals("quit") ){
                return
            }
//          ■ Evaluate the response
            if(word == (element.correct+1).toString()){
                correctAnswers++
            }
        }
//      ○ Shows the final result in the following format:
//      Correct answers/Total number of answers
        println("Correct answers/Total number of answers: $correctAnswers/$questionsReceived")
    }
}