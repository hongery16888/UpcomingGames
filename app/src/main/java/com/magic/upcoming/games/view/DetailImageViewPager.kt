package com.magic.upcoming.games.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class DetailImageViewPager(context: Context, attr: AttributeSet? = null) : ViewPager(context, attr) {

    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        return try {
            super.onInterceptHoverEvent(event)
        } catch (e: Exception) {
            false
        }
    }

}