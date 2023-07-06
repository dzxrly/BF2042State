package com.eggtargaryen.bf2042state.utils

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.eggtargaryen.bf2042state.R
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

fun numberFormat(number: Long): String {
    // convert Long to Double
    val numberDouble = number.toDouble()
    return if (numberDouble < 1000) {
        numberDouble.toString()
    } else if (numberDouble < 1000000) {
        // keep 2 digits after decimal point
        (numberDouble / 10000).toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP)
            .toString() + "万"
    } else if (numberDouble < 100000000) {
        (numberDouble / 1000000).toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP)
            .toString() + "百万"
    } else {
        (numberDouble / 100000000).toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP)
            .toString() + "亿"
    }
}

fun getRealKills(totalKills: Long, humanPrecentage: String): Long {
    // humanPrecentage is like "89.5%" or "89.5", convert to 0.895
    val humanPrecentageDouble = humanPrecentage.replace("%", "").toDouble() / 100
    return (totalKills * humanPrecentageDouble).toLong()
}

@Keep
@Composable
fun characterNameENGToCHN(name: String): String {
    return when (name) {
        "Mackay" -> stringResource(id = R.string.character_name_translation_Mackay)
        "Irish" -> stringResource(id = R.string.character_name_translation_Irish)
        "Sundance" -> stringResource(id = R.string.character_name_translation_Sundance)
        "Dozer" -> stringResource(id = R.string.character_name_translation_Dozer)
        "Paik" -> stringResource(id = R.string.character_name_translation_Paik)
        "Falck" -> stringResource(id = R.string.character_name_translation_Falck)
        "Angel" -> stringResource(id = R.string.character_name_translation_Angel)
        "Boris" -> stringResource(id = R.string.character_name_translation_Boris)
        "Casper" -> stringResource(id = R.string.character_name_translation_Casper)
        "Rao" -> stringResource(id = R.string.character_name_translation_Rao)
        "Lis" -> stringResource(id = R.string.character_name_translation_Lis)
        "Crawford" -> stringResource(id = R.string.character_name_translation_Crawford)
        "Zain" -> stringResource(id = R.string.character_name_translation_Zain)
        "Blasco" -> stringResource(id = R.string.character_name_translation_Blasco)
        else -> name
    }
}

@Keep
@Composable
fun classesNameENGToCHN(name: String): String {
    return when (name) {
        "Assault" -> stringResource(id = R.string.classes_name_translation_Assault)
        "Engineer" -> stringResource(id = R.string.classes_name_translation_Engineer)
        "Support" -> stringResource(id = R.string.classes_name_translation_Support)
        "Recon" -> stringResource(id = R.string.classes_name_translation_Recon)
        else -> name
    }
}
