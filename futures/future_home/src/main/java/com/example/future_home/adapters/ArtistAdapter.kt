package com.example.future_home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.ArtistModel
import com.example.future_home.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ArtistAdapter(private val listener: ClickListener):PagingDataAdapter<ArtistModel, ArtistAdapter.ArtistViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ArtistViewHolder(private val view:View,  private val listener: ClickListener):RecyclerView.ViewHolder(view){
        private val img: ShapeableImageView = view.findViewById(R.id.artist_item_img)
        private val name:TextView = view.findViewById(R.id.artist_item_name)

        fun bind(item:ArtistModel?){
            item?.let { artist ->
                artist.links.thumbnail?.let { thumbnail ->
                    Picasso.get()
                            .load(thumbnail)
                            .into(img)
                }
                name.text = artist.name

                view.setOnClickListener {
                    listener.artistItemClick(it, artist)
                }
            }
        }

        companion object{
            fun create(parent:ViewGroup, listener: ClickListener):ArtistViewHolder{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.artist_item, parent, false)
                return ArtistViewHolder(v, listener)
            }
        }
    }

    companion object COMPARATOR: DiffUtil.ItemCallback<ArtistModel>(){
        override fun areItemsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener{
        fun artistItemClick(view: View, item: ArtistModel)
    }
}