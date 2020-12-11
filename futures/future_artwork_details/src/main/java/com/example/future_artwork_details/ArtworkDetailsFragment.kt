package com.example.future_artwork_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.future_artwork_details.adapter.ArtworksAdapter
import com.example.future_artwork_details.adapter.SliderTransformer
import com.example.future_artwork_details.databinding.ArtworkDetailsFragmentBinding

class ArtworkDetailsFragment : Fragment() {

    private val viewModel: ArtworkDetailsViewModel by viewModels()

    private var binder:ArtworkDetailsFragmentBinding? = null
    private val artworkAdapter:ArtworksAdapter by lazy { ArtworksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = ArtworkDetailsFragmentBinding.inflate(inflater, container, false)
        return binder!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initToolbar()
        initViewPager()
    }

    private fun initViewPager(){
        binder!!.artworkDetailsViewPager.apply {
            adapter = artworkAdapter
            setPageTransformer(SliderTransformer(3))
            offscreenPageLimit = 3
        }
    }

    private fun initToolbar(){
        binder!!.artworkDetailsToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        binder!!.artworkDetailsToolbar.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        binder = null
        super.onDestroy()
    }

}