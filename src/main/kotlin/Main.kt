import kotlin.math.roundToInt
fun main() {
    while (true) {
        println("Выбери задачу:")
        println("1 - Сжать строку")
        println("2 - Посчитать символы")
        println("3 - В двоичную")
        println("4 - Калькулятор")
        println("5 - Найти степень")
        println("6 - Нечетное число")
        println("0 - Выход")
        print("Задача: ")

        when (readln()) {
            "1" -> {
                println("Введи строку:")
                val userString = readln()
                if (userString.isNotEmpty()) println(compress(userString))
            }
            "2" -> {
                println("Введи строку:")
                val userString = readln()
                val map = mutableMapOf<Char, Int>()
                userString.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
                map.entries.sortedBy { it.key }.forEach { println("${it.key} - ${it.value}") }
            }
            "3" -> {
                println("Введи число:")
                val userNumber = readln().toIntOrNull() ?: 0
                if (userNumber > 0) println(userNumber.toString(2))
            }
            "4" -> {
                println("Введи: число1 число2 операция")
                val parts = readln().split(" ")
                if (parts.size == 3) {
                    val a = parts[0].toDoubleOrNull()
                    val b = parts[1].toDoubleOrNull()
                    val operation = parts[2]
                    if (a != null && b != null) {
                        val res = when (operation) {
                            "+" -> (a + b).toInt()
                            "-" -> (a - b).toInt()
                            "*" -> (a * b).toInt()
                            "/" -> if (b != 0.0) (a / b).toInt() else "Делить на ноль нельзя"
                            else -> "Не та операция"
                        }
                        println(res)
                    }
                }
            }
            "5" -> {
                println("Введи n и x:")
                val parts = readln().split(" ")
                if (parts.size == 2) {
                    val n = parts[0].toIntOrNull()
                    val x = parts[1].toIntOrNull()
                    if (n != null && x != null && n > 0 && x > 1) {
                        var p = 1
                        var y = 0
                        while (p < n) {
                            p *= x
                            y++
                        }
                        println(if (p == n) y else "Нет такого показателя")
                    }
                }
            }
            "6" -> {
                println("Введи первую цифру:")
                val a = readln().toIntOrNull()
                println("Введи вторую цифру:")
                val b = readln().toIntOrNull()
                if (a != null && b != null && a in 0..9 && b in 0..9 && a != b) {
                    val n1 = a * 10 + b
                    val n2 = b * 10 + a
                    when {
                        n1 % 2 != 0 -> println(n1)
                        n2 % 2 != 0 -> println(n2)
                        else -> println("Нечетное нельзя")
                    }
                }
            }
            "0" -> return
        }
        println()
    }
}

fun compress(stringUser: String): String {
    if (stringUser.isEmpty()) return ""
    val r = StringBuilder()
    var c = stringUser[0]
    var n = 1

    for (i in 1 until stringUser.length) {
        if (stringUser[i] == c) n++ else {
            r.append(c)
            if (n > 1) r.append(n)
            c = stringUser[i]
            n = 1
        }
    }
    r.append(c)
    if (n > 1) r.append(n)
    return r.toString()
}