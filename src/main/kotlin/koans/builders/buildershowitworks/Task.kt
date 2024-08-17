package koans.builders.buildershowitworks

import koans.builders.buildershowitworks.Answer.*

enum class Answer { a, b, c }

val answers = mapOf<Int, Answer?>(
    1 to c, 2 to b, 3 to b, 4 to c
)
