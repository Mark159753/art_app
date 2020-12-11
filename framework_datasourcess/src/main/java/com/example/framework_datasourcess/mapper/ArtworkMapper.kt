package com.example.framework_datasourcess.mapper

import com.example.core.model.ArtworkDimensions
import com.example.core.model.ArtworkLinks
import com.example.core.model.ArtworkModel
import com.example.framework_datasourcess.model.artwork.Artwork

class ArtworkMapper:IMapper<Artwork, ArtworkModel> {

    override fun map(from: Artwork): ArtworkModel {
        return ArtworkModel(
            from.additionalInformation,
            from.blurb,
            from.canAcquire,
            from.canInquire,
            from.canShare,
            from.category,
            from.collectingInstitution,
            from.createdAt,
            from.culturalMaker,
            from.date,
            ArtworkDimensions(
                from.dimensions?.cm?.depth,
                from.dimensions?.cm?.diameter,
                from.dimensions?.cm?.height,
                from.dimensions?.cm?.text,
                from.dimensions?.cm?.width
            ),
            from.exhibitionHistory,
            from.iconicity,
            from.id,
            from.imageRights,
            from.imageVersions,
            ArtworkLinks(
                from.links.artists?.href,
                from.links.collectionUsers?.href,
                from.links.genes?.href,
                from.links.image?.href,
                from.links.partner?.href,
                from.links.permalink?.href,
                from.links.saleArtworks?.href,
                from.links.self?.href,
                from.links.similarArtworks?.href,
                from.links.thumbnail?.href
            ),
            from.literature,
            from.medium,
            from.provenance,
            from.published,
            from.saleMessage,
            from.signature,
            from.slug,
            from.sold,
            from.title,
            from.unique,
            from.updatedAt,
            from.website
        )
    }
}