fun main() {
    println("=== ПРОГРАММА ДЛЯ РАБОТЫ С МАССИВАМИ И ШИФРОВАНИЯ ===")

    while (true) {
        println("\nВыберите задачу:")
        println("1 - Подсчет различных цифр в массиве")
        println("2 - Проверка симметрии массива")
        println("3 - Шифровка/дешифровка текста")
        println("4 - Пересечение массивов")
        println("5 - Группировка слов по буквам")
        println("0 - Выход")
        print("Ваш выбор: ")

        when (readLine()!!.toInt()) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> task4()
            5 -> task5()
            0 -> {
                println("Выход из программы...")
                return
            }
            else -> println("Неверный выбор, попробуйте снова")
        }
    }
}

// Задача 1: Подсчет различных цифр в массиве
fun task1() {
    println("\n=== ПОДСЧЕТ РАЗЛИЧНЫХ ЦИФР В МАССИВЕ ===")

    print("Введите количество строк: ")
    val rows = readLine()!!.toInt()
    print("Введите количество столбцов: ")
    val cols = readLine()!!.toInt()

    val matrix = Array(rows) { IntArray(cols) }
    println("Введите $rows строк по $cols трехзначных чисел:")

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            matrix[i][j] = readLine()!!.toInt()
        }
    }

    val uniqueDigits = countUniqueDigits(matrix)

    println("\nВведенный массив:")
    printMatrix(matrix)
    println("\nВ массиве использовано $uniqueDigits различных цифр")
}

// Задача 2: Проверка симметрии массива
fun task2() {
    println("\n=== ПРОВЕРКА СИММЕТРИИ МАССИВА ===")
    println("Массив должен быть квадратным (5x5)")

    val size = 5
    val matrix = Array(size) { IntArray(size) }

    println("Введите элементы массива 5x5:")
    for (i in 0 until size) {
        for (j in 0 until size) {
            print("Элемент [$i][$j]: ")
            matrix[i][j] = readLine()!!.toInt()
        }
    }

    val isSymmetric = isMatrixSymmetric(matrix)

    println("\nВведенный массив:")
    printMatrix(matrix)

    if (isSymmetric) {
        println("\n✓ Массив симметричен относительно главной диагонали")
    } else {
        println("\n✗ Массив НЕ симметричен относительно главной диагонали")
    }
}

// Задача 3: Шифровка/дешифровка текста
fun task3() {
    println("\n=== ШИФРОВКА И ДЕШИФРОВКА ТЕКСТА ===")

    // Создаем алфавит и его нумерацию (пример из задания)
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
    val numbering = listOf(21,13,4,20,22,1,25,12,24,14,2,28,9,23,3,29,6,16,15,11,26,5,30,27,8,18,10,33,31,32,19,7,17)

    // Создаем отображение буква -> номер и номер -> буква
    val charToNum = mutableMapOf<Char, Int>()
    val numToChar = mutableMapOf<Int, Char>()

    for (i in alphabet.indices) {
        charToNum[alphabet[i]] = numbering[i]
        numToChar[numbering[i]] = alphabet[i]
    }

    print("Выберите действие (1 - шифровать, 2 - расшифровать): ")
    val action = readLine()!!.toInt()

    print("Введите ключевое слово: ")
    val key = readLine()!!.uppercase()

    print("Введите текст: ")
    val text = readLine()!!.uppercase()

    val result = when (action) {
        1 -> encrypt(text, key, charToNum, numToChar, alphabet.length)
        2 -> decrypt(text, key, charToNum, numToChar, alphabet.length)
        else -> {
            println("Неверное действие")
            return
        }
    }

    println("Результат: $result")
}

// Задача 4: Пересечение массивов
fun task4() {
    println("\n=== ПЕРЕСЕЧЕНИЕ МАССИВОВ ===")

    println("Введите первый массив чисел через пробел:")
    val arr1 = readLine()!!.split(" ").map { it.toInt() }

    println("Введите второй массив чисел через пробел:")
    val arr2 = readLine()!!.split(" ").map { it.toInt() }

    val intersection = findIntersection(arr1, arr2)

    println("Первый массив: ${arr1.joinToString()}")
    println("Второй массив: ${arr2.joinToString()}")
    println("Пересечение: ${intersection.joinToString()}")
}

