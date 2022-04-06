package connectfour

class Menu {

    private val board = Field()

    fun mainMenu() {
        println("Connect Four")
        println("First player's name:")
        val playerOne = Player(readLine()!!)
        println("Second player's name:")
        val playerTwo = Player(readLine()!!)
        board.createCustomField()
        print("${playerOne.name} VS ${playerTwo.name}\n${board.rows} X ${board.columns} board")
    }
}