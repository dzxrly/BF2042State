package com.eggtargaryen.bf2042state.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerInfo(
    val accuracy: String,
    val avatar: String,
    val bestClass: String,
    val classes: List<PurpleClass>,
    val damage: Long,
    val damagePerMatch: Double,
    val damagePerMinute: Double,
    val deaths: Long,
    val devidedAssists: DevidedAssist,
    val devidedDamage: DevidedDamage,
    val distanceTraveled: DistanceTraveled,
    val dividedKills: DividedKill,
    val enemiesSpotted: Long,
    val gadgets: List<Gadget>,
    val gamemodes: List<Gamemode>,
    val headShots: Long,
    val headshots: String,
    val heals: Long,
    val humanPrecentage: String,
    val id: Long,
    val infantryKillDeath: Double,
    val inRound: InRound,
    val killAssists: Long,
    val killDeath: Double,
    val kills: Long,
    val killsPerMatch: Double,
    val killsPerMinute: Double,
    val loses: Long,
    val maps: List<PurpleMap>,
    val matchesPlayed: Long,
    val mvp: Long,
    val objective: Objective,
    val repairs: Long,
    val resupplies: Long,
    val revives: Long,
    val seasons: Season,
    val secondsPlayed: Long,
    val shotsFired: Long,
    val shotsHit: Long,
    val squadmateRespawn: Long,
    val squadmateRevive: Long,
    val thrownThrowables: Long,
    val timePlayed: String,

    @Json(name = "userId") val userID: Long,

    val userName: String,
    val vehicles: List<Vehicle>,
    val vehiclesDestroyed: Long,
    val weapons: List<Weapon>,
    val winPercent: String,
    val wins: Long,

    @Json(name = "XP") val xp: List<XP>
)

@JsonClass(generateAdapter = true)
data class Weapon(
    val accuracy: String,
    val bodyKills: Long,
    val damage: Long,
    val damagePerMinute: Double,
    val headshotKills: Long,
    val headshots: String,
    val hipfireKills: Long,
    val hitVKills: Double,
    val id: String,
    val image: String,
    val kills: Long,
    val killsPerMinute: Double,
    val multiKills: Long,
    val shotsFired: Long,
    val shotsHit: Long,
    val spawns: Long,
    val timeEquipped: Long,
    val type: String,
    val weaponName: String
)

@JsonClass(generateAdapter = true)
data class Vehicle(
    val assists: Long,
    val callIns: Long,
    val damage: Long,
    val damageTo: Long,
    val destroyed: Long,
    val distanceTraveled: Long,
    val driverAssists: Long,
    val id: String,
    val image: String,
    val kills: Long,
    val killsPerMinute: Double,
    val multiKills: Long,
    val passengerAssists: Long,
    val roadKills: Long,
    val spawns: Long,
    val timeIn: Long,
    val type: String,
    val vehicleName: String,
    val vehiclesDestroyedWith: Long
)

@JsonClass(generateAdapter = true)
data class PurpleClass(
    val assists: Long,
    val avatarImages: AvatarImages,
    val characterName: String,
    val className: String,
    val deaths: Long,
    val hazardZoneStreaks: Long,
    val id: String,
    val image: String,
    val killDeath: Double,
    val kills: Long,
    val kpm: Double,
    val revives: Long,
    val secondsPlayed: Long,
    val spawns: Long,
    val statName: String
)

@JsonClass(generateAdapter = true)
data class AvatarImages(
    val us: String
)

@JsonClass(generateAdapter = true)
data class Gamemode(
    val assists: Long,
    val bestSquad: Long,
    val gamemodeName: String,
    val id: String,
    val image: String,
    val kills: Long,
    val kpm: Double,
    val losses: Long,
    val matches: Long,
    val mvp: Long,
    val objectivesArmed: Long,
    val objectivesCaptured: Long,
    val objectivesDefended: Long,
    val objectivesDestroyed: Long,
    val objectivesDisarmed: Long,
    val objetiveTime: Long,
    val revives: Long,
    val secondsPlayed: Long,
    val sectorDefend: Long,
    val winPercent: String,
    val wins: Long
)


@JsonClass(generateAdapter = true)
data class PurpleMap(
    val id: String,
    val image: String,
    val losses: Long,
    val mapName: String,
    val matches: Long,
    val secondsPlayed: Long,
    val winPercent: String,
    val wins: Long
)


