package com.magic.upcoming.games.listener

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.magic.upcoming.games.activity.game.GameDetailActivity
import com.magic.upcoming.games.constant.IconType

object BindingClickListener {

    @JvmStatic
    fun onGameClickType(view: View, guid: String) {
        view.context.startActivity(Intent(view.context, GameDetailActivity::class.java).apply { putExtra("guid", guid) })
    }

    @JvmStatic
    fun onSearchClickType(view: View, guid: String, resource: String) {
        when(resource){
            IconType.Game.type -> view.context.startActivity(Intent(view.context, GameDetailActivity::class.java).apply { putExtra("guid", guid) })
            IconType.Video.type ->{
                Toast.makeText(view.context, "OnClick Video Item!", Toast.LENGTH_LONG).show()
            }
        }

    }

}