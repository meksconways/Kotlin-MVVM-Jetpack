package com.mek.haberlerkotlin.tabfragment.homefragment

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
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel


class ListNewsAdapter(
    owner: LifecycleOwner,
    viewModel: ListNewsVM
) : RecyclerView.Adapter<ListNewsAdapter.NewsAdapter>() {


    private val dataList = ArrayList<ListNewsModel>()

    init {

        viewModel.getData().observe(owner, Observer { data ->
            dataList.clear()
            if (data != null) {
                dataList.addAll(data)
            }
            notifyDataSetChanged()
        })


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topics, parent, false)
        return NewsAdapter(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    override fun onBindViewHolder(holder: NewsAdapter, position: Int) {
        holder.bind(dataList[position])
    }


    class NewsAdapter(containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val title = containerView.findViewById(R.id.txt_topicTitle) as TextView
        private val img = containerView.findViewById(R.id.img_topicImage) as ImageView

        fun bind(listNewsModel: ListNewsModel) {

            println(listNewsModel.title)

            title.text = listNewsModel.title
            if (listNewsModel.files.isNotEmpty()) {
                Glide.with(title.context)
                    .load(listNewsModel.files[0].fileUrl)
                    .apply(RequestOptions().override(800, 450))
                    .into(img)
            }

        }


    }


}