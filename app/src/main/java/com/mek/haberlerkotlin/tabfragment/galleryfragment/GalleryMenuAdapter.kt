package com.mek.haberlerkotlin.tabfragment.galleryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mek.haberlerkotlin.R

class GalleryMenuAdapter(private val galleryMenuItem: ArrayList<GalleryMenuModel>) :
    RecyclerView.Adapter<GalleryMenuAdapter.GalleryMenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_menu, parent, false)
        return GalleryMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return galleryMenuItem.size
    }

    override fun onBindViewHolder(holder: GalleryMenuViewHolder, position: Int) {
        holder.bind(galleryMenuItem[position])
    }


    class GalleryMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }

        private val name: TextView = itemView.findViewById(R.id.txt_newstitle)
        private val img: ImageView = itemView.findViewById(R.id.news_image)

        fun bind(model: GalleryMenuModel) {
            name.text = model.name
            img.apply {
                Glide.with(this)
                    .load(model.photo)
                    .apply(RequestOptions().circleCrop())
                    .into(this)

            }
        }
    }

}