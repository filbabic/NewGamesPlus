package com.babic.filip.core.common

import android.support.design.widget.BottomNavigationView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

inline fun View.onClick(crossinline onClickHandler: () -> Unit) {
    setOnClickListener { onClickHandler() }
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(isVisible) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

fun EditText.onTextChanged(onTextChangedHandler: (String) -> Unit) {
    addTextChangedListener(SimpleTextWatcher(onTextChangedHandler))
}

class SimpleTextWatcher(private inline val onTextChangedHandler: (String) -> Unit) : TextWatcher {
    override fun afterTextChanged(input: Editable?) {
        input?.run { onTextChangedHandler(toString()) }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
}


inline fun BottomNavigationView.onNavigationItemSelected(crossinline onItemSelectedHandler: (Int) -> Unit) {
    setOnNavigationItemSelectedListener {
        onItemSelectedHandler(it.itemId)
        true
    }
}

inline fun BottomNavigationView.onNavigationItemReselected(crossinline onItemReselected: () -> Unit) {
    setOnNavigationItemReselectedListener { onItemReselected() }
}