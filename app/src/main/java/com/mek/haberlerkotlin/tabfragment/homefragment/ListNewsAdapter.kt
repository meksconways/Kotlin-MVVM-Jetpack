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
    viewModel: ListNewsVM,
    private val listener: NewsSelectedListener
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
        return NewsAdapter(view, listener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onViewAttachedToWindow(holder: NewsAdapter) {
        super.onViewAttachedToWindow(holder)
        android.util.Log.d("*** ${holder.adapterPosition}", "+ oldu")
    }

    override fun onViewDetachedFromWindow(holder: NewsAdapter) {
        super.onViewDetachedFromWindow(holder)
        android.util.Log.d("*** ${holder.adapterPosition}", "- oldu")
    }


    override fun onBindViewHolder(holder: NewsAdapter, position: Int) {
//        holder.bind(dataList[position % dataList.size])
        holder.bind(dataList[position])

    }


    class NewsAdapter(
        containerView: View,
        listener: NewsSelectedListener
    ) : RecyclerView.ViewHolder(containerView) {

        private val title = containerView.findViewById(R.id.txt_topicTitle) as TextView
        private val img = containerView.findViewById(R.id.img_topicImage) as ImageView

        lateinit var model: ListNewsModel

        init {

            containerView.setOnClickListener {
                listener.setSelectedNews(model)
            }
        }

        fun bind(listNewsModel: ListNewsModel) {
            this.model = listNewsModel
            title.text = listNewsModel.title
            if (listNewsModel.files.isNotEmpty()) {
                img.apply {
                    Glide.with(this)
                        .load(listNewsModel.files[0].fileUrl)
                        .apply(RequestOptions().override(800, 450))
                        .into(this)

                }

            }

        }


    }


}