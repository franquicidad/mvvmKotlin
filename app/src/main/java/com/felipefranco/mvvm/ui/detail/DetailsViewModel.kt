package com.felipefranco.mvvm.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.felipefranco.mvvm.database.AppDB
import com.felipefranco.mvvm.domain.ArtistDomain
import com.felipefranco.mvvm.repositories.DetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application, mbid: String) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = AppDB.getInstance(application)
    private val detailsRepository = DetailsRepository(database, mbid)

    init {
        getArtistDetail(mbid)
    }

    private fun getArtistDetail(mbid: String) {
        coroutineScope.launch {
            detailsRepository.gerArtist(mbid)
        }
    }

    val artist: LiveData<ArtistDomain> = detailsRepository.artist


    class Factory(val app: Application, private val mbid: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(app, mbid) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}