@JsonClass(generateAdapter = true)
data class Gadget(
    val damage: Long,
    val dpm: Double,
    val gadgetName: String,
    val id: String,
    val image: String,
    val kills: Long,
    val kpm: Double,
    val multiKills: Long,
    val secondsPlayed: Long,
    val spawns: Long,
    val type: String,
    val uses: Long,
    val vehiclesDestroyedWith: Long
)


@JsonClass(generateAdapter = true)
data class InRound(
    val resupplies: Long,
    val revives: Long,
    val spotAssists: Long,
    val squadmateRevive: Long,
    val thrownThrowables: Long
)


@JsonClass(generateAdapter = true)
data class DividedKill(
    val ads: Long,
    val ai: Long,
    val grenades: Long,
    val hipfire: Long,
    val human: Long,
    val inRound: DividedKillInRound,
    val longDistance: Long,
    val melee: Long,
    val multiKills: Long,
    val parachute: Long,
    val passenger: Long,
    val ranger: Long,
    val roadkills: Long,
    val turret: Long,
    val vehicle: Long,
    val weapons: DividedKillWeapons
)

@JsonClass(generateAdapter = true)
data class DividedKillInRound(
    val drone: Long,
    val grenade: Long,
    val headshots: Long,
    val hipfire: Long,
    val killsAndAssists: Long,
    val longDistance: Long,
    val melee: Long,
    val multiKills: Long,
    val parachute: Long,
    val passenger: Long,
    val total: Long,
    val turret: Long,
    val weapons: InRoundWeapons
)

@JsonClass(generateAdapter = true)
data class InRoundWeapons(
    @Json(name = "AR") val ar: Long,
    @Json(name = "BAR") val bar: Long,
    @Json(name = "DMR") val dmr: Long,
    @Json(name = "SMG") val smg: Long
)

@JsonClass(generateAdapter = true)
data class DividedKillWeapons(
    @Json(name = "BAR") val bar: Long,
    @Json(name = "DMR") val dmr: Long,
    @Json(name = "LMG") val lmg: Long,
    @Json(name = "SMG") val smg: Long
)


@JsonClass(generateAdapter = true)
data class DevidedDamage(
    val explosion: Long,
    val inRound: DevidedDamageInRound,
    val passenger: Long,
    val toVehicle: Long,
    val vehicleDriver: Long
)

@JsonClass(generateAdapter = true)
data class DevidedDamageInRound(
    val asVehicle: Long,
    val explosion: Long,
    val passenger: Long,
    val toVehicle: Long
)


@JsonClass(generateAdapter = true)
data class DevidedAssist(
    val ai: Long,
    val driver: Long,
    val inRound: DevidedAssistInRound,
    val passenger: Long,
    val pilot: Long,
    val spot: Long
)

@JsonClass(generateAdapter = true)
data class DevidedAssistInRound(
    val passenger: Long,
    val pilot: Long,
    val total: Long
)


@JsonClass(generateAdapter = true)
data class DistanceTraveled(
    val foot: Long,
    val grapple: Long,
    val passenger: Long,
    val vehicle: Long
)


@JsonClass(generateAdapter = true)
data class Objective(
    val armed: Long,
    val defused: Long,
    val inRound: ObjectiveInRound,
    val time: Long
)

@JsonClass(generateAdapter = true)
data class ObjectiveInRound(
    val armed: Long,
    val defused: Long,
    val destroyed: Long,
    val time: Long
)


@JsonClass(generateAdapter = true)
data class XP(
    val performance: Long,
    val ribbons: Ribbons,
    val total: Long
)

@JsonClass(generateAdapter = true)
data class Ribbons(
    val combat: Long,
    val intel: Long,
    val objective: Long,
    val squad: Long,
    val support: Long,
    val total: Long
)

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "1") val the1: The1
)

@JsonClass(generateAdapter = true)
data class The1(
    val assists: Long,
    val deaths: Long,
    val enemiesSpotted: Long,
    val headshots: Long,
    val heals: Long,
    val intelExtracted: Long,
    val kills: Long,
    val loses: Long,
    val missionsCompleted: Long,
    val objective: SeasonObjective,
    val resupplies: Long,
    val revives: Long,
    val ribbons: Long,
    val roundsPlayed: Long,
    val timesExtracted: Long,
    val vehiclesDestroyed: Long,
    val vehiclesHPRepaired: Long,
    val wins: Long
)

@JsonClass(generateAdapter = true)
data class SeasonObjective(
    val armed: Long,
    val captured: Long,
    val defused: Long,
    val destroyed: Long,
    val neutralized: Long
)
