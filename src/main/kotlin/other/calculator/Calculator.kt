package other.calculator

val operators = mapOf(
    '+' to 1,
    '-' to 1,
    '*' to 2,
    '/' to 2,
)

fun eval(expression: String): Int {
    val rpn = toRPN(expression)
    val stack = ArrayDeque<Int>()

    for (token in rpn) {
        when (token) {
            "+" -> {
                val result = stack.removeFirst() + stack.removeFirst()
                stack.addFirst(result)
            }
            "-" -> {
                val a = stack.removeFirst()
                val b = stack.removeFirst()
                stack.addFirst(b - a)
            }
            "*" -> {
                val result = stack.removeFirst() * stack.removeFirst()
                stack.addFirst(result)
            }
            "/" -> {
                val a = stack.removeFirst()
                val b = stack.removeFirst()
                stack.addFirst(b / a)
            }
            else -> stack.addFirst(token.toInt())
        }
    }

    require(stack.size == 1) {
        "Incorrect expression"
    }

    return stack.first()
}

fun toRPN(expression: String): List<String> {
    val output = mutableListOf<String>()
    val stack = ArrayDeque<Char>()

    var firstDigit = 0
    for (i in expression.indices) {
        val char = expression[i]

        if (char.isDigit()) {
            if (i == expression.length - 1 || !expression[i + 1].isDigit()) {
                val num = expression.substring(firstDigit, i + 1)
                output.add(num)
            }
            continue
        }
        firstDigit = i + 1

        if (char.isWhitespace()) {
            continue
        }

        if (char == '(') {
            stack.addFirst(char)
            continue
        }

        if (char == ')') {
            while (stack.first() != '(') {
                val operation = stack.removeFirstOrNull()
                require(operation != null) {
                    "Incorrect expression"
                }
                output.add(operation.toString())
            }
            stack.removeFirstOrNull()
            continue
        }

        require(char in operators.keys) {
            "The given expression contains an unsupported operation"
        }

        while (stack.isNotEmpty()) {
            val prevOperation = stack.firstOrNull()
            val prevPrecedence = prevOperation?.let { operators[it] } ?: 0
            if (prevPrecedence < operators[char]!!) {
                break
            }
            output.addLast(stack.removeFirst().toString())
        }
        stack.addFirst(char)
    }

    while (stack.isNotEmpty()) {
        output.add(stack.removeFirst().toString())
    }

    return output.toList()
}

fun main() {
    println(eval("82 - 3 * 15 + 6 + 28") == 82 - 3 * 15 + 6 + 28)
    println(eval("(5 + 4 * 3 ) / 2") == (5 + 4 * 3) / 2)
    println(eval("(65 - 13 * 1 / 31) / 3 * 5") == (65 - 13 * 1 / 31) / 3 * 5)
    println(eval("((65 - 13) * 3 / (31 - 43)) / 3 * (5 + 4)") == ((65 - 13) * 3 / (31 - 43)) / 3 * (5 + 4))
}