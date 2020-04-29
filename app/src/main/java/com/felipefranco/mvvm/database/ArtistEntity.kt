package com.felipefranco.mvvm.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.felipefranco.mvvm.domain.ArtistDomain

@Entity(tableName = "artist_table")
class ArtistEntity(
    @PrimaryKey
    val mbid: String,
    val name: String,
    val listeners: String,
    val url: String,
    val streamable: String,
    val bio: String,
    val image: String,
    val published: String,
    val summary: String,
    val content: String
)

fun ArtistEntity.asDomainModel(): ArtistDomain {
    return ArtistDomain(
        mbid = this.mbid,
        name = this.name,
        listeners = this.listeners,
        url = this.url,
        streamable = this.streamable,
        bio = this.bio,
        image = this.image,
        published = this.published,
        summary = this.summary,
        content = this.content
    )
}

fun List<ArtistEntity>.asDomainModel(): List<ArtistDomain> {
    return map {
        ArtistDomain(
            mbid = it.mbid,
            name = it.name,
            listeners = it.listeners,
            url = it.url,
            streamable = it.streamable,
            bio = it.bio,
            image = it.image,
            published = it.published,
            summary = it.summary,
            content = it.content
        )
    }
}