// Задача 5: Группировка слов по буквам
fun task5() {
    println("\n=== ГРУППИРОВКА СЛОВ ПО БУКВАМ ===")

    println("Введите слова через пробел:")
    val words = readLine()!!.split(" ").map { it.trim() }

    val groups = groupWordsByLetters(words)

    println("\nГруппы слов:")
    groups.forEachIndexed { index, group ->
        println("Группа ${index + 1}: ${group.joinToString(", ")}")
    }
}

// Вспомогательные функции для задачи 1
fun countUniqueDigits(matrix: Array<IntArray>): Int {
    val digits = mutableSetOf<Char>()

    for (row in matrix) {
        for (number in row) {
            number.toString().forEach { digit ->
                digits.add(digit)
            }
        }
    }

    return digits.size
}

// Вспомогательные функции для задачи 2
fun isMatrixSymmetric(matrix: Array<IntArray>): Boolean {
    val size = matrix.size

    if (matrix.any { it.size != size }) {
        println("Ошибка: массив должен быть квадратным!")
        return false
    }

    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (matrix[i][j] != matrix[j][i]) {
                return false
            }
        }
    }

    return true
}

// Вспомогательные функции для задачи 3
fun encrypt(text: String, key: String, charToNum: Map<Char, Int>, numToChar: Map<Int, Char>, alphabetSize: Int): String {
    val result = StringBuilder()

    for (i in text.indices) {
        val textChar = text[i]
        val keyChar = key[i % key.length]

        if (textChar !in charToNum) {
            result.append(textChar)
            continue
        }

        val textNum = charToNum[textChar]!!
        val keyNum = charToNum[keyChar]!!

        // Шифрование: (номер_буквы + номер_ключа - 1) % размер_алфавита + 1
        val encryptedNum = (textNum + keyNum - 1 - 1) % alphabetSize + 1
        result.append(numToChar[encryptedNum])
    }

    return result.toString()
}

fun decrypt(text: String, key: String, charToNum: Map<Char, Int>, numToChar: Map<Int, Char>, alphabetSize: Int): String {
    val result = StringBuilder()

    for (i in text.indices) {
        val textChar = text[i]
        val keyChar = key[i % key.length]

        if (textChar !in charToNum) {
            result.append(textChar)
            continue
        }

        val textNum = charToNum[textChar]!!
        val keyNum = charToNum[keyChar]!!

        // Дешифрование: (номер_буквы - номер_ключа + размер_алфавита - 1) % размер_алфавита + 1
        val decryptedNum = (textNum - keyNum + alphabetSize - 1) % alphabetSize + 1
        result.append(numToChar[decryptedNum])
    }

    return result.toString()
}

// Вспомогательные функции для задачи 4
fun findIntersection(arr1: List<Int>, arr2: List<Int>): List<Int> {
    val frequency1 = arr1.groupingBy { it }.eachCount()
    val frequency2 = arr2.groupingBy { it }.eachCount()
    val result = mutableListOf<Int>()

    for ((num, count1) in frequency1) {
        val count2 = frequency2[num] ?: 0
        val minCount = minOf(count1, count2)
        repeat(minCount) { result.add(num) }
    }

    return result.sorted()
}

// Вспомогательные функции для задачи 5
fun groupWordsByLetters(words: List<String>): List<List<String>> {
    val groups = mutableMapOf<String, MutableList<String>>()

    for (word in words) {
        val sortedLetters = word.toCharArray().sorted().joinToString("")
        groups.getOrPut(sortedLetters) { mutableListOf() }.add(word)
    }

    return groups.values.toList()
}

// Общая функция для вывода массива
fun printMatrix(matrix: Array<IntArray>) {
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            print("${matrix[i][j]}\t")
        }
        println()
    }
}