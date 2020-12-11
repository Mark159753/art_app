package com.example.future_search.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.SearchResultModel
import com.example.future_search.R
import com.example.future_search.databinding.SearchResultItemBinding
import com.squareup.picasso.Picasso

class SearchRcAdapter: PagingDataAdapter<SearchResultModel, SearchRcAdapter.SearchViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchViewHolder private constructor(private val binding:SearchResultItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:SearchResultModel?){
            item?.let { element ->
                binding.searchResultDescription.text = element.description
                binding.searchResultTitle.text = element.title
                binding.searchResultType.text = element.type
                Picasso.get()
                    .load(element.thumbnail)
                    .into(binding.searchResultImg)
            }
        }


        companion object{
            fun create(parent:ViewGroup):SearchViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val b = DataBindingUtil.inflate<SearchResultItemBinding>(inflater, R.layout.search_result_item, parent, false)
                return SearchViewHolder(b)
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
}