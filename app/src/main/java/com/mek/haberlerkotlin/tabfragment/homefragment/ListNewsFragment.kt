package com.mek.haberlerkotlin.tabfragment.homefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.getAppComponent
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.DUNYA_PATH
import com.mek.haberlerkotlin.utils.EKONOMI_PATH
import com.mek.haberlerkotlin.utils.GUNDEM_PATH
import com.mek.haberlerkotlin.utils.SPOR_PATH
import com.mek.haberlerkotlin.viewallfragment.AllPathNewsVM
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.gallery_fragment.*
import kotlinx.android.synthetic.main.layout_subnews.view.*
import kotlinx.android.synthetic.main.layout_topic.*
import kotlinx.android.synthetic.main.layout_topic.view.*
import kotlinx.android.synthetic.main.list_news_fragment.*
import javax.inject.Inject


class ListNewsFragment : Fragment(), NewsSelectedListener {


    override fun setSelectedNews(model: ListNewsModel) {

        try {


            progressBar.findNavController().navigate(
                R.id.go_to_newsDetail,
                bundleOf("model" to model.id),
                null,
                null
            )

        } catch (e: Exception) {
            e.printStackTrace()

        }


    }

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: ListNewsVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.list_news_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().inject(this)

    }

    lateinit var mainViewModel: MainActivityVM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        mainViewModel.setTitle("Haberler")
        mainViewModel.setHasBackButton(false)


        viewModel = ViewModelProviders.of(activity!!, factory).get(ListNewsVM::class.java)
        val linLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //lyt_0.rv_topics.layoutManager = linLayoutManager
        lyt_0.rv_topics.layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
        lyt_0.rv_topics.adapter = ListNewsAdapter(this, viewModel, this)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(lyt_0.rv_topics)
        indicator.attachToRecyclerView(lyt_0.rv_topics)

        lyt_spor.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_spor.rv_subNews.adapter = SubNewsAdapter(this, viewModel, SPOR_PATH,this)
        val snapHelperSports: SnapHelper = LinearSnapHelper()
        snapHelperSports.attachToRecyclerView(lyt_spor.rv_subNews)

        lyt_spor.txt_viewAll.setOnClickListener {
            clickViewAll(SPOR_PATH)
        }
        lyt_journal.txt_viewAll.setOnClickListener {
            clickViewAll(GUNDEM_PATH)
        }
        lyt_economy.txt_viewAll.setOnClickListener {
            clickViewAll(EKONOMI_PATH)
        }
        lyt_dunya.txt_viewAll.setOnClickListener {
            clickViewAll(DUNYA_PATH)
        }


        lyt_dunya.txt_title.text = "Dünya Haberleri"
        lyt_dunya.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_dunya.rv_subNews.adapter = SubNewsAdapter(this, viewModel, DUNYA_PATH,this)
        val snapHelperCountry: SnapHelper = LinearSnapHelper()
        snapHelperCountry.attachToRecyclerView(lyt_dunya.rv_subNews)

        lyt_economy.txt_title.text = "Ekonomi Haberleri"
        lyt_economy.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_economy.rv_subNews.adapter = SubNewsAdapter(this, viewModel, EKONOMI_PATH,this)
        val snapHelperEconomy: SnapHelper = LinearSnapHelper()
        snapHelperEconomy.attachToRecyclerView(lyt_economy.rv_subNews)

        lyt_journal.txt_title.text = "Gündem Haberleri"
        lyt_journal.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_journal.rv_subNews.adapter = SubNewsAdapter(this, viewModel, GUNDEM_PATH,this)
        val snapHelperJournal: SnapHelper = LinearSnapHelper()
        snapHelperJournal.attachToRecyclerView(lyt_journal.rv_subNews)

        observeViewModel()

    }

    private fun clickViewAll(cat: String) {

        val pathNewsViewModel = ViewModelProviders.of(activity!!, factory).get(AllPathNewsVM::class.java)
        pathNewsViewModel.setPathData(cat)
        pathNewsViewModel.isFirst = true

        try {
            progressBar.findNavController().navigate(R.id.go_to_allPathNewsFragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun observeViewModel() {
        viewModel.getExecutionCount().observe(this, Observer { count ->

            if (count > 4) {
                lyt_0.rv_topics.hideShimmerAdapter()
                lyt_dunya.rv_subNews.hideShimmerAdapter()
                lyt_economy.rv_subNews.hideShimmerAdapter()
                lyt_spor.rv_subNews.hideShimmerAdapter()
                lyt_journal.rv_subNews.hideShimmerAdapter()
                mainViewModel.setBottomBarBehavior(true)

            } else {
                lyt_0.rv_topics.showShimmerAdapter()
                lyt_dunya.rv_subNews.showShimmerAdapter()
                lyt_economy.rv_subNews.showShimmerAdapter()
                lyt_spor.rv_subNews.showShimmerAdapter()
                lyt_journal.rv_subNews.showShimmerAdapter()
            }
        })

    }

}
