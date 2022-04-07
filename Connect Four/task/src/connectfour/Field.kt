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

    fun putCoin(column: Int, symbol: Char) {
        for (i in rows - 1 downTo 0) {
            if (field[i][column - 1] == ' ') {
                field[i][column - 1] = symbol
                break
            }
        }
    }
}