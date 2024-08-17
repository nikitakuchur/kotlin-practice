package koans.introduction.namedarguments

fun joinOptions(options: Collection<String>) =
    options.joinToString(prefix = "[", separator = ", ", postfix = "]")
