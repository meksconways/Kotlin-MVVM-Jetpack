package com.mek.haberlerkotlin.gallerydetail.galleryslider

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.tabfragment.galleryfragment.Files

class GallerySliderAdapter constructor(
    viewModel: GallerySliderVM,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<GallerySliderAdapter.GallerySliderVH>() {

    init {
        viewModel.getData().observe(lifecycleOwner, Observer { data ->

            dataList.clear()
            if (data != null) {
                dataList.addAll(data)
            }
            notifyDataSetChanged()
        })
    }

    private var dataList = ArrayList<Files>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GallerySliderVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_galleryslider, parent, false)
        return GallerySliderVH(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: GallerySliderVH, position: Int) {
        holder.bind(dataList[position], itemCount)
    }


    class GallerySliderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var model: Files

        init {
            itemView.setOnClickListener {

            }
        }

        private val image: ImageView = itemView.findViewById(R.id.img_slider)
        private val newsText: TextView = itemView.findViewById(R.id.txt_slider)
        private val countText: TextView = itemView.findViewById(R.id.txt_count)

        @SuppressLint("SetTextI18n")
        fun bind(files: Files, count: Int) {
            this.model = files
            image.apply {
                Glide.with(this)
                    .load(files.fileUrl)
                    .into(this)
            }
            countText.text = "${adapterPosition + 1}/$count"
            newsText.text = files.metadata.description
        }

    }

}