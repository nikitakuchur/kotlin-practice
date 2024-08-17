package koans.conventions.operatorsoverloading

import koans.conventions.operatorsoverloading.TimeInterval.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

// Supported intervals that might be added to dates:
enum class TimeInterval { DAY, WEEK, YEAR }

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)

class RepeatedTimeInterval(val interval: TimeInterval, val amount: Int)

operator fun TimeInterval.times(amount: Int) = RepeatedTimeInterval(this, amount)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = with(timeIntervals) {
    addTimeIntervals(interval, amount)
}

fun task1(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

fun task2(today: MyDate): MyDate {
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
}
