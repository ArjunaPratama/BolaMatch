package com.arjuna.matchball

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.arjuna.matchball.Api.ApiRepository
import com.arjuna.matchball.Model.Club.TeamsItem
import com.arjuna.matchball.Model.Match.Events
import com.arjuna.matchball.Presenter.DetailMatchPresenter
import com.arjuna.matchball.Presenter.DetailMatchView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_item.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), DetailMatchView {




    private var idClubA: String = ""
    private var idClubB: String = ""
    private var idEvent: String = ""

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = "Detail Match"

        val intent = intent
        idClubA = intent.getStringExtra("ID_CLUBA")
        idClubB = intent.getStringExtra("ID_CLUBB")
        idEvent = intent.getStringExtra("ID_EVENT")
        id_match.text = idEvent
        progressBar = pBarr

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)
        presenter.getDetailClub(idClubA, idClubB)
        presenter.getDetailMatch(idEvent)
    }
    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }
    override fun showDetailClub(data1: List<TeamsItem>, data2: List<TeamsItem>) {
        txtteamA.text = data1.get(0).strTeam
        txtteamB.text = data2.get(0).strTeam

        Glide.with(this).load(data1.get(0).teamBadge).into(imgteam1)
        Glide.with(this).load(data2.get(0).teamBadge).into(imgteam2)
    }

    override fun showDetailMatch(data: List<Events>) {
        if(data.get(0).intHomeScore != null){
            var tanggalpertandingan = data.get(0).dateEvent
            var locale = Locale("ID")
            var formatwaktu = SimpleDateFormat("yyyy-M-d", locale)
            val tanggale: Date
            try {
                tanggale = formatwaktu.parse(tanggalpertandingan)
                formatwaktu = SimpleDateFormat("E, d MMM yyyy", locale)
                tanggalpertandingan = formatwaktu.format(tanggale)
            }catch (e: ParseException){
                e.printStackTrace()
            }

            txtdate.text = tanggalpertandingan


            txtnteam1.text = data.get(0).intHomeScore
            txtformation1.text = data.get(0).strHomeFormation
            txtshots1.text = data.get(0).intHomeShots
            txtgoalkeeper1.text = data.get(0).strHomeLineupGoalkeeper
            txtgoals1.text = data.get(0).strHomeGoalDetails
            txtmidfield1.text = data.get(0).strHomeLineupMidfield
            txtdefense1.text = data.get(0).strHomeLineupDefense
            txtsubstitutes1.text = data.get(0).strHomeLineupSubstitutes


            txtnteam2.text = data.get(0).intAwayScore
            txtformation2.text = data.get(0).strAwayFormation
            txtshots2.text = data.get(0).intAwayShots
            txtgoalkeeper2.text = data.get(0).strAwayLineupGoalkeeper
            txtgoals2.text = data.get(0).strAwayGoalDetails
            txtmidfield2.text = data.get(0).strAwayLineupMidfield
            txtdefense2.text = data.get(0).strAwayLineupDefense
            txtsubstitutes2.text = data.get(0).strAwayLineupSubstitutes
        }else{

        }







    }


}
