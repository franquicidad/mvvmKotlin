package com.felipefranco.mvvm.ui.list

import android.app.Application
import androidx.lifecycle.*
import com.felipefranco.mvvm.database.AppDB.Companion.getInstance
import com.felipefranco.mvvm.repositories.ListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getInstance(application)
    private val listRepository = ListRepository(database)

    val artistList = listRepository.artists

    init {
        getArtists()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun getArtists() {
        coroutineScope.launch {
            listRepository.refreshArtists()
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
