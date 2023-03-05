package com.eggtargaryen.bf2042state.utils

import java.math.BigDecimal


fun secondsToHours(seconds: Long, fixedDigits: Int = 2): Double {
    return if (seconds != -1L) {
        (seconds.toDouble() / 3600.0).toBigDecimal()
            .setScale(fixedDigits, BigDecimal.ROUND_HALF_UP).toDouble()
    } else -1.0
}
