package connectfour

import java.util.*

class Field {
    var rows = 6
    var columns = 7

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
            }
        }
    }
}