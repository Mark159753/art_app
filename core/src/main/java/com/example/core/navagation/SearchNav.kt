package com.example.core.navagation

import android.os.Bundle
import android.view.View

interface SearchNav {

    fun openSearchDialog(view: View)

    fun navToSearchFragment(view: View)

    fun navFromSearchFragmentToSearchFilter(view: View, queryBundle: Bundle)

    fun navFromSearchFilterToSearchFragment(view: View, queryBundle:Bundle)

    fun navFromHomeFragmentToArtworkDetails(view: View, id:String)
}