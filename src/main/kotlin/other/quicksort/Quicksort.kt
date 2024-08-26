package other.quicksort

fun IntArray.quicksort(first: Int = 0, last: Int = size - 1) {
    if (first >= last) {
        return
    }

    val pivot = this[last]

    var left = first
    var right = last - 1
    while (left <= right) {
        if (this[left] <= pivot) {
            left++
            continue
        }
        if (this[right] >= pivot) {
            right--
            continue
        }

        swap(left, right)
        left++
        right--
    }

    swap(left, last)

    quicksort(first, left - 1)
    quicksort(left + 1, last)
}

private fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun main() {
    val arr = intArrayOf(54, 23, 85, 19, 23, 12, 56, 18, 40)
    println(arr.joinToString())
    arr.quicksort()
    println(arr.joinToString())
}
