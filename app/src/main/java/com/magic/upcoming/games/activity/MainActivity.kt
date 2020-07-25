package com.magic.upcoming.games.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseFragmentAdapter
import com.magic.upcoming.games.fragment.FavoriteFragment
import com.magic.upcoming.games.fragment.GameFragment
import com.magic.upcoming.games.fragment.GameMainFragment
import com.magic.upcoming.games.view.VerticalViewpager
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val container by lazy { findViewById<VerticalViewpager>(R.id.container) }
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }
    private val list: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add(GameMainFragment())
        list.add(FavoriteFragment())
        list.add(FavoriteFragment())
        list.add(FavoriteFragment())


        container.setScanScroll(false)
        container.offscreenPageLimit = 4
        container.adapter = BaseFragmentAdapter(supportFragmentManager, list)

        menu.setItemSelected(0)

        menu.setOnItemSelectedListener {
            when(it){
                R.id.home -> container.currentItem = 0
                R.id.video -> container.currentItem = 1
                R.id.favorites -> container.currentItem = 2
                R.id.settings -> container.currentItem = 3
            }
        }
    }

}