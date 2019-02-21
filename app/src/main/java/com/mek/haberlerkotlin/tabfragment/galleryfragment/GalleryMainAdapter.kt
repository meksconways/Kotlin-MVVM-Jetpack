package com.mek.haberlerkotlin.tabfragment.galleryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mek.haberlerkotlin.R

class GalleryMainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var type_menu = 0
    private var type_main = 1


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            type_menu
        } else {
            type_main
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == type_menu) {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_gallery_menu, parent, false)
            return GalleryMenuVH(view)

        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_gallery_main_page, parent, false)
            return GalleryMainPageVH(view)
        }

    }

    class GalleryMenuVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }

        val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_galleryMenu)

    }

    class GalleryMainPageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {

            val holderMenu = holder as GalleryMenuVH
            holderMenu.recyclerView.layoutManager = LinearLayoutManager(holderMenu.recyclerView.context)
            val galleryMenuItem = ArrayList<GalleryMenuModel>()
            galleryMenuItem.run {
                add(
                        GalleryMenuModel(
                            holderMenu.recyclerView.resources.getColor(R.color.primaryColor),
                            holderMenu.recyclerView.resources.getDrawable(R.drawable.cat_dunya),
                            "Dünya"
                        )
                    )
                add(
                        GalleryMenuModel(
                            holderMenu.recyclerView.resources.getColor(R.color.primaryColor),
                            holderMenu.recyclerView.resources.getDrawable(R.drawable.cat_ekonomi),
                            "Ekonomi"
                        )
                    )
                add(
                        GalleryMenuModel(
                            holderMenu.recyclerView.resources.getColor(R.color.primaryColor),
                            holderMenu.recyclerView.resources.getDrawable(R.drawable.cat_spor),
                            "Spor"
                        )
                    )
            }
            holderMenu.recyclerView.adapter = GalleryMenuAdapter(galleryMenuItem)

        } else {



        }
    }
}