package com.example.future_artwork_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.room.EmptyResultSetException
import androidx.viewpager2.widget.ViewPager2
import com.example.core.ARTIST_FRAGMENT_ARTIST_MODEL
import com.example.core.HOME_FRAGMENT_ARTWORK_MODEL
import com.example.core.SEARCH_FRAGMENT_ARTIST_ID
import com.example.core.SEARCH_FRAGMENT_ARTWORK_ID
import com.example.core.exception.NoConnectivityException
import com.example.core.exception.UnknownArtistException
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.future_artwork_details.adapter.ArtworksAdapter
import com.example.future_artwork_details.adapter.SliderTransformer
import com.example.future_artwork_details.databinding.ArtworkDetailsFragmentBinding
import com.example.future_artwork_details.di.ArtworkDetailsInjector
import com.example.future_artwork_details.state.UiState
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.CompositeException
import javax.inject.Inject

class ArtworkDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ArtworkDetailsViewModel by viewModels{ viewModelFactory }

    private var binder:ArtworkDetailsFragmentBinding? = null
    private val artworkAdapter:ArtworksAdapter by lazy { ArtworksAdapter() }

    private val mDisposable = CompositeDisposable()

    private var headerItem:ArtworkModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = ArtworkDetailsFragmentBinding.inflate(inflater, container, false)
        return binder!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectMe()
        binder!!.apply {
            lifecycleOwner = this@ArtworkDetailsFragment
            viewModel = this@ArtworkDetailsFragment.viewModel
        }

        headerItem = arguments?.getParcelable(HOME_FRAGMENT_ARTWORK_MODEL)

        subscribeOnUiState()
        initToolbar()

        loadData()
        initViewPager()
    }


    private fun initViewPager(){
        binder!!.artworkDetailsViewPager.apply {
            adapter = artworkAdapter
            setPageTransformer(SliderTransformer(3))
            offscreenPageLimit = 3
            registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    val item = artworkAdapter.getItemFromPosition(position)
                    item?.let { viewModel.setArtworkLiveData(it) }
                }
            })
        }
    }

    private fun loadData(){
        val artwork:ArtworkModel? = arguments?.getParcelable(HOME_FRAGMENT_ARTWORK_MODEL)
        val artist:ArtistModel? = arguments?.getParcelable(ARTIST_FRAGMENT_ARTIST_MODEL)
        val artistId:String? = arguments?.getString(SEARCH_FRAGMENT_ARTIST_ID)
        val artworkId:String? = arguments?.getString(SEARCH_FRAGMENT_ARTWORK_ID)

        viewModel.setUiState(UiState.Loading)
        val disposable = Flowable.defer {
            when {
                artwork != null -> {
                    viewModel.getArtworkPagingByArtwork(artwork.id)
                }
                artworkId != null -> {
                    viewModel.getArtworkPagingByArtwork(artworkId)
                }
                artist != null -> {
                    viewModel.getArtworkPagingByArtist(artist)
                }
                else -> {
                    viewModel.getArtworkPagingByArtistId(artistId!!)
                }
            }
        }
                .subscribe(
                {data ->
                    viewModel.setUiState(UiState.Success)
                    artwork?.let { data.insertHeaderItem(it) }
                    artworkAdapter.submitData(lifecycle, data)
                },
        {e ->
            if (e is CompositeException){
                (e as CompositeException).exceptions.forEach {
                    checkError(it, artwork)
                }
            }else{
                checkError(e, artwork)
            }
        }
        )

        mDisposable.add(disposable)
    }

    private fun checkError(e:Throwable, artwork:ArtworkModel?){
        when (e) {
            is NoConnectivityException -> {
                viewModel.setUiState(UiState.NoInternetConnection)
            }
            is EmptyResultSetException -> {
                viewModel.setUiState(UiState.NoInternetConnection)
            }
            is UnknownArtistException -> {
                viewModel.setUiState(UiState.Success)
                artwork?.let { item ->
                    val data = PagingData.from(listOf(item))
                    artworkAdapter.submitData(lifecycle, data)
                }
            }
            else -> {
                Log.e("ERROR", "Message: ${e.message}")
                viewModel.setUiState(UiState.Error(e))
            }
        }
    }



    private fun subscribeOnUiState(){
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                UiState.Loading -> {
                    showLoadingUi()
                }
                is UiState.Error -> {
                    showErrorUi()
                }
                UiState.NoInternetConnection -> {
                    showNoInternetUi()
                }
                UiState.Success -> {
                    showContent()
                }
            }
        })
    }

    private fun showLoadingUi(){
        binder!!.apply {
            showHideGroup.visibility = View.GONE
            loadingScreen.visibility = View.VISIBLE
            noInternetMessage.visibility = View.GONE
            errorMessage.visibility = View.GONE
        }
    }

    private fun showErrorUi(){
        binder!!.apply {
            showHideGroup.visibility = View.GONE
            loadingScreen.visibility = View.GONE
            noInternetMessage.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
        }
    }

    private fun showNoInternetUi(){
        binder!!.apply {
            showHideGroup.visibility = View.GONE
            loadingScreen.visibility = View.GONE
            noInternetMessage.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
        }
    }

    private fun showContent(){
        binder!!.apply {
            showHideGroup.visibility = View.VISIBLE
            loadingScreen.visibility = View.GONE
            noInternetMessage.visibility = View.GONE
            errorMessage.visibility = View.GONE
        }
    }

    private fun initToolbar(){
        binder!!.artworkDetailsToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        binder!!.artworkDetailsToolbar.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun injectMe(){
        (activity?.application as ArtworkDetailsInjector)
                .getDetailsArtworkComponentFactory()
                .create()
                .inject(this)
    }

    override fun onDestroy() {
        binder = null
        mDisposable.dispose()
        super.onDestroy()
    }

}