package com.eggtargaryen.bf2042state.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpdateInfoViewModel : ViewModel() {
    private val _updateInfo = MutableLiveData<GiteeRelease>()
    val updateInfo: LiveData<GiteeRelease> = _updateInfo

    fun postUpdateInfo(updateInfo: GiteeRelease) {
        _updateInfo.postValue(updateInfo)
    }

    fun getUpdateInfo(): GiteeRelease? {
        return _updateInfo.value
    }
}
