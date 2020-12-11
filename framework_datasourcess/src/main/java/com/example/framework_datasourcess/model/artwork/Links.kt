package com.example.framework_datasourcess.model.artwork


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Links(
    @Embedded(prefix = "artist_")
    @SerializedName("artists")
    val artists: Artists?,
    @Embedded(prefix = "collection_users_")
    @SerializedName("collection_users")
    val collectionUsers: CollectionUsers?,
    @Embedded(prefix = "genes_")
    @SerializedName("genes")
    val genes: Genes?,
    @Embedded(prefix = "image_")
    @SerializedName("image")
    val image: Image?,
    @Embedded(prefix = "partner_")
    @SerializedName("partner")
    val partner: Partner?,
    @Embedded(prefix = "permalink_")
    @SerializedName("permalink")
    val permalink: Permalink?,
    @Embedded(prefix = "sale_artworks_")
    @SerializedName("sale_artworks")
    val saleArtworks: SaleArtworks?,
    @Embedded(prefix = "self_")
    @SerializedName("self")
    val self: Self?,
    @Embedded(prefix = "similar_artworks_")
    @SerializedName("similar_artworks")
    val similarArtworks: SimilarArtworks?,
    @Embedded(prefix = "thumbnail_")
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?
)