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
    viewModel: MainGalleryVM
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_top,parent,false)
        return MainGalleryVH(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MainGalleryVH, position: Int) {
        holder.bind(dataList[position])
    }


    class MainGalleryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }
        val img1 = itemView.findViewById<ImageView>(R.id.ph1)
        val img2 = itemView.findViewById<ImageView>(R.id.ph2)
        val img3 = itemView.findViewById<ImageView>(R.id.ph3)
        val title = itemView.findViewById<TextView>(R.id.txt_newstitle)

        fun bind(model: GalleryNewsModel){
            try {
                Log.d("***",model.id+"  "+model.title)
            }catch (e:Exception) {
                e.printStackTrace()
            }
            title.text = model.title
            img1.apply {
                Glide.with(this)
                    .load(model.files[0].fileUrl)
                    .apply(RequestOptions().override(500,500))
                    .into(this)
            }
            img2.apply {
                Glide.with(this)
                    .load(model.files[1].fileUrl)
                    .apply(RequestOptions().override(500,500))
                    .into(this)
            }
            img3.apply {
                Glide.with(this)
                    .load(model.files[2].fileUrl)
                    .apply(RequestOptions().override(500,500))
                    .into(this)
            }

        }
    }


}