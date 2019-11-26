package com.peiky.moviespeiky.ui.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.*


fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun language(): String {
    return Locale.getDefault().language
}

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}


