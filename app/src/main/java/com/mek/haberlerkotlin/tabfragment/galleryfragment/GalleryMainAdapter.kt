package com.mek.haberlerkotlin.tabfragment.galleryfragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.home.MainActivity
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.MainGalleryFragment
import kotlin.coroutines.coroutineContext

class GalleryMainAdapter constructor(val fragment: GalleryFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), MenuItemSelectedListener {


    init {

    }

    private val galleryMenuItem = mutableListOf<GalleryMenuModel>()
    private var position = 0
    override fun setItemSelected(
        position: Int,
        galleryMenuAdapter: GalleryMenuAdapter
    ) {
        this.position = position
        galleryMenuAdapter.notifyDataSetChanged()
    }


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

        return if (viewType == type_menu) {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_gallery_menu, parent, false)
            GalleryMenuVH(view)

        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_gallery_main_page, parent, false)
            GalleryMainPageVH(view)
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
        val container: FrameLayout = itemView.findViewById(R.id.container)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {

            val holderMenu = holder as GalleryMenuVH
            holderMenu.recyclerView.layoutManager = LinearLayoutManager(holderMenu.recyclerView.context)

            galleryMenuItem.run {
                add(
                    GalleryMenuModel(
                        holderMenu.recyclerView.resources.getColor(R.color.mavi),
                        holderMenu.recyclerView.resources.getDrawable(R.drawable.ic_tum_haberler),
                        "Tümü",
                        true
                    )
                )
                add(
                    GalleryMenuModel(
                        holderMenu.recyclerView.resources.getColor(R.color.secondaryDarkColor),
                        holderMenu.recyclerView.resources.getDrawable(R.drawable.ic_money),
                        "Ekonomi",
                        false
                    )
                )
                add(
                    GalleryMenuModel(
                        holderMenu.recyclerView.resources.getColor(R.color.teal),
                        holderMenu.recyclerView.resources.getDrawable(R.drawable.ic_dunya),
                        "Dünya",
                        false
                    )
                )
                add(
                    GalleryMenuModel(
                        holderMenu.recyclerView.resources.getColor(R.color.turuncu),
                        holderMenu.recyclerView.resources.getDrawable(R.drawable.ic_sport),
                        "Spor",
                        false
                    )
                )

            }
            holderMenu.recyclerView.adapter = GalleryMenuAdapter(galleryMenuItem, this)


        }else{

            val holderMain = holder as GalleryMainPageVH
            val fragmentManager: FragmentManager = (fragment.activity)!!.supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.container,MainGalleryFragment.newInstance())
                .disallowAddToBackStack()
                .commit()


        }

    }
}