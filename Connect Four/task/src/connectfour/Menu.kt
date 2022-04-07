package connectfour

class Menu {
    private val board = Field()

    private lateinit var playerOne: Player
    private lateinit var playerTwo: Player

    fun mainMenu() {
        println("Connect Four")
        println("First player's name:")
        playerOne = Player(readLine()!!, 'o')
        println("Second player's name:")
        playerTwo = Player(readLine()!!, '*')
        board.createCustomField()
        println("${playerOne.name} VS ${playerTwo.name}\n${board.rows} X ${board.columns} board")
        board.drawTheField()
        gameProcess()
    }

    private fun gameProcess() {
        var currentPlayer = playerOne
        while (makeMove(currentPlayer)) {
            board.drawTheField()
            currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne
        }
    }

    private fun makeMove(player: Player): Boolean {
        println("${player.name}'s turn:")
        val input = readLine()!!
        val reg = "[0-9]+".toRegex()
        if (input == "end") {
            print("Game over!")
            return false
        }
        if (!input.matches(reg)) {
            println("Incorrect column number")
            makeMove(player)
        } else if (input.toInt() !in 1..board.columns) {
            println("The column number is out of range (1 - ${board.columns})")
            makeMove(player)
        } else if (board.field[0][input.toInt() - 1] != ' ') {
            println("Column $input is full")
            makeMove(player)
        } else {
            when (board.putCoin(input.toInt(), player.symbol)) {
                "isGoing" -> return true
                "Draw" -> {
                    board.drawTheField()
                    print("It is a draw\nGame over!")
                    return false
                }
                "Win" -> {
                    board.drawTheField()
                    println("Player ${player.name} won")
                    print("Game over!")
                    return false
                }
            }
        }
        return true
    }
}