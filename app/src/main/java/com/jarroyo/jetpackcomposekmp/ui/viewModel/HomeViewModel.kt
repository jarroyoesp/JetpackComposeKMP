package com.jarroyo.jetpackcomposekmp.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarroyo.sharedcodeclient.di.InjectorCommon
import com.jarroyo.sharedcodeclient.di.KodeinInjector
import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.usecase.GetAnimalListUsecase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
) : ViewModel() {

    private var _randomNumber: MutableLiveData<Int> = MutableLiveData()
    val randomNumber: LiveData<Int> get() = _randomNumber

    private val getAnimalListUsecase: GetAnimalListUsecase = InjectorCommon.provideGetAnimalListUsecase()

    fun getRandomNumnber() {
        viewModelScope.launch {
            val response = getAnimalListUsecase.execute()
            if (response is Response.Success) {
                _randomNumber.postValue(response.data)
            }
        }
    }
}