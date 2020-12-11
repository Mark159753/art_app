package com.example.future_home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.Fade
import com.example.core.navagation.SearchNav
import com.example.core.until.MarginItemDecorator
import com.example.future_home.adapters.ArtistAdapter
import com.example.future_home.adapters.ArtworkItemDecorator
import com.example.future_home.adapters.ArtworksAdapter
import com.example.future_home.databinding.HomeFragmentBinding
import com.example.future_home.di.HomeInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomeFragment : Fragment(), ArtworksAdapter.ClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appNavigator:SearchNav
    private val viewModel: HomeViewModel by viewModels{ viewModelFactory }
    private var binder:HomeFragmentBinding? = null
    private val artworksAdapter:ArtworksAdapter = ArtworksAdapter(this)
    private val artistAdapter:ArtistAdapter = ArtistAdapter()
    private val mDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = HomeFragmentBinding.inflate(inflater, container, false)
        return binder!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectMe()

        initFilterBtn()

        initArtworksAdapter()
        initWhatNextList()

        initArtistList()
        initArtistAdapter()

        blockSearchFocus()
    }

    private fun blockSearchFocus(){
        binder!!.homeSearch.blockFocus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Fade()
    }

    private fun initFilterBtn(){
        binder!!.homeSearch.addFilterBtnListener{
            appNavigator.openSearchDialog(it)
        }

        binder!!.homeSearch.addTextFieldClickListener{
            appNavigator.navToSearchFragment(it)
        }
    }

    private fun initArtworksAdapter(){
        mDisposable.add(viewModel.artworks.subscribe(
            { data ->
                artworksAdapter.submitData(lifecycle, data)
            },
            {e ->
                Log.e("ERROR", e.message.toString())
            }
        ))
    }

    private fun initWhatNextList(){

        binder!!.whatsNextList.apply {
            adapter = artworksAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
            addItemDecoration(ArtworkItemDecorator(resources.getDimensionPixelSize(R.dimen.item_space), resources.getDimensionPixelSize(R.dimen.item_space)))
            setHasFixedSize(true)
        }
    }

    private fun initArtistAdapter(){
        mDisposable.add(viewModel.artists.subscribe(
                {data ->
                    artistAdapter.submitData(lifecycle, data)
                },
                {e ->
                    Log.e("ERROR", e.message.toString())
                }
        ))
    }

    private fun initArtistList(){
        binder!!.homeArtistDiscover.apply {
            adapter = artistAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(MarginItemDecorator(resources.getDimensionPixelSize(R.dimen.item_space), resources.getDimensionPixelSize(R.dimen.item_space), true))
            setHasFixedSize(true)
        }
    }

    override fun artworkItemClick(view: View, id: String) {
        appNavigator.navFromHomeFragmentToArtworkDetails(view, id)
    }

    private fun injectMe(){
        (activity?.application as HomeInjector)
            .getHomeFragmentComponentFactory()
            .create()
            .inject(this)
    }


    override fun onDestroy() {
        binder = null
        mDisposable.dispose()
        super.onDestroy()
    }

}