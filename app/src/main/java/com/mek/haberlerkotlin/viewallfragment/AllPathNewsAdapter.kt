package com.mek.haberlerkotlin.viewallfragment

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
import com.mek.haberlerkotlin.utils.Helper

class AllPathNewsAdapter(
    owner: LifecycleOwner,
    viewModel: AllPathNewsVM
) : RecyclerView.Adapter<AllPathNewsAdapter.AllPathNewsVH>() {


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPathNewsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_allpathnews,parent,false)
        return AllPathNewsVH(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: AllPathNewsVH, position: Int) {
        holder.bind(dataList[position])
    }


    class AllPathNewsVH(itemView:View) : RecyclerView.ViewHolder(itemView){

        private val newsPhoto = itemView.findViewById(R.id.img_subNewsPhoto) as ImageView
        private val newsPath = itemView.findViewById(R.id.txt_subNewsPath) as TextView
        private val newsTitle = itemView.findViewById(R.id.txt_subNewsTitle) as TextView
        private val newsDate = itemView.findViewById(R.id.txt_subNewsDate) as TextView

        fun bind(listNewsModel: ListNewsModel) {

            if (listNewsModel.files.isNotEmpty()) {
                Glide.with(newsPath.context)
                    .load(listNewsModel.files[0].fileUrl)
                    .apply(RequestOptions().override(800, 450))
                    .into(newsPhoto)
            }
            newsTitle.text = listNewsModel.title
            newsPath.text =  Helper.pathParse(listNewsModel.path).capitalize()
            newsDate.text = Helper.dateParse(listNewsModel.date)

        }

    }


}