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
import com.mek.haberlerkotlin.utils.Helper

class SubNewsAdapter constructor(owner: LifecycleOwner, viewModel: ListNewsVM, path: String = "/spor/") :
    RecyclerView.Adapter<SubNewsAdapter.SubNewsViewHolder>() {

    private val dataList = ArrayList<ListNewsModel>()


    init {

        when(path){
            "/spor/" -> {
                viewModel.getSportsData().observe(owner, Observer { data ->
                    dataList.clear()
                    if (data != null) {
                        dataList.addAll(data)
                    }
                    notifyDataSetChanged()
                })
            }
            "/dunya/" -> {
                viewModel.getCountryData().observe(owner, Observer { data ->
                    dataList.clear()
                    if (data != null) {
                        dataList.addAll(data)
                    }
                    notifyDataSetChanged()
                })
            }
            "/gundem/" -> {
                viewModel.getJournalData().observe(owner, Observer { data ->
                    dataList.clear()
                    if (data != null) {
                        dataList.addAll(data)
                    }
                    notifyDataSetChanged()
                })
            }
            "/ekonomi/" -> {
                viewModel.getEconomyData().observe(owner, Observer { data ->
                    dataList.clear()
                    if (data != null) {
                        dataList.addAll(data)
                    }
                    notifyDataSetChanged()
                })
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subnews, parent, false)
        return SubNewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: SubNewsViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    class SubNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
            newsPath.text = listNewsModel.path.substring(1, listNewsModel.path.length - 1)
            newsDate.text = Helper.dateParse(listNewsModel.date)

        }

    }


}