package com.arjuna.matchball.Presenter

import com.arjuna.matchball.Api.ApiRepository
import com.arjuna.matchball.Api.TheSportDBApi
import com.arjuna.matchball.Model.EventResponse
import com.arjuna.matchball.Model.EventsItem
import com.arjuna.matchball.Model.mainview
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchNextPresenter(private val view: mainview,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {
    fun getNextList(league: String?) {
        view.showLoading()
        doAsync {
            val data= gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatch(league)),
                    EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events as List<EventsItem>)
            }
        }
    }
}