package com.mek.haberlerkotlin.tabfragment.galleryfragment.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel

class MainGalleryAdapter(
    lifecycleOwner: LifecycleOwner,
    viewModel: MainGalleryVM,
    private val listener: GalleryNewsSelectedListener
) : RecyclerView.Adapter<MainGalleryAdapter.MainGalleryVH>() {

    private val dataList = ArrayList<GalleryNewsModel>()

    init {
        viewModel.getData().observe(lifecycleOwner, Observer { data ->

            dataList.clear()
            if (data != null) {
                dataList.addAll(data)
            }
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainGalleryVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_top, parent, false)
        return MainGalleryVH(view, listener)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MainGalleryVH, position: Int) {
        holder.bind(dataList[position])
    }


    class MainGalleryVH(
        itemView: View,
        listener: GalleryNewsSelectedListener
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var model: GalleryNewsModel

        init {
            itemView.setOnClickListener {
                listener.setSelectedNews(model)
            }
        }

        val img1 = itemView.findViewById<ImageView>(R.id.ph1)
        val img2 = itemView.findViewById<ImageView>(R.id.ph2)
        val img3 = itemView.findViewById<ImageView>(R.id.ph3)
        val title = itemView.findViewById<TextView>(R.id.txt_newstitle)
        val txtMore = itemView.findViewById<TextView>(R.id.txt_plus)

        fun bind(model: GalleryNewsModel) {
            this.model = model
            title.text = model.title
            txtMore.text = "+${model.files.size - 2}"
            img1.apply {
                Glide.with(this)
                    .load(model.files[0].fileUrl)
                    .apply(RequestOptions().override(500, 500))
                    .into(this)
            }
            img2.apply {
                Glide.with(this)
                    .load(model.files[1].fileUrl)
                    .apply(RequestOptions().override(500, 500))
                    .into(this)
            }
            img3.apply {
                Glide.with(this)
                    .load(model.files[2].fileUrl)
                    .apply(RequestOptions().override(500, 500))
                    .into(this)
            }

        }
    }


}