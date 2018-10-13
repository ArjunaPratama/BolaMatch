package com.arjuna.matchball.Presenter

import com.arjuna.matchball.Api.ApiRepository
import com.arjuna.matchball.Api.TheSportDBApi
import com.arjuna.matchball.Model.Club.ClubResponse
import com.arjuna.matchball.Model.Club.TeamsItem
import com.arjuna.matchball.Model.Match.Events
import com.arjuna.matchball.Model.Match.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(private val view: DetailMatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {
    fun getDetailClub(league1: String?, league2: String?){
        view.showLoading()
        doAsync {
            val data1 = gson
                    .fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailClub(league1)),
                            ClubResponse::class.java
                    )
            val data2 = gson
                    .fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailClub(league2)),
                    ClubResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showDetailClub(data1.teams as List<TeamsItem>, data2.teams as List<TeamsItem>)
            }
        }
    }

    fun getDetailMatch(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailMatch(league)),
                    MatchResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showDetailMatch(data.events as List<Events>)
            }
        }
    }
}