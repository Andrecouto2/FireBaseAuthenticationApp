package com.firebase.andrecouto.myapplication.extensions

import android.support.design.widget.TextInputLayout

fun TextInputLayout.getText() : String {
    return editText!!.text.toString()
}