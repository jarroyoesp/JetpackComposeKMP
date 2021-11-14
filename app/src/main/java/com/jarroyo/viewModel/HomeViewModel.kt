package com.jarroyo.viewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarroyo.sharedcodeclient.di.Injector
import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.usecase.GetAnimalListUsecase
import com.jarroyo.sharedcodeclient.domain.usecase.GetAnimalListUsecaseFlow
import kotlinx.coroutines.launch
import com.jarroyo.sharedcodeclient.domain.model.Breed
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

class HomeViewModel @ViewModelInject constructor(
) : ViewModel() {
    companion object {
        val TAG = HomeViewModel::class.simpleName
    }

    private var _animalListLiveData: MutableLiveData<List<Breed>?> = MutableLiveData()
    private var _animalListSearchLiveData: MutableLiveData<List<Breed>?> = MutableLiveData()
    val animalListLiveData: LiveData<List<Breed>?> get() = _animalListSearchLiveData

    private val getAnimalListUsecase: GetAnimalListUsecase = Injector().getAnimalListUsecase
    private val getAnimalListUsecaseFlow: GetAnimalListUsecaseFlow = Injector().getAnimalListUsecaseFlow


    fun getAnimalList() {
        viewModelScope.launch {
            val response = getAnimalListUsecase.execute()
            if (response is Response.Success) {
                _animalListLiveData.postValue(response.data)
                _animalListSearchLiveData.postValue(response.data)
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
                _animalListSearchLiveData.postValue(it.data)
            }
        }
    }

    fun onSearchBreedText(search: String){
        Log.d(TAG, "[onSearchBreedText] search: $search")
        if (search.isNotEmpty()) {
            _animalListSearchLiveData.value =
                _animalListLiveData.value?.filter { breed -> breed.name.contains(search) }
        } else {
            _animalListSearchLiveData.value =_animalListLiveData.value
        }
    }
}