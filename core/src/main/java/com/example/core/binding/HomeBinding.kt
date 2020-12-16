package com.example.core.binding

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.core.R
import com.example.core.model.ArtworkDimensions
import com.example.core.model.ArtworkModel

@BindingAdapter("changeFirstLettersColor")
fun changeLettersColor(view: TextView, text:String){
    val span = SpannableString(text)
    span.setSpan(ForegroundColorSpan(view.context.getColor(R.color.red_1)), 0, 2, 0)
    span.setSpan(ForegroundColorSpan(view.context.getColor(R.color.red_1)), 3, 4, 0)
    view.setText(span, TextView.BufferType.SPANNABLE)
}

@BindingAdapter("setTextOrHide")
fun setTextOrHide(view: TextView, text: String?){
    if (text != null && text != "")
        view.text = text
    else
        view.visibility = View.GONE
}

@BindingAdapter("setTextOrHide")
fun setTextOrHide(view: TextView, text: Double?){
    if (text != null)
        view.text = text.toString()
    else
        view.visibility = View.GONE
}

@BindingAdapter("hideIfNull")
fun hideIfNull(view: TextView, text: String?){
    if (text == null || text == "")
        view.visibility = View.GONE
}

@BindingAdapter("hideIfNull")
fun hideIfNull(view: TextView, text: Double?){
    if (text == null)
        view.visibility = View.GONE
}

@BindingAdapter("showIfNotNull")
fun showIfNotNull(view: TextView, text: String?){
    if (text != null && text != "")
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("showIfNotNull")
fun showIfNotNull(view: TextView, text: Double?){
    if (text != null)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("setTextAndShow")
fun setTextAndShow(view: TextView, text: String?){
    if (text != null && text != "") {
        view.visibility = View.VISIBLE
        view.text = text
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("setTextAndShow")
fun setTextAndShow(view: TextView, text: Double?){
    if (text != null) {
        view.visibility = View.VISIBLE
        view.text = text.toString()
    }else
        view.visibility = View.GONE
}

@BindingAdapter("showDimensionTittle")
fun showDimensionTitle(view: TextView, artworkModel: ArtworkModel?){
    if (artworkModel == null){
        view.visibility = View.GONE
    }else if (artworkModel.dimensions.height != null || artworkModel.dimensions.width != null || artworkModel.dimensions.depth != null || artworkModel.dimensions.diameter != null){
        view.visibility = View.VISIBLE
    }else
        view.visibility = View.GONE
}
