package com.mek.haberlerkotlin.gallerydetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.tabfragment.galleryfragment.Files
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.GalleryNewsSelectedListener


class GalleryDetailAdapter constructor(
    owner: LifecycleOwner,
    viewModel: GalleryDetailVM,
    private val listener: GalleryNewsSelectedListener
) : RecyclerView.Adapter<GalleryDetailAdapter.GalleryDetailVH>() {

    private val dataList = ArrayList<Files>()

    init {
        viewModel.getData().observe(owner, Observer { data ->
            dataList.clear()
            if (data != null) {
                dataList.addAll(data.files)
            }
            notifyDataSetChanged()
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryDetailVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_detail, parent, false)
        return GalleryDetailVH(view, listener)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: GalleryDetailVH, position: Int) {
        holder.bind(dataList[position])
    }


    class GalleryDetailVH(
        itemView: View,
        listener: GalleryNewsSelectedListener
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.setSelectedImage(adapterPosition)
            }
        }

        lateinit var model: Files

        private val galleryImage: ImageView = itemView.findViewById(R.id.img_galleryItem)

        fun bind(model: Files) {
            this.model = model
            Log.d("///0", model.fileUrl)
            galleryImage.apply {
                Glide.with(this)
                    .load(model.fileUrl)
                    .thumbnail(0.6f)
                    .apply(RequestOptions().override(300, 300).placeholder(R.color.lightgrey))
                    .into(this)
            }
        }

    }

}