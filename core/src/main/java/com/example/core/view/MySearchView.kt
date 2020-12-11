package com.example.core.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.doOnTextChanged
import com.example.core.R
import io.reactivex.Observable

class MySearchView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle:Int = 0)
    :FrameLayout(context, attributeSet, defStyle) {

    private val editText: AppCompatEditText
    private val filterBtn: AppCompatImageButton

    init {
        inflate(context, R.layout.my_search_view, this)
        editText = findViewById(R.id.my_search_edit_text)
        filterBtn = findViewById(R.id.my_search_filter_btn)

        editText.clearFocus()
        filterBtn.requestFocus()
    }


    fun addTextChangeListener(l:(
            text: CharSequence?,
            start: Int,
            before: Int,
            count: Int
    ) -> Unit ){
        editText.doOnTextChanged(l)
    }

    fun addFilterBtnListener(l:View.OnClickListener){
        filterBtn.setOnClickListener(l)
    }

    fun blockFocus(){
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
    }

    fun addTextFieldClickListener(l: View.OnClickListener){
        editText.setOnClickListener(l)
    }

    fun getSearchInputText(): Editable? {
        return editText.text
    }

    fun setSearchQuery(q:String?){
        q?.let {
            editText.setText(it, TextView.BufferType.EDITABLE)
        }
    }

    fun addEditorActionListener(l: TextView.OnEditorActionListener){
        editText.setOnEditorActionListener(l)
    }

    fun getObservableOnTextInput():Observable<CharSequence>{
        return Observable.create<CharSequence> { emitter ->
            val watcher = object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (!emitter.isDisposed){
                        emitter.onNext(s ?: "")
                    }
                }
            }
            emitter.setCancellable { editText.removeTextChangedListener(watcher) }
            editText.addTextChangedListener(watcher)
        }
    }
}