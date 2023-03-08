package com.eggtargaryen.bf2042state.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerInfoViewModel : ViewModel() {
    private val _playerInfo = MutableLiveData<PlayerInfo>()
    val playerInfo: LiveData<PlayerInfo> = _playerInfo

    private fun sortData(playerInfo: PlayerInfo): PlayerInfo {
        playerInfo.weapons = playerInfo.weapons.sortedByDescending { it.kills }
        playerInfo.vehicles = playerInfo.vehicles.sortedByDescending { it.kills }
        playerInfo.classes = playerInfo.classes.sortedByDescending { it.kills }
        playerInfo.gadgets = playerInfo.gadgets.sortedByDescending { it.kills }
        return playerInfo
    }

    fun postPlayerInfo(playerInfo: PlayerInfo) {
        _playerInfo.postValue(sortData(playerInfo))
    }

    fun getPlayerInfo(): PlayerInfo? {
        return _playerInfo.value
    }
}
