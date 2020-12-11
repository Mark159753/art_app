package com.example.future_home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.ArtworkModel
import com.example.future_home.R
import com.squareup.picasso.Picasso

private const val BIG_ITEM = 1
private const val SMALL_ITEM = 2

class ArtworksAdapter(
    private val listener:ClickListener
): PagingDataAdapter<ArtworkModel, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ArtworkSmallItemViewHolder -> holder.bind(getItem(position))
            is ArtworkBigItemViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == SMALL_ITEM) ArtworkSmallItemViewHolder.create(parent, listener)
        else ArtworkBigItemViewHolder.create(parent, listener)
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position - 1) % 3 == 0) BIG_ITEM else SMALL_ITEM
    }

    class ArtworkSmallItemViewHolder(private val view:View, private val listener:ClickListener):RecyclerView.ViewHolder(view){

        private val img = view.findViewById<ImageView>(R.id.artwork_small_img)

        fun bind(item:ArtworkModel?){
            item.let { itemArt ->
                itemArt?.links?.thumbnail?.let { thumbnail ->
                    Picasso.get()
                        .load(thumbnail)
                        .into(img)
                }
                view.setOnClickListener {
                    listener.artworkItemClick(it, itemArt!!.id)
                }
            }
        }

        companion object{
            fun create(parent: ViewGroup, listener:ClickListener):ArtworkSmallItemViewHolder{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.artwork_small_item, parent, false)
                return ArtworkSmallItemViewHolder(v, listener)
            }
        }
    }

    class ArtworkBigItemViewHolder(private val view:View, private val listener:ClickListener):RecyclerView.ViewHolder(view){
        private val img = view.findViewById<ImageView>(R.id.artwork_big_img)

        fun bind(item:ArtworkModel?){
            item.let { itemArt ->
                itemArt?.links?.thumbnail?.let { thumbnail ->
                    Picasso.get()
                            .load(thumbnail)
                            .into(img)
                }

                view.setOnClickListener {
                    listener.artworkItemClick(it, itemArt!!.id)
                }
            }
        }

        companion object{
            fun create(parent: ViewGroup, listener:ClickListener):ArtworkBigItemViewHolder{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.artwork_big_item, parent, false)
                return ArtworkBigItemViewHolder(v, listener)
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

    interface ClickListener{
        fun artworkItemClick(view: View, id:String)
    }
}