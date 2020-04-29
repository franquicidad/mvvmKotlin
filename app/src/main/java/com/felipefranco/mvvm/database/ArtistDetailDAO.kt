package com.felipefranco.mvvm.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtistDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: ArtistEntity)

    @Query("SELECT * FROM artist_table WHERE mbid = :mbid")
    fun getArtist(mbid: String): LiveData<ArtistEntity>

    @Query("SELECT * FROM artist_table")
    fun getArtists(): LiveData<List<ArtistEntity>>

    @Update
    fun updateArtist(artist:ArtistEntity)
}