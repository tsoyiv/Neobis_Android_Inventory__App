package com.example.shop_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.shop_app.data.ShoeDatabase
import com.example.shop_app.repository.ShoeRepository
import com.example.shop_app.model.Shoe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoeViewModel(application: Application): AndroidViewModel(application) {

    public val readAllData: LiveData<List<Shoe>>
    private val repository: ShoeRepository

    init {
        val shoeDao = ShoeDatabase.getDatabase(application).shoeDao()
        repository = ShoeRepository(shoeDao)
        readAllData = repository.readAllData
    }
    fun addShoe(shoe: Shoe) {
        viewModelScope.launch( Dispatchers.IO) {
            repository.addShoe(shoe)
        }
    }
    fun searchDatabase(searchQuery: String): LiveData<List<Shoe>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
    fun updateShoe(shoe: Shoe) {
        viewModelScope.launch (Dispatchers.IO){
            repository.updateShoe(shoe)
        }
    }
}