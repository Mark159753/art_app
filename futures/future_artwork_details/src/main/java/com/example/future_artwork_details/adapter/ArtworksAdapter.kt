package com.example.future_artwork_details.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.future_artwork_details.R

class ArtworksAdapter:RecyclerView.Adapter<ArtworksAdapter.ArtworkItemViewHolder>() {

    private val testData = arrayOf(
        TestItem(Color.RED),
        TestItem(Color.YELLOW),
        TestItem(Color.BLUE),
        TestItem(Color.GREEN),
        TestItem(Color.CYAN),
        TestItem(Color.MAGENTA),
        TestItem(Color.WHITE),
        TestItem(Color.DKGRAY),
        TestItem(Color.LTGRAY),
        TestItem(Color.BLACK)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkItemViewHolder {
        return ArtworkItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArtworkItemViewHolder, position: Int) {
        holder.bind(testData[position])
    }

    override fun getItemCount(): Int {
        return testData.size
    }

    class ArtworkItemViewHolder(view:View):RecyclerView.ViewHolder(view){

        private val img:ImageView = view.findViewById(R.id.artwork_item_img)

        fun bind(item:TestItem){
            img.setBackgroundColor(item.color)
        }

        companion object{
            fun create(parent: ViewGroup):ArtworkItemViewHolder{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.artwork_item, parent, false)
                return ArtworkItemViewHolder(v)
            }
        }
    }

    data class TestItem(val color:Int)
}