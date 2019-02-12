package com.mek.haberlerkotlin.tabfragment.homefragment

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.mek.haberlerkotlin.R
import com.mek.haberlerkotlin.base.MyApplication
import kotlinx.android.synthetic.main.list_news_fragment.*


class ListNewsFragment : Fragment() {

    companion object {
        fun newInstance(i: Int) = ListNewsFragment()
    }
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
        viewModel = ViewModelProviders.of(this).get(ListNewsVM::class.java)

        btn_goToChild.setOnClickListener{
            it.findNavController().navigate(R.id.action_listNewsFragment2_to_galleryFragment3)
        }


    }

}
