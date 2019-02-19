package com.mek.haberlerkotlin.tabfragment.homefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import com.mek.haberlerkotlin.utils.DUNYA_PATH
import com.mek.haberlerkotlin.utils.EKONOMI_PATH
import com.mek.haberlerkotlin.utils.GUNDEM_PATH
import com.mek.haberlerkotlin.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.layout_subnews.view.*
import kotlinx.android.synthetic.main.layout_topic.*
import kotlinx.android.synthetic.main.layout_topic.view.*
import kotlinx.android.synthetic.main.list_news_fragment.*
import javax.inject.Inject


class ListNewsFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ListNewsVM::class.java)
        lyt_0.rv_topics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        lyt_0.rv_topics.adapter = ListNewsAdapter(this, viewModel)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(lyt_0.rv_topics)
        indicator.attachToRecyclerView(lyt_0.rv_topics)

        lyt_spor.rv_subNews.layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.HORIZONTAL, false
        )
        lyt_spor.rv_subNews.adapter = SubNewsAdapter(this, viewModel)
        val snapHelperSports: SnapHelper = LinearSnapHelper()
        snapHelperSports.attachToRecyclerView(lyt_spor.rv_subNews)

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

//        btn_goToChild.setOnClickListener{
//            it.findNavController().navigate(R.id.action_listNewsFragment2_to_galleryFragment3)
//        }


    }

    private fun observeViewModel() {
        viewModel.getLoading().observe(this, Observer { loading ->
            run {
                if (loading) {
                    //progressBar.visibility = View.VISIBLE
                    //rv_listNews.visibility = View.GONE
                } else {
                    //progressBar.visibility = View.GONE
                    //rv_listNews.visibility = View.VISIBLE
                }
            }
        })
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
