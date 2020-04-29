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

class DetailsRepository(private val database: AppDB,mbid:String) {

    val artist: LiveData<ArtistDomain> = Transformations.map(database.artistDetailDao.getArtist(mbid)) {
        it.asDomainModel()
    }

    suspend fun gerArtist(mbid :String) {
        withContext(Dispatchers.IO) {
            val artistResponse = LastfmApi.retrofitService.getArtistDetailAsync(mbid).await()
            database.artistDetailDao.updateArtist(artistResponse.artist.asDatabaseModel())
        }
    }
}