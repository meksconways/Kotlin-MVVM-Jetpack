package com.mek.haberlerkotlin.tabfragment.homefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.DUNYA_PATH
import com.mek.haberlerkotlin.utils.EKONOMI_PATH
import com.mek.haberlerkotlin.utils.GUNDEM_PATH
import com.mek.haberlerkotlin.utils.SPOR_PATH
import com.mek.haberlerkotlin.viewallfragment.AllPathNewsVM
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.layout_subnews.view.*
import kotlinx.android.synthetic.main.layout_topic.*
import kotlinx.android.synthetic.main.layout_topic.view.*
import kotlinx.android.synthetic.main.list_news_fragment.*
import javax.inject.Inject


class ListNewsFragment : Fragment(), NewsSelectedListener {


    override fun setSelectedNews(model: ListNewsModel) {
        view!!.findNavController().navigate(
            R.id.action_listNewsFragment2_to_newsDetail,
            bundleOf("model" to model.id),
            null,
            null
        )
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
        MyApplication.getAppComponent(context).inject(this)

    }

    /**
     * unused inline func
     */
    private inline fun <T : View> T.afterMeasure(crossinline f: T.() -> Unit) {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (measuredWidth > 0 && measuredHeight > 0) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    f()
                }
            }
        })
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ListNewsVM::class.java)
        val mainViewModel = ViewModelProviders.of(activity!!).get(MainActivityVM::class.java)
        mainViewModel.setTitle("Hürriyet Haber")
        mainViewModel.setHasBackButton(false)
        val linLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        lyt_0.rv_topics.layoutManager = linLayoutManager
        lyt_0.rv_topics.adapter = ListNewsAdapter(this, viewModel, this)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(lyt_0.rv_topics)
        indicator.attachToRecyclerView(lyt_0.rv_topics)

        /**
         * unused object
         */
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemVisible = linLayoutManager.findFirstVisibleItemPosition()
                if (firstItemVisible != 0 && firstItemVisible % 15 == 0) {
                    recyclerView.layoutManager!!.scrollToPosition(0)
                }
            }
        }
        // lyt_0.rv_topics.addOnScrollListener(scrollListener)

        lyt_spor.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_spor.rv_subNews.adapter = SubNewsAdapter(this, viewModel)
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
        lyt_dunya.rv_subNews.adapter = SubNewsAdapter(this, viewModel, DUNYA_PATH)
        val snapHelperCountry: SnapHelper = LinearSnapHelper()
        snapHelperCountry.attachToRecyclerView(lyt_dunya.rv_subNews)

        lyt_economy.txt_title.text = "Ekonomi Haberleri"
        lyt_economy.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_economy.rv_subNews.adapter = SubNewsAdapter(this, viewModel, EKONOMI_PATH)
        val snapHelperEconomy: SnapHelper = LinearSnapHelper()
        snapHelperEconomy.attachToRecyclerView(lyt_economy.rv_subNews)

        lyt_journal.txt_title.text = "Gündem Haberleri"
        lyt_journal.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_journal.rv_subNews.adapter = SubNewsAdapter(this, viewModel, GUNDEM_PATH)
        val snapHelperJournal: SnapHelper = LinearSnapHelper()
        snapHelperJournal.attachToRecyclerView(lyt_journal.rv_subNews)

        observeViewModel()

    }

    private fun clickViewAll(cat: String) {

        val pathNewsViewModel = ViewModelProviders.of(activity!!,factory).get(AllPathNewsVM::class.java)
        pathNewsViewModel.setPathData(cat)

        view!!.findNavController().navigate(R.id.action_listNewsFragment2_to_allPathNewsFragment)


    }

    private fun observeViewModel() {
        viewModel.getExecutionCount().observe(this, Observer { count ->

            if (count > 4) {
                progressBar.visibility = View.GONE
                scrollView.visibility = View.VISIBLE
            } else {
                scrollView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        })

    }

}
