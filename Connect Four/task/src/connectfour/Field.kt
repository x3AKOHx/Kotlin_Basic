package connectfour

import java.util.*

class Field {
    var rows = 6
    var columns = 7
    var field = Array(rows) {Array(columns) {' '} }

    fun createCustomField() {
        print("Set the board dimensions (Rows x Columns)\n" +
                "Press Enter for default (6 x 7)")
        val input = readLine()!!
        if (input.isEmpty()) return
        val reg = "^\\s*[0-9]+\\s*[xX]\\s*[0-9]+\\s*\$".toRegex()
        if (!input.matches(reg)) {
            println("Invalid input")
            createCustomField()
        } else {
            val rowsTemp = input.trim().lowercase(Locale.getDefault()).split("x")[0].trim().toInt()
            val columnsTemp = input.trim().lowercase(Locale.getDefault()).split("x")[1].trim().toInt()
            if (rowsTemp !in 5..9) {
                println("Board rows should be from 5 to 9")
                createCustomField()
            } else if (columnsTemp !in 5..9) {
                println("Board columns should be from 5 to 9")
                createCustomField()
            } else {
                rows = rowsTemp
                columns = columnsTemp
                field = Array(rows) {Array(columns) {' '} }
            }
        }
    }

    fun drawTheField() {
        for (i in 1..columns) print(" $i")
        print(" \n")
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                print("║${field[i][j]}")
            }
            print("║\n")
        }
        print("╚═")
        for (i in 2..columns) {
            print("╩═")
        }
        print("╝\n")
    }

    fun clearField() {
        field = Array(rows) {Array(columns) {' '} }
    }

    fun putCoin(column: Int, symbol: Char): String {
        for (i in rows - 1 downTo 0) {
            if (field[i][column - 1] == ' ') {
                field[i][column - 1] = symbol
                return isGameEnded()
            }
        }
        return "isGoing"
    }

    fun isGameEnded(): String {
        var gameState = "Draw"
        for (i in field) {
            if (' ' in i) gameState = "isGoing"
        }
        if (haveWinner()) gameState = "Win"
        return gameState
    }

    private fun haveWinner(): Boolean {
        var win = false
        var count = 0
        //test horizontal lines
        for (i in field) {
            for (x in 1 until columns) {
                if (i[x] == i[x-1] && i[x] != ' ') {
                    count++
                    if (count == 3) {
                        win = true
                        break
                    }
                } else {
                    count = 0
                }
            }
        }
        //test horizontal ended
        count = 0
        //test vertical
        for (j in 0 until columns) {
            for (i in 1 until rows) {
                if (field[i][j] == field[i - 1][j] && field[i][j] != ' ') {
                    count++
                    if (count == 3) {
                        win = true
                        break
                    }
                } else {
                    count = 0
                }
            }
        }
        //test vertical ended
        count = 0
        //test diagonal
        val diagonalsList = arrayListOf<String>()
        for (i in 0..rows - 4) {
            var diagonal = ""
            var x = i
            var y = 0
            while (x < rows && y < columns && x >= 0 && y >= 0) {
                diagonal += field[x][y]
                x++
                y++
            }
            diagonalsList.add(diagonal)
        }
        for (i in 1..columns - 4) {
            var diagonal = ""
            var x = 0
            var y = i
            while (x < rows && y < columns && x >= 0 && y >= 0) {
                diagonal += field[x][y]
                x++
                y++
            }
            diagonalsList.add(diagonal)
        }
        for (i in 0..rows - 4) {
            var diagonal = ""
            var x = i
            var y = columns - 1
            while (x < rows && y >= 0 && x >= 0) {
                diagonal += field[x][y]
                x++
                y--
            }
            diagonalsList.add(diagonal)
        }
        for (i in columns - 2 downTo 2) {
            var diagonal = ""
            var x = 0
            var y = i
            while (x < rows && y < columns && x >= 0 && y >= 0) {
                diagonal += field[x][y]
                x++
                y--
            }
            diagonalsList.add(diagonal)
        }
        for (i in diagonalsList) {
            if (i.contains("oooo") || i.contains("****")) {
                win = true
            }
        }
        //test diagonals ended
        return win
    }
}