package com.example.framework_datasourcess.model.artist


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Links(
    @Embedded(prefix = "artworks_")
    @SerializedName("artworks")
    val artworks: Artworks?,
    @Embedded(prefix = "genes_")
    @SerializedName("genes_")
    val genes: Genes?,
    @Embedded(prefix = "image_")
    @SerializedName("image")
    val image: Image?,
    @SerializedName("permalink")
    @Embedded(prefix = "permalink_")
    val permalink: Permalink?,
    @Embedded(prefix = "published_artworks_")
    @SerializedName("published_artworks")
    val publishedArtworks: PublishedArtworks?,
    @Embedded(prefix = "self_")
    @SerializedName("self")
    val self: Self?,
    @Embedded(prefix = "similar_artists_")
    @SerializedName("similar_artists")
    val similarArtists: SimilarArtists?,
    @Embedded(prefix = "similar_contemporary_artists_")
    @SerializedName("similar_contemporary_artists")
    val similarContemporaryArtists: SimilarContemporaryArtists?,
    @Embedded(prefix = "thumbnail_")
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?
)