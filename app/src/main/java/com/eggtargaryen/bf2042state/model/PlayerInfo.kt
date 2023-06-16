package com.eggtargaryen.bf2042state.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PlayerInfo(
    val accuracy: String? = "",
    val avatar: String? = "",
    val bestClass: String? = "",
    val bestSquad: Long? = 0L,
    val callIns: Long? = 0L,
    var classes: List<ClassElement>? = null,
    val damage: Long? = 0L,
    val damagePerMatch: Double? = 0.0,
    val damagePerMinute: Double? = 0.0,
    val deaths: Long? = 0L,
    val devidedAssists: DevidedAssists? = null,
    val devidedDamage: DevidedDamage? = null,
    val distanceTraveled: DistanceTraveled? = null,
    val dividedKills: DividedKills? = null,
    val dividedSecondsPlayed: DividedSecondsPlayed? = null,
    val enemiesSpotted: Long? = 0L,
    var gadgets: List<Gadget>? = null,
    val gadgetsDestoyed: Long? = 0L,
    var gamemodes: List<Gamemode>? = null,
    val headShots: Long? = 0L,
    val headshots: String? = "",
    val heals: Long? = 0L,
    val humanPrecentage: String? = "",
    val id: Long? = 0L,
    val infantryKillDeath: Double? = 0.0,
    val inRound: PlayerInfoInRound? = null,
    val killAssists: Long? = 0L,
    val killDeath: Double? = 0.0,
    val kills: Long? = 0L,
    val killsPerMatch: Double? = 0.0,
    val killsPerMinute: Double? = 0.0,
    val loses: Long? = 0L,
    var maps: List<MapElement>? = null,
    val matchesPlayed: Long? = 0L,
    val mvp: Long? = 0L,
    val objective: PlayerInfoObjective? = null,
    val playerTakeDowns: Long? = 0L,
    val repairs: Long? = 0L,
    val resupplies: Long? = 0L,
    val revives: Long? = 0L,
    val saviorKills: Long? = 0L,
    val seasons: Seasons? = null,
    val secondsPlayed: Long? = 0L,
    val sector: Sector? = null,
    val shotsFired: Long? = 0L,
    val shotsHit: Long? = 0L,
    val squadmateRespawn: Long? = 0L,
    val squadmateRevive: Long? = 0L,
    val teammatesSupported: Long? = 0L,
    val thrownThrowables: Long? = 0L,
    val timePlayed: String? = "",

    val userId: Long,
    val userName: String,

    val vehicleGroups: List<VehicleGroup>? = null,
    var vehicles: List<Vehicle>? = null,
    val vehiclesDestroyed: Long? = 0L,
    val weaponGroups: List<WeaponGroup>? = null,
    var weapons: List<Weapon>? = null,
    val winPercent: String? = "",
    val wins: Long? = 0L,

    @Json(name = "XP")
    val xp: List<XP>? = null
)

@Keep
@JsonClass(generateAdapter = true)
data class ClassElement(
    val assists: Long? = 0L,
    val avatarImage: String? = "",
    val avatarImages: AvatarImages? = null,
    val characterName: String? = "",
    val className: String? = "",
    val deaths: Long? = 0L,
    val hazardZoneStreaks: Long? = 0L,
    val id: String? = "",
    val image: String? = "",
    val killDeath: Double? = 0.0,
    val kills: Long? = 0L,
    val kpm: Double? = 0.0,
    val revives: Long? = 0L,
    val secondsPlayed: Long? = 0L,
    val spawns: Long? = 0L,
    val statName: String? = ""
)

@Keep
@JsonClass(generateAdapter = true)
data class AvatarImages(
    val ger: String? = "",
    val rus: String? = "",
    val uk: String? = "",
    val us: String? = ""
)

