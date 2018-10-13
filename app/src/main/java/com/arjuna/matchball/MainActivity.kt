package com.arjuna.matchball

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.arjuna.matchball.Fragment.NextFragment
import com.arjuna.matchball.Fragment.PrevFragment
import com.arjuna.matchball.R.id.navigation_home
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                loadMatchLastFragment()
//            }
//            R.id.navigation_dashboard -> {
//                loadMatchNextFragment()
//            }
//
//        }
//        true
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadMatchLastFragment(savedInstanceState)
                    val fragment = NextFragment.newInstance()
                }
                R.id.navigation_dashboard -> {
                    loadMatchNextFragment(savedInstanceState)
                }

            }
            true
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = navigation_home


    }

    private fun loadMatchLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_main, PrevFragment(), PrevFragment::class.simpleName)
                    .commit()
        }

    }

    private fun loadMatchNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_main, NextFragment(), NextFragment::class.simpleName)
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                    .commit()

        }

    }
}
