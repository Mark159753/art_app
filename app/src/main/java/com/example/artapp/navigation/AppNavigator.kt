package com.example.artapp.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.artapp.MainActivity
import com.example.artapp.R
import com.example.core.ARTIST_FRAGMENT_ARTIST_MODEL
import com.example.core.HOME_FRAGMENT_ARTWORK_MODEL
import com.example.core.SEARCH_FRAGMENT_ARTIST_ID
import com.example.core.SEARCH_FRAGMENT_ARTWORK_ID
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.core.navagation.AuthorizationToMainActivityNav
import com.example.core.navagation.ProfileNav
import com.example.core.navagation.SearchNav
import com.example.future_authorization.AuthorizationActivity
import com.example.future_search.search.SearchFragmentDirections
import com.example.future_search.searchFilter.SearchFilterDirections
import javax.inject.Inject

class AppNavigator @Inject constructor():AuthorizationToMainActivityNav, SearchNav, ProfileNav {

    override fun navigateToMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    override fun openSearchDialog(view: View) {
        view.findNavController().navigate(R.id.searchFilterDialog)
    }

    override fun navToSearchFragment(view: View) {
        view.findNavController().navigate(R.id.searchFragment)
    }

    override fun navFromSearchFragmentToSearchFilter(view: View, queryBundle:Bundle) {
        view.findNavController().navigate(R.id.action_searchFragment_to_searchFilterDialog, queryBundle)
    }

    override fun navFromSearchFilterToSearchFragment(view: View, queryBundle:Bundle) {
        view.findNavController().navigate(R.id.action_searchFilterDialog_to_searchFragment, queryBundle)
    }

    override fun navFromHomeFragmentToArtworkDetails(view: View, item: ArtworkModel) {
        val b = bundleOf(HOME_FRAGMENT_ARTWORK_MODEL to item)
        view.findNavController().navigate(R.id.artworkDetailsFragment, b)
    }

    override fun navFromHomeFragmentToArtistDetails(view: View, item: ArtistModel) {
        val b = bundleOf(ARTIST_FRAGMENT_ARTIST_MODEL to item)
        view.findNavController().navigate(R.id.artworkDetailsFragment, b)
    }

    override fun navFromSearchFragmentToArtworkDetails(view: View, artistId: String?, artworkId: String?) {
        val b = bundleOf(SEARCH_FRAGMENT_ARTWORK_ID to artworkId, SEARCH_FRAGMENT_ARTIST_ID to artistId)
        view.findNavController().navigate(R.id.artworkDetailsFragment, b)
    }

    override fun navFromProfileFragmentToAuthFragment(view: View) {
        val intent = Intent(view.context, AuthorizationActivity::class.java)
        view.context.startActivity(intent)
    }
}