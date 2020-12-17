package com.example.future_search.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseActivity
import com.example.core.navagation.SearchNav
import com.example.future_search.SearchViewModel
import com.example.future_search.databinding.SearchFragmentBinding
import com.example.future_search.di.SearchInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment(), SearchRcAdapter.ClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by activityViewModels{ viewModelFactory }
    private var binder:SearchFragmentBinding? = null

    @Inject
    lateinit var appNavigator:SearchNav

    private val mSearchAdapter:SearchRcAdapter by lazy { SearchRcAdapter(this) }

    private val mDisposable = CompositeDisposable()
    private var pagingSearchDisposable:Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = SearchFragmentBinding.inflate(inflater, container, false)
        return binder!!.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectMe()

        initRecyclerView()
        initSearchBox()
        initSearchFilterBtn()

        val q:String? = arguments?.getString(SEARCH_QUERY)
        binder!!.searchBox.setSearchQuery(q)
    }

    private fun initSearchFilterBtn(){
        binder!!.searchBox.addFilterBtnListener{
            val q = binder!!.searchBox.getSearchInputText()
            val res = q?.toString()
            val b = bundleOf(SEARCH_QUERY to res)
            appNavigator.navFromSearchFragmentToSearchFilter(it, b)
        }
    }

    private fun initRecyclerView(){
        binder!!.searchResultList.apply {
            adapter = mSearchAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun initSearchBox(){
        initSearchKeyboardActionListener()
        val dis = binder!!.searchBox.getObservableOnTextInput()
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe(
                { data ->
                    pagingSearchDisposable?.dispose()
                    pagingSearchDisposable = viewModel.search(data.toString(), viewModel.searchCategoryType.category)
                        .subscribe(
                            {paging ->
                                mSearchAdapter.submitData(viewLifecycleOwner.lifecycle, paging)
                            },
                            {e ->
                                Log.e("PagingError", e.message ?: "Unknown")
                            }
                        )
                },
                { e ->
                    Log.e("TextInputError", e.message ?: "Unknown")
                }
            )
        mDisposable.add(dis)
    }

    private fun initSearchKeyboardActionListener(){
        binder!!.searchBox.addEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                (activity as BaseActivity).softHideKeyboard(requireContext())
                binder!!.searchResultList.requestFocus()
                return@addEditorActionListener true
            }
            return@addEditorActionListener false
        }
    }

    override fun artistItemClick(view: View, artistId: String) {
        Log.e("ARTIST_CLICK", artistId)
        appNavigator.navFromSearchFragmentToArtworkDetails(view, artistId, null)
    }

    override fun artworkItemClick(view: View, artworkId: String) {
        Log.e("ARTWORK_CLICK", artworkId)
        appNavigator.navFromSearchFragmentToArtworkDetails(view, null, artworkId)
    }

    private fun injectMe(){
        (activity?.application as SearchInjector)
            .getSearchComponentFactory()
            .create()
            .inject(this)
    }


    override fun onDestroy() {
        binder = null
        mDisposable.dispose()
        super.onDestroy()
    }

    companion object{
        const val SEARCH_QUERY = "com.example.future_search.search.SEARCH_QUERY"
    }

}