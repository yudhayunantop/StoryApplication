package com.submission.storyapplication.assets

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.submission.storyapplication.R

class MyEditText : AppCompatEditText {

    private lateinit var buttonShowPassword: Drawable

    constructor(context: Context) : super(context) {
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    private fun init() {
        buttonShowPassword = ContextCompat.getDrawable(context, R.drawable.ic_baseline_remove_red_eye_24) as Drawable
        //setOnTouchListener(this)
    }

//    override fun onTouch(v: View?, event: MotionEvent): Boolean {
//        return false
//    }
}