package com.codespacepro.medicinecompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codespacepro.medicinecompose.model.Medicines
import com.codespacepro.medicinecompose.repository.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _myResponse: MutableLiveData<Response<Medicines>> = MutableLiveData()
    val myResponse: LiveData<Response<Medicines>> = _myResponse

    @Singleton
    fun getMedicines() {
        viewModelScope.launch {
            val response = repository.getMedicines()
            _myResponse.value = response
        }
    }
}