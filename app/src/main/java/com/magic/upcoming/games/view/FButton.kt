package com.magic.upcoming.games.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.magic.upcoming.games.utils.FontCache

internal class FButton : AppCompatButton {
    constructor(context: Context) : super(context) {
        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        applyCustomFont(context)
    }

    private fun applyCustomFont(context: Context) {
        val customFont = FontCache.getTypeface("AsimovPrintA.otf", context)
        typeface = customFont
    }
}