@Keep
@JsonClass(generateAdapter = true)
data class DevidedAssists(
    val ai: Long? = 0L,
    val driver: Long? = 0L,
    val inRound: DevidedAssistsInRound? = null,
    val passenger: Long? = 0L,
    val pilot: Long? = 0L,
    val spot: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class DevidedAssistsInRound(
    val passenger: Long? = 0L,
    val pilot: Long? = 0L,
    val total: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class DevidedDamage(
    val explosion: Long? = 0L,
    val inRound: DevidedDamageInRound? = null,
    val passenger: Long? = 0L,
    val toVehicle: Long? = 0L,
    val vehicleDriver: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class DevidedDamageInRound(
    val asVehicle: Long? = 0L,
    val explosion: Long? = 0L,
    val passenger: Long? = 0L,
    val toVehicle: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class DistanceTraveled(
    val foot: Long? = 0L,
    val grapple: Long? = 0L,
    val passenger: Long? = 0L,
    val vehicle: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class DividedKills(
    val ads: Long? = 0L,
    val ai: Long? = 0L,
    val grenades: Long? = 0L,
    val hipfire: Long? = 0L,
    val human: Long? = 0L,
    val inRound: DividedKillsInRound? = null,
    val longDistance: Long? = 0L,
    val melee: Long? = 0L,
    val multiKills: Long? = 0L,
    val parachute: Long? = 0L,
    val passenger: Long? = 0L,
    val ranger: Long? = 0L,
    val roadkills: Long? = 0L,
    val turret: Long? = 0L,
    val vehicle: Long? = 0L,
    val weapons: DividedKillsWeapons? = null
)

@Keep
@JsonClass(generateAdapter = true)
data class DividedKillsInRound(
    val drone: Long? = 0L,
    val grenade: Long? = 0L,
    val headshots: Long? = 0L,
    val hipfire: Long? = 0L,
    val killsAndAssists: Long? = 0L,
    val longDistance: Long? = 0L,
    val melee: Long? = 0L,
    val multiKills: Long? = 0L,
    val parachute: Long? = 0L,
    val passenger: Long? = 0L,
    val total: Long? = 0L,
    val turret: Long? = 0L,
    val weapons: InRoundWeapons? = null
)

@Keep
@JsonClass(generateAdapter = true)
data class InRoundWeapons(
    @Json(name = "AR")
    val ar: Long? = 0L,

    @Json(name = "BAR")
    val bar: Long? = 0L,

    @Json(name = "DMR")
    val dmr: Long? = 0L,

    @Json(name = "Sidearm")
    val sidearm: Long? = 0L,

    @Json(name = "SMG")
    val smg: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class DividedKillsWeapons(
    @Json(name = "Assault Rifles")
    val assaultRifles: Long? = 0L,

    @Json(name = "BAR")
    val bar: Long? = 0L,

    @Json(name = "Crossbows")
    val crossbows: Long? = 0L,

    @Json(name = "DMR")
    val dmr: Long? = 0L,

    @Json(name = "LMG")
    val lmg: Long? = 0L,

    @Json(name = "Shotguns")
    val shotguns: Long? = 0L,

    @Json(name = "Sidearm")
    val sidearm: Long? = 0L,

    @Json(name = "SMG")
    val smg: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class DividedSecondsPlayed(
    val driving: Long? = 0L,
    val flying: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Gadget(
    val damage: Long? = 0L,
    val dpm: Double? = 0.0,
    val gadgetName: String? = "",
    val id: String? = "",
    val image: String? = "",
    val kills: Long? = 0L,
    val kpm: Double? = 0.0,
    val multiKills: Long? = 0L,
    val secondsPlayed: Long? = 0L,
    val spawns: Long? = 0L,
    val type: String? = "",
    val uses: Long? = 0L,
    val vehiclesDestroyedWith: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Gamemode(
    val assists: Long? = 0L,
    val bestSquad: Long? = 0L,
    val gamemodeName: String? = "",
    val id: String? = "",
    val image: String? = "",
    val kills: Long? = 0L,
    val kpm: Double? = 0.0,
    val losses: Long? = 0L,
    val matches: Long? = 0L,
    val mvp: Long? = 0L,
    val objectivesArmed: Long? = 0L,
    val objectivesCaptured: Long? = 0L,
    val objectivesDefended: Long? = 0L,
    val objectivesDestroyed: Long? = 0L,
    val objectivesDisarmed: Long? = 0L,
    val objetiveTime: Long? = 0L,
    val revives: Long? = 0L,
    val secondsPlayed: Long? = 0L,
    val sectorDefend: Long? = 0L,
    val winPercent: String? = "",
    val wins: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class PlayerInfoInRound(
    val gadgetsDestoyed: Long? = 0L,
    val playerTakeDowns: Long? = 0L,
    val resupplies: Long? = 0L,
    val revives: Long? = 0L,
    val spotAssists: Long? = 0L,
    val squadmateRevive: Long? = 0L,
    val thrownThrowables: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class MapElement(
    val id: String? = "",
    val image: String? = "",
    val losses: Long? = 0L,
    val mapName: String? = "",
    val matches: Long? = 0L,
    val secondsPlayed: Long? = 0L,
    val winPercent: String? = "",
    val wins: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class PlayerInfoObjective(
    val armed: Long? = 0L,
    val captured: Long? = 0L,
    val defused: Long? = 0L,
    val destroyed: Long? = 0L,
    val inRound: ObjectiveInRound? = null,
    val neutralized: Long? = 0L,
    val time: Time? = null
)

@Keep
@JsonClass(generateAdapter = true)
data class ObjectiveInRound(
    val armed: Long? = 0L,
    val captured: Long? = 0L,
    val defused: Long? = 0L,
    val destroyed: Long? = 0L,
    val neutralized: Long? = 0L,
    val time: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Time(
    val attacked: Long? = 0L,
    val defended: Long? = 0L,
    val total: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Seasons(
    @Json(name = "1")
    val the1: The1? = null
)

@Keep
@JsonClass(generateAdapter = true)
data class The1(
    val assists: Long? = 0L,
    val deaths: Long? = 0L,
    val enemiesSpotted: Long? = 0L,
    val headshots: Long? = 0L,
    val heals: Long? = 0L,
    val intelExtracted: Long? = 0L,
    val kills: Long? = 0L,
    val loses: Long? = 0L,
    val missionsCompleted: Long? = 0L,
    val objective: The1_Objective? = null,
    val resupplies: Long? = 0L,
    val revives: Long? = 0L,
    val ribbons: Long? = 0L,
    val roundsPlayed: Long? = 0L,
    val timesExtracted: Long? = 0L,
    val vehiclesDestroyed: Long? = 0L,
    val vehiclesHPRepaired: Long? = 0L,
    val wins: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class The1_Objective(
    val armed: Long? = 0L,
    val captured: Long? = 0L,
    val defused: Long? = 0L,
    val destroyed: Long? = 0L,
    val neutralized: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Sector(
    val captured: Long? = 0L,
    val defended: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class VehicleGroup(
    val assists: Long? = 0L,
    val callIns: Long? = 0L,
    val damage: Long? = 0L,
    val damageTo: Long? = 0L,
    val destroyed: Long? = 0L,
    val distanceTraveled: Long? = 0L,
    val driverAssists: Long? = 0L,
    val groupName: String? = "",
    val id: String? = "",
    val kills: Long? = 0L,
    val killsPerMinute: Double? = 0.0,
    val multiKills: Long? = 0L,
    val passengerAssists: Long? = 0L,
    val roadKills: Long? = 0L,
    val spawns: Long? = 0L,
    val timeIn: Long? = 0L,
    val vehiclesDestroyedWith: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Vehicle(
    val assists: Long? = 0L,
    val callIns: Long? = 0L,
    val damage: Long? = 0L,
    val damageTo: Long? = 0L,
    val destroyed: Long? = 0L,
    val distanceTraveled: Long? = 0L,
    val driverAssists: Long? = 0L,
    val id: String? = "",
    val image: String? = "",
    val kills: Long? = 0L,
    val killsPerMinute: Double? = 0.0,
    val multiKills: Long? = 0L,
    val passengerAssists: Long? = 0L,
    val roadKills: Long? = 0L,
    val spawns: Long? = 0L,
    val timeIn: Long? = 0L,
    val type: String? = "",
    val vehicleName: String? = "",
    val vehiclesDestroyedWith: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class WeaponGroup(
    val accuracy: String? = "",
    val bodyKills: Long? = 0L,
    val damage: Long? = 0L,
    val damagePerMinute: Double? = 0.0,
    val groupName: String? = "",
    val headshotKills: Long? = 0L,
    val headshots: String? = "",
    val hipfireKills: Long? = 0L,
    val hitVKills: Double? = 0.0,
    val id: String? = "",
    val kills: Long? = 0L,
    val killsPerMinute: Double? = 0.0,
    val multiKills: Long? = 0L,
    val shotsFired: Long? = 0L,
    val shotsHit: Long? = 0L,
    val spawns: Long? = 0L,
    val timeEquipped: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Weapon(
    val accuracy: String? = "",
    val bodyKills: Long? = 0L,
    val damage: Long? = 0L,
    val damagePerMinute: Double? = 0.0,
    val headshotKills: Long? = 0L,
    val headshots: String? = "",
    val hipfireKills: Long? = 0L,
    val hitVKills: Double? = 0.0,
    val id: String? = "",
    val image: String? = "",
    val kills: Long? = 0L,
    val killsPerMinute: Double? = 0.0,
    val multiKills: Long? = 0L,
    val shotsFired: Long? = 0L,
    val shotsHit: Long? = 0L,
    val spawns: Long? = 0L,
    val timeEquipped: Long? = 0L,
    val type: String? = "",
    val weaponName: String? = ""
)

@Keep
@JsonClass(generateAdapter = true)
data class XP(
    val performance: Long? = 0L,
    val ribbons: Ribbons? = null,
    val total: Long? = 0L
)

@Keep
@JsonClass(generateAdapter = true)
data class Ribbons(
    val combat: Long? = 0L,
    val intel: Long? = 0L,
    val objective: Long? = 0L,
    val squad: Long? = 0L,
    val support: Long? = 0L,
    val total: Long? = 0L
)