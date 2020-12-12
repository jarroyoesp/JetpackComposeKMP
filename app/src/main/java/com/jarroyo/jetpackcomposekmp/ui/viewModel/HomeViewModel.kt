package com.jarroyo.jetpackcomposekmp.ui.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    //private val useCase: GetDataUseCase
) : ViewModel() {

    private var _randomNumber: MutableLiveData<Int> = MutableLiveData()
    val randomNumber: LiveData<Int> get() = _randomNumber

    fun getRandomNumnber() {
        viewModelScope.launch {
            _randomNumber.postValue(1)
        }
    }
}