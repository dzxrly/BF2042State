package com.eggtargaryen.bf2042state.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerInfoViewModel : ViewModel() {
    private val _playerInfo = MutableLiveData<PlayerInfo>()
    val playerInfo: LiveData<PlayerInfo> = _playerInfo

    fun setPlayerInfo(playerInfo: PlayerInfo) {
        _playerInfo.value = playerInfo
    }

    fun postPlayerInfo(playerInfo: PlayerInfo) {
        _playerInfo.postValue(playerInfo)
    }

    fun getPlayerInfo(): PlayerInfo? {
        return _playerInfo.value
    }
}
