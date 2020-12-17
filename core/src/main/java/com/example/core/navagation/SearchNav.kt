package com.example.core.navagation

import android.os.Bundle
import android.view.View
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel

interface SearchNav {

    fun openSearchDialog(view: View)

    fun navToSearchFragment(view: View)

    fun navFromSearchFragmentToSearchFilter(view: View, queryBundle: Bundle)

    fun navFromSearchFilterToSearchFragment(view: View, queryBundle:Bundle)

    fun navFromHomeFragmentToArtworkDetails(view: View, item: ArtworkModel)

    fun navFromHomeFragmentToArtistDetails(view: View, item:ArtistModel)

    fun navFromSearchFragmentToArtworkDetails(view: View, artistId:String?, artworkId:String?)
}