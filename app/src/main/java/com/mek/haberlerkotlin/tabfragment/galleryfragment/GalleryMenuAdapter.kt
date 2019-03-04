package com.mek.haberlerkotlin.tabfragment.galleryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mek.haberlerkotlin.R


class GalleryMenuAdapter(
    private val galleryMenuItem: MutableList<GalleryMenuModel>,
    private val listener: MenuItemSelectedListener,
    viewmodel: GalleryVM,
    lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<GalleryMenuAdapter.GalleryMenuViewHolder>() {


    init {
        viewmodel.getSelectionPosition().observe(lifecycleOwner, Observer { pos ->
            for (index in 0 until galleryMenuItem.size) {
                galleryMenuItem[index].isSelected = index == pos
            }
           // listener.setItemSelected(galleryMenuItem[pos].Path,pos)
            notifyDataSetChanged()
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_menu, parent, false)
        return GalleryMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return galleryMenuItem.size
    }

    override fun onBindViewHolder(holder: GalleryMenuViewHolder, position: Int) {
        holder.bind(galleryMenuItem[position])
        holder.imgBack.setOnClickListener {

            for (pos in 0 until galleryMenuItem.size) {
                galleryMenuItem[pos].isSelected = pos == position
            }
            listener.setItemSelected(galleryMenuItem[position].Path,position)
            notifyDataSetChanged()
        }
    }


    class GalleryMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.txt_newstitle)
        val imgBack: CardView = itemView.findViewById(R.id.news_image_back)
        private val imgIcon: ImageView = itemView.findViewById(R.id.news_image)
        private val selectorItem: ImageView = itemView.findViewById(R.id.img_Selected)

        fun bind(model: GalleryMenuModel) {
            if (model.isSelected) {
                selectorItem.visibility = View.VISIBLE
            } else {
                selectorItem.visibility = View.INVISIBLE
            }
            selectorItem.setColorFilter(model.color)
            name.text = model.name
            imgBack.apply {
                setCardBackgroundColor(model.color)
            }
            imgIcon.apply {
                Glide.with(this)
                    .load(model.photo)
                    .into(this)

            }
        }
    }

}