package com.example.framework_datasourcess.mapper

import com.example.core.model.ArtistLinks
import com.example.core.model.ArtistModel
import com.example.framework_datasourcess.model.artist.Artist

class ArtistMapper:IMapper<Artist, ArtistModel> {

    override fun map(from: Artist): ArtistModel {
        return ArtistModel(
            from.biography,
            from.birthday,
            from.createdAt,
            from.deathday,
            from.gender,
            from.hometown,
            from.id,
            from.imageVersions,
            ArtistLinks(
                from.links.artworks?.href,
                from.links.genes?.href,
                from.links.image?.href,
                from.links.permalink?.href,
                from.links.publishedArtworks?.href,
                from.links.self?.href,
                from.links.similarArtists?.href,
                from.links.similarContemporaryArtists?.href,
                from.links.thumbnail?.href
            ),
            from.location,
            from.name,
            from.nationality,
            from.slug,
            from.sortableName,
            from.updatedAt
        )
    }
}