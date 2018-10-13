package com.arjuna.matchball.Presenter

import com.arjuna.matchball.Model.Club.TeamsItem
import com.arjuna.matchball.Model.Match.Events

interface DetailMatchView {
    fun showLoading()

    fun showDetailClub(data1: List<TeamsItem>, data2: List<TeamsItem>)

    fun showDetailMatch(data: List<Events>)

    fun hideLoading()

}