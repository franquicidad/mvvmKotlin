package com.felipefranco.mvvm.network.model

import com.felipefranco.mvvm.database.ArtistEntity
import com.squareup.moshi.Json

data class ArtistsResponse(
    val topartists: ArtistsList
)

data class ArtistsList(
    @Json(name = "artist")
    val artistsList: List<Artist>
)

data class Artist(
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<Image>
)

/**
 *
 */

data class ArtistDetailsResponse(
    val artist: ArtistDetails
)

data class ArtistDetails(
    val name: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<Image>,
    val bio: Bio
)

data class Bio(
    val published: String,
    val summary: String,
    val content: String
)

/**
 *
 */

data class Image(
    @Json(name = "#text")
    val text: String,
    val size: String
)

/**
 *
 */

fun Artist.asDatabaseModel(): ArtistEntity {
    return ArtistEntity(
        this.mbid,
        this.name,
        this.listeners,
        this.url,
        this.streamable,
        "",
        this.image[0].text,
        "",
        "",
        ""
    )
}

fun ArtistDetails.asDatabaseModel(): ArtistEntity {
    return ArtistEntity(
        this.mbid,
        this.name,
        "",
        this.url,
        this.streamable,
        "",
        this.image[0].text,
        this.bio.published,
        this.bio.summary,
        this.bio.content
    )
}