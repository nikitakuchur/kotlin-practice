package koans.introduction.lambdas

fun containsEven(collection: Collection<Int>): Boolean =
    collection.any { item -> item % 2 == 0 }
