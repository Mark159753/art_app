package com.example.artapp.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.artapp.MainActivity
import com.example.artapp.R
import com.example.core.navagation.AuthorizationToMainActivityNav
import com.example.core.navagation.SearchNav
import com.example.future_search.search.SearchFragmentDirections
import com.example.future_search.searchFilter.SearchFilterDirections
import javax.inject.Inject

class AppNavigator @Inject constructor():AuthorizationToMainActivityNav, SearchNav {

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

    override fun navFromHomeFragmentToArtworkDetails(view: View, id: String) {
        view.findNavController().navigate(R.id.artworkDetailsFragment)
    }
}