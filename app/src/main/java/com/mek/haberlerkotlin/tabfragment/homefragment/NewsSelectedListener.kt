package com.mek.haberlerkotlin.tabfragment.homefragment

import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel

interface NewsSelectedListener {

    fun setSelectedNews(model: ListNewsModel)

}