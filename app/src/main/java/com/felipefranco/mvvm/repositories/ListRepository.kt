package com.felipefranco.mvvm.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.felipefranco.mvvm.database.AppDB
import com.felipefranco.mvvm.database.asDomainModel
import com.felipefranco.mvvm.domain.ArtistDomain
import com.felipefranco.mvvm.network.LastfmApi
import com.felipefranco.mvvm.network.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListRepository(private val database: AppDB) {

    val artists: LiveData<List<ArtistDomain>> = Transformations.map(database.artistDetailDao.getArtists()) {
        it.asDomainModel()
    }


    suspend fun refreshArtists() {
        withContext(Dispatchers.IO) {
            val artistsResponse = LastfmApi.retrofitService.getArtistsAsync().await()
            artistsResponse.topartists.artistsList.forEach{
                database.artistDetailDao.insertArtist(it.asDatabaseModel())
            }
        }
    }
}