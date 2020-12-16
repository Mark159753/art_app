package com.example.future_artwork_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.ArtworkModel
import com.example.future_artwork_details.R
import com.squareup.picasso.Picasso

class ArtworksAdapter: PagingDataAdapter<ArtworkModel, ArtworksAdapter.ArtworkItemViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ArtworkItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkItemViewHolder {
        return ArtworkItemViewHolder.create(parent)
    }

    fun getItemFromPosition(position: Int):ArtworkModel? = getItem(position)

    class ArtworkItemViewHolder(view:View):RecyclerView.ViewHolder(view){

        private val img:ImageView = view.findViewById(R.id.artwork_item_img)

        fun bind(item:ArtworkModel?){
            item?.let { artwork ->
                artwork.links.thumbnail?.let { thumbnail ->
                    Picasso.get()
                            .load(thumbnail)
                            .into(img)
                }
            }
        }

        companion object{
            fun create(parent: ViewGroup):ArtworkItemViewHolder{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.artwork_item, parent, false)
                return ArtworkItemViewHolder(v)
            }
        }
    }

    companion object COMPARATOR: DiffUtil.ItemCallback<ArtworkModel>(){
        override fun areItemsTheSame(oldItem: ArtworkModel, newItem: ArtworkModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArtworkModel, newItem: ArtworkModel): Boolean {
            return oldItem == newItem
        }
    }
}