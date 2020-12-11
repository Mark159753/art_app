package com.example.future_search.searchFilter

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.core.navagation.SearchNav
import com.example.core.view.MySearchView
import com.example.future_search.R
import com.example.future_search.SearchViewModel
import com.example.future_search.di.SearchInjector
import com.example.future_search.search.SearchFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.SlideDistanceProvider
import javax.inject.Inject

class SearchFilter:Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by activityViewModels{ viewModelFactory }

    @Inject
    lateinit var appNavigator:SearchNav
    private lateinit var toolbar: MaterialToolbar
    private lateinit var mSearchView:MySearchView
    private lateinit var radioGroup: RadioGroup
    private lateinit var searchBtn:MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.search_filter, container, false)

        toolbar = view.findViewById(R.id.search_dialog_toolbar)
        mSearchView = view.findViewById(R.id.search_dialog_search)
        radioGroup = view.findViewById(R.id.search_dialog_radioGroup)
        searchBtn = view.findViewById(R.id.search_dialog_btn)
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            secondaryAnimatorProvider = SlideDistanceProvider(Gravity.TOP)
            addTarget(R.id.search_category_cont)
            addTarget(R.id.search_dialog_btn)
            addTarget(R.id.search_dialog_appbar)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { it.findNavController().popBackStack() }
        mSearchView.addFilterBtnListener{ it.findNavController().popBackStack() }

        val q:String? = arguments?.getString(SearchFragment.SEARCH_QUERY)
        mSearchView.setSearchQuery(q)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectMe()

        initRadioGroup()
        initSearchBtn()
    }

    private fun initSearchBtn(){
        searchBtn.setOnClickListener {
            val q = if (mSearchView.getSearchInputText() == null) null else mSearchView.getSearchInputText().toString()
            val b = bundleOf(SearchFragment.SEARCH_QUERY to q)
            setSearchCategory()
            appNavigator.navFromSearchFilterToSearchFragment(it, b)
        }
    }

    private fun setSearchCategory(){
        viewModel.searchCategoryType = when(radioGroup.checkedRadioButtonId){
            R.id.search_category_all -> {SearchCategory.ALL}
            R.id.search_category_artist -> {SearchCategory.ARTIST}
            R.id.search_category_artwork -> {SearchCategory.ARTWORK}
            R.id.search_category_profile -> {SearchCategory.PROFILE}
            R.id.search_category_gene -> {SearchCategory.GENE}
            R.id.search_category_show -> {SearchCategory.SHOW}
            else -> {SearchCategory.ALL}
        }
    }


    private fun initRadioGroup(){
        when(viewModel.searchCategoryType){
            SearchCategory.ALL -> { radioGroup.check(R.id.search_category_all)}
            SearchCategory.ARTIST -> { radioGroup.check(R.id.search_category_artist)}
            SearchCategory.ARTWORK -> { radioGroup.check(R.id.search_category_artwork)}
            SearchCategory.PROFILE -> { radioGroup.check(R.id.search_category_profile)}
            SearchCategory.GENE -> { radioGroup.check(R.id.search_category_gene)}
            SearchCategory.SHOW -> { radioGroup.check(R.id.search_category_show)}
        }
    }

    private fun injectMe(){
        (activity?.application as SearchInjector)
            .getSearchComponentFactory()
            .create()
            .inject(this)
    }

    enum class SearchCategory(val category:String?){
        ALL(null),
        ARTIST("artist"),
        ARTWORK("artwork"),
        PROFILE("profile"),
        GENE("gene"),
        SHOW("show"),
    }
}