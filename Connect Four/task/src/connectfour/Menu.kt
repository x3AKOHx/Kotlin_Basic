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
        println("Do you want to play single or multiple games?\n" +
                "For a single game, input 1 or press Enter\n" +
                "Input a number of games:")
        val amountOfGames = gameType()
        println("${playerOne.name} VS ${playerTwo.name}\n${board.rows} X ${board.columns} board")
        gameProcess(amountOfGames)
    }

    private fun gameProcess(games: Int = 1) {
        var currentPlayer = playerOne
        var gameCount = 1
        var playerOneScore = 0
        var playerTwoScore = 0
        if (games > 1) {
            println("Total $games games")
            for (i in 1..games) {
                println("Game #$gameCount")
                board.drawTheField()
                while (makeMove(currentPlayer)) {
                    board.drawTheField()
                    currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne
                }
                if (board.isGameEnded() == "Draw") {
                    playerOneScore++
                    playerTwoScore++
                } else {
                    if (currentPlayer == playerOne) playerOneScore += 2 else playerTwoScore += 2
                }
                println("Score")
                println("${playerOne.name}: $playerOneScore ${playerTwo.name}: $playerTwoScore")
                board.clearField()
                gameCount++;
                currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne
            }
            print("Game over!")
        } else {
            println("Single game")
            board.drawTheField()
            while (makeMove(currentPlayer)) {
                board.drawTheField()
                currentPlayer = if (currentPlayer == playerOne) playerTwo else playerOne
            }
            print("Game over!")
        }
    }

    private fun makeMove(player: Player): Boolean {
        println("${player.name}'s turn:")
        val input = readLine()!!
        val reg = "[0-9]+".toRegex()
        var gameGoing = true
        if (input == "end") {
            print("Game over!")
            gameGoing = false
        } else if (!input.matches(reg)) {
            println("Incorrect column number")
            gameGoing = makeMove(player)
        } else if (input.toInt() !in 1..board.columns) {
            println("The column number is out of range (1 - ${board.columns})")
            gameGoing = makeMove(player)
        } else if (board.field[0][input.toInt() - 1] != ' ') {
            println("Column $input is full")
            gameGoing = makeMove(player)
        } else {
            when (board.putCoin(input.toInt(), player.symbol)) {
                "isGoing" -> gameGoing = true
                "Draw" -> {
                    board.drawTheField()
                    println("It is a draw")
                    gameGoing = false
                }
                "Win" -> {
                    board.drawTheField()
                    println("Player ${player.name} won")
                    gameGoing = false
                }
            }
        }
        return gameGoing
    }

    private fun gameType(): Int {
        val input = readLine()!!
        if (input == "") return 1
        return if (input.matches("^[1-9][0-9]*\$".toRegex())) {
            input.toInt()
        } else {
            println("Invalid input")
            println("Do you want to play single or multiple games?\n" +
                    "For a single game, input 1 or press Enter\n" +
                    "Input a number of games:")
            gameType()
        }
    }
}