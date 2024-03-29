package com.eggtargaryen.bf2042state.utils

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.eggtargaryen.bf2042state.R
import java.math.BigDecimal


fun secondsToHours(seconds: Long, fixedDigits: Int = 2): Double {
    return if (seconds != -1L) {
        (seconds.toDouble() / 3600.0).toBigDecimal().setScale(fixedDigits, BigDecimal.ROUND_HALF_UP)
            .toDouble()
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

fun getPlayerNameAndPlatformFromPlayerQueryStr(
    playerQueryStr: String,
    spliter: String = "$@$"
): Pair<String, String> {
    // check if playerQueryStr is like "playerName$@$platform"
    if (playerQueryStr.split(spliter).size != 2) {
        return Pair("", "")
    }
    // playerQueryStr is like "playerName$@$platform"
    val playerName = playerQueryStr.split(spliter)[0]
    val platform = playerQueryStr.split(spliter)[1]
    return Pair(playerName, platform)
}

fun getRealKPM(totalKills: Long, humanPrecentage: String, secondsPlayed: Long): String {
    val realKills = getRealKills(totalKills, humanPrecentage)
    // check secondsPlayed is not 0
    return if (secondsPlayed != 0L) {
        // keep 2 digits after decimal point
        (realKills.toDouble() / secondsPlayed.toDouble() * 60.0).toBigDecimal()
            .setScale(2, BigDecimal.ROUND_HALF_UP).toString()
    } else {
        "0.0"
    }
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

@Keep
@Composable
fun mapNameENGToCHN(name: String): String {
    return when (name) {
        "Discarded" -> stringResource(id = R.string.map_name_translation_Discarded)
        "Hourglass" -> stringResource(id = R.string.map_name_translation_Hourglass)
        "Breakaway" -> stringResource(id = R.string.map_name_translation_Breakaway)
        "Kaleidescope" -> stringResource(id = R.string.map_name_translation_Kaleidescope)
        "Manifest" -> stringResource(id = R.string.map_name_translation_Manifest)
        "Orbital" -> stringResource(id = R.string.map_name_translation_Orbital)
        "Renewal" -> stringResource(id = R.string.map_name_translation_Renewal)
        "Battle of the Bulge" -> stringResource(id = R.string.map_name_translation_Battle_of_the_Bulge)
        "Arica Harbor" -> stringResource(id = R.string.map_name_translation_Arica_Harbor)
        "Valparaiso" -> stringResource(id = R.string.map_name_translation_Valparaiso)
        "El Alamein" -> stringResource(id = R.string.map_name_translation_El_Alamein)
        "Noshahr Canals" -> stringResource(id = R.string.map_name_translation_Noshahr_Canals)
        "Caspian Border" -> stringResource(id = R.string.map_name_translation_Caspian_Border)
        "Exposure" -> stringResource(id = R.string.map_name_translation_Exposure)
        "Stranded" -> stringResource(id = R.string.map_name_translation_Stranded)
        "Spearhead" -> stringResource(id = R.string.map_name_translation_Spearhead)
        "Flashpoint" -> stringResource(id = R.string.map_name_translation_Flashpoint)
        "Reclaimed" -> stringResource(id = R.string.map_name_translation_Reclaimed)
        else -> name
    }
}

@Keep
@Composable
fun modeENGToCHN(name: String): String {
    return when (name) {
        "Breakthrough" -> stringResource(id = R.string.mode_name_translation_Breakthrough)
        "Breakthrough Large" -> stringResource(id = R.string.mode_name_translation_Breakthrough_Large)
        "Conquest" -> stringResource(id = R.string.mode_name_translation_Conquest)
        "Conquest Large" -> stringResource(id = R.string.mode_name_translation_Conquest_Large)
        "Hazard Zone" -> stringResource(id = R.string.mode_name_translation_Hazard_Zone)
        "Hazard Zone Large" -> stringResource(id = R.string.mode_name_translation_Hazard_Zone_Large)
        "Rush" -> stringResource(id = R.string.mode_name_translation_Rush)
        "Custom" -> stringResource(id = R.string.mode_name_translation_Custom)
        else -> name
    }
}
