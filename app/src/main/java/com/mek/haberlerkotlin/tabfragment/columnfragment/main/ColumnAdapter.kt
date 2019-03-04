package com.mek.haberlerkotlin.tabfragment.columnfragment.main

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

class ColumnAdapter constructor(
    lifecycleOwner: LifecycleOwner,
    viewModel: ColumnVM,
    private val listener: ColumnSelectedListener
) : RecyclerView.Adapter<ColumnAdapter.ColumnAdapterVH>() {


    init {
        viewModel.getData().observe(lifecycleOwner, Observer { data ->
            dataList.clear()
            if (data != null) {
                dataList.addAll(data)
            }
            notifyDataSetChanged()
        })
    }

    var dataList = ArrayList<ColumnModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColumnAdapterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_columns, parent, false)
        return ColumnAdapterVH(view,listener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ColumnAdapterVH, position: Int) {
        holder.bind(dataList[position])
    }


    class ColumnAdapterVH(
        itemView: View,
        listener: ColumnSelectedListener
    ) : RecyclerView.ViewHolder(itemView) {

        lateinit var model: ColumnModel

        init {
            itemView.setOnClickListener {
                listener.setSelectedColumn(model.id)
            }
        }

        private val profileImage: ImageView = itemView.findViewById(R.id.img_person)
        private val title: TextView = itemView.findViewById(R.id.txt_title)
        private val desc: TextView = itemView.findViewById(R.id.txt_desc)
        private val name: TextView = itemView.findViewById(R.id.txt_writer)

        fun bind(model: ColumnModel) {
            this.model = model
            if (model.files.isNotEmpty()) {
                profileImage.apply {
                    Glide.with(this)
                        .load(model.files[0].fileUrl)
                        .apply(RequestOptions().circleCrop().placeholder(R.drawable.ic_person))
                        .into(this)
                }
            }

            title.text = model.title
            desc.text = model.desc
            name.text = model.name
        }


    }

}