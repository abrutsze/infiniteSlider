package com.example.task.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.GetDataInteractor

import com.example.entity.localmodels.DataModel
import com.example.entity.Result
import com.example.task.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getDataInteractor: GetDataInteractor
) : ViewModel() {

    private val _getUserDataList by lazy { SingleLiveEvent<List<DataModel>>() }
    val getDataModelDataList: LiveData<List<DataModel>> = _getUserDataList
    private val _loadingData by lazy { SingleLiveEvent<Boolean>() }
    val loadingData: LiveData<Boolean> = _loadingData
    private val _errorNullData by lazy { SingleLiveEvent<String>() }
    val errorNullData get() = _errorNullData

    init {
        getDataList()
    }

    private fun getDataList() {
        _loadingData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            getDataInteractor.getDataResponse().collect { userData ->
                when (userData) {
                    is Result.Success -> withContext(Dispatchers.Main) {
                        _getUserDataList.value = userData.data
                        _loadingData.value = false
                    }
                    is Result.Error -> withContext(Dispatchers.Main) {
                        _errorNullData.value = userData.errors.errorMessage
                        _loadingData.value = false
                    }
                }

            }
        }
    }
}

