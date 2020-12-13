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
import com.jarroyo.sharedcodeclient.domain.usecase.GetAnimalListUsecaseFlow
import kotlinx.coroutines.launch
import com.jarroyo.sharedcodeclient.domain.model.Breed
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

class HomeViewModel @ViewModelInject constructor(
) : ViewModel() {

    private var _randomNumber: MutableLiveData<Int> = MutableLiveData()
    val randomNumber: LiveData<Int> get() = _randomNumber

    private var _animalListLiveData: MutableLiveData<List<Breed>?> = MutableLiveData()
    val animalListLiveData: LiveData<List<Breed>?> get() = _animalListLiveData

    private val getAnimalListUsecase: GetAnimalListUsecase = InjectorCommon.provideGetAnimalListUsecase()
    private val getAnimalListUsecaseFlow: GetAnimalListUsecaseFlow = InjectorCommon.provideGetAnimalListUsecaseFlow()

    fun getRandomNumnber() {
        viewModelScope.launch {
                _randomNumber.postValue(1234)
            }
    }

    fun getAnimalList() {
        viewModelScope.launch {
            val response = getAnimalListUsecase.execute()
            if (response is Response.Success) {
                _animalListLiveData.postValue(response.data)
            }
        }
    }

    /**
     * Get Breed List Flow
     */
    fun getAnimalListFlow() = viewModelScope.launch {
        val response = getAnimalListUsecaseFlow.execute()
        response.collect {
            if (it is Response.Success) {
                _animalListLiveData.postValue(it.data)
            }
        }
    }
}