package com.example.core.binding

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.core.R

@BindingAdapter("changeFirstLettersColor")
fun changeLettersColor(view: TextView, text:String){
    Log.e("TEXT", text)
    val span = SpannableString(text)
    span.setSpan(ForegroundColorSpan(view.context.getColor(R.color.red_1)), 0, 2, 0)
    span.setSpan(ForegroundColorSpan(view.context.getColor(R.color.red_1)), 3, 4, 0)
    view.setText(span, TextView.BufferType.SPANNABLE)
}
