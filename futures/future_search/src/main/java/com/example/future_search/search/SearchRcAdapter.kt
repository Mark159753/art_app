package com.example.future_search.search

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.ArtistModel
import com.example.core.model.ArtworkModel
import com.example.core.model.SearchResultModel
import com.example.future_search.R
import com.example.future_search.databinding.SearchResultItemBinding
import com.squareup.picasso.Picasso

private const val ARTWORK_SEARCH_TYPE = "artwork"
private const val ARTIST_SEARCH_TYPE = "artist"

class SearchRcAdapter(private val listener: ClickListener): PagingDataAdapter<SearchResultModel, SearchRcAdapter.SearchViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchViewHolder private constructor(
            private val binding:SearchResultItemBinding,
            private val listener: ClickListener
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item:SearchResultModel?){
            item?.let { element ->
                binding.searchResultDescription.text = element.description
                binding.searchResultTitle.text = element.title
                binding.searchResultType.text = element.type
                Picasso.get()
                    .load(element.thumbnail)
                    .into(binding.searchResultImg)

                binding.root.setOnClickListener {
                    when(item.type!!){
                        ARTIST_SEARCH_TYPE ->{ listener.artistItemClick(it, parseUri(element.self!!))}
                        ARTWORK_SEARCH_TYPE ->{ listener.artworkItemClick(it, parseUri(element.self!!))}
                    }
                }
            }
        }

        private fun parseUri(url:String):String{
            return Uri.parse(url).lastPathSegment!!
        }

        companion object{
            fun create(parent:ViewGroup, listener: ClickListener):SearchViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val b = DataBindingUtil.inflate<SearchResultItemBinding>(inflater, R.layout.search_result_item, parent, false)
                return SearchViewHolder(b, listener)
            }
        }
    }

    private companion object COMPARATOR: DiffUtil.ItemCallback<SearchResultModel>(){
        override fun areItemsTheSame(
            oldItem: SearchResultModel,
            newItem: SearchResultModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchResultModel,
            newItem: SearchResultModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener{
        fun artistItemClick(view: View, artistId: String)
        fun artworkItemClick(view: View, artworkId:String)
    }
}