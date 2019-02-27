package com.mek.haberlerkotlin.tabfragment.galleryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.MainGalleryFragment
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.MainGalleryVM
import com.mek.haberlerkotlin.utils.DUNYA_PATH
import com.mek.haberlerkotlin.utils.EKONOMI_PATH
import com.mek.haberlerkotlin.utils.SPOR_PATH

class GalleryMainAdapter(
    val fragment: GalleryFragment,
    private val viewModel: GalleryVM,
    owner: LifecycleOwner,
    vm: MainGalleryVM
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MenuItemSelectedListener {

    private var newsType = "tümü"

    override fun setItemSelected(type: String) {
        this.newsType = type
        viewModel.setNewsType(type)
    }

    init {

        viewModel.getNewsType().observe(owner, Observer { type ->
            vm.setNewsType(type)
        })

    }

    private val galleryMenuItem = mutableListOf<GalleryMenuModel>()
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

        val container: FrameLayout = itemView.findViewById(R.id.containerSub)
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
                        true,
                        "tümü"

                    )
                )
                add(
                    GalleryMenuModel(
                        holderMenu.recyclerView.resources.getColor(R.color.secondaryDarkColor),
                        holderMenu.recyclerView.resources.getDrawable(R.drawable.ic_money),
                        "Ekonomi",
                        false,
                        EKONOMI_PATH

                    )
                )
                add(
                    GalleryMenuModel(
                        holderMenu.recyclerView.resources.getColor(R.color.teal),
                        holderMenu.recyclerView.resources.getDrawable(R.drawable.ic_dunya),
                        "Dünya",
                        false,
                        DUNYA_PATH

                    )
                )
                add(
                    GalleryMenuModel(
                        holderMenu.recyclerView.resources.getColor(R.color.turuncu),
                        holderMenu.recyclerView.resources.getDrawable(R.drawable.ic_sport),
                        "Spor",
                        false,
                        SPOR_PATH

                    )
                )

            }
            holderMenu.recyclerView.adapter = GalleryMenuAdapter(galleryMenuItem, this)


        } else {

            val fragmentManager: FragmentManager = (fragment.activity)!!.supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.containerSub, MainGalleryFragment.newInstance(newsType))
                .disallowAddToBackStack()
                .commit()


        }

    }
}