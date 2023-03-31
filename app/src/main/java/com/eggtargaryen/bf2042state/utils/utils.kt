package com.eggtargaryen.bf2042state.utils

import java.math.BigDecimal


fun secondsToHours(seconds: Long, fixedDigits: Int = 2): Double {
    return if (seconds != -1L) {
        (seconds.toDouble() / 3600.0).toBigDecimal()
            .setScale(fixedDigits, BigDecimal.ROUND_HALF_UP).toDouble()
    } else -1.0
}

fun getYear(): String {
    val year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
    return year.toString()
}

fun versionCompare(versionCurrent: String, versionNext: String): Boolean {
    // compare version name between a and b (like 1.0.0 and 1.0.1)
    // return true if a < b
    val versionA = versionCurrent.split(".")
    val versionB = versionNext.split(".")
    // versionA from ["1", "0", "0"] to 100
    val versionANumber = versionA[0].toInt() * 100 + versionA[1].toInt() * 10 + versionA[2].toInt()
    // versionB from ["1", "0", "1"] to 101
    val versionBNumber = versionB[0].toInt() * 100 + versionB[1].toInt() * 10 + versionB[2].toInt()
    return versionANumber < versionBNumber
}
