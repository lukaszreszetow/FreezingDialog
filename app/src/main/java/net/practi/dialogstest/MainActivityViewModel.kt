package net.practi.dialogstest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _isLoadingLive = MutableLiveData(false)
    val isLoadingLive: LiveData<Boolean> = _isLoadingLive

    private val _startActivity = MutableLiveData(false)
    val startActivity: LiveData<Boolean> = _startActivity

    fun start() = viewModelScope.launch {
        Log.d("Testing", "ViewModel - show loading")
        _isLoadingLive.postValue(true)
        delay(2000)
        Log.d("Testing", "ViewModel - start Dialog Activity")
        _startActivity.postValue(true)
        delay(2000)
        Log.d("Testing", "ViewModel - hide loading")
        _isLoadingLive.postValue(false)
    }
}