package com.arjuna.matchball.Fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.arjuna.matchball.Adapter.NextMatchAdapter
import com.arjuna.matchball.Api.ApiRepository
import com.arjuna.matchball.DetailActivity
import com.arjuna.matchball.Model.EventsItem
import com.arjuna.matchball.Model.mainview
import com.arjuna.matchball.Presenter.MatchNextPresenter
import com.arjuna.matchball.Presenter.MatchPrevPresnter

import com.arjuna.matchball.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh


/**
 * A simple [Fragment] subclass.
 *
 */
class PrevFragment : Fragment(), mainview {

    private  var events: MutableList<EventsItem> = mutableListOf()
    private lateinit var presenter: MatchPrevPresnter
    private lateinit var  adapter: NextMatchAdapter
    private lateinit var  MatchName: String
    private lateinit var  Listnya: RecyclerView
    private lateinit var  progressBar: ProgressBar
    private lateinit var  spinner: Spinner
    private lateinit var  swipeRefreshLayout: SwipeRefreshLayout

//    companion object {
//        fun newInstance(): NextFragment{
//            var fragmentNext = NextFragment()
//            var pendapat = Bundle()
//            fragmentNext.arguments = pendapat
//            return fragmentNext
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_next, container, false)
        val kegiatan  = activity

        adapter = NextMatchAdapter(context, events){
            startActivity(intentFor<DetailActivity>(
                    "ID_CLUBA" to it.idHomeTeam,
                    "ID_CLUBB" to it.idAwayTeam,
                    "ID_EVENT" to it.idEvent
            ))
        }

        Listnya = view.findViewById(R.id.rcListNext)
        Listnya.layoutManager = LinearLayoutManager(kegiatan)
        Listnya.adapter = adapter

        spinner = view.spinnernya
        swipeRefreshLayout = view.swipe
        progressBar = view.pBar


        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerItemsId = resources.getStringArray(R.array.league_id)
        spinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val getLeague = spinner.selectedItemPosition
                MatchName = spinnerItemsId[getLeague].toString()
                presenter.getPrevList(MatchName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        swipeRefreshLayout.onRefresh {
            presenter.getPrevList(MatchName)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPrevPresnter(this, request, gson)
        return  view
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatchList(data: List<EventsItem>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }



}
