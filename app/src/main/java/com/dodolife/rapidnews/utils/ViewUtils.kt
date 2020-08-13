package com.dodolife.rapidnews.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


private val dateFormat = ThreadLocal<SimpleDateFormat?>()

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}


fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}


fun String.toDate(pattern: String = "yyyy-MM-dd'T'HH:mm:ss"): String? {
    val sdf = dateFormat.get() ?: SimpleDateFormat(pattern, Locale("id")).apply {
        dateFormat.set(this)
    }
    sdf.applyPattern(pattern)
    return try {
        sdf.parse(this).toString()
    } catch (error: ParseException) {
        return null
    }
}

fun String.formatDate(pattern: String = "yyyy-MM-dd'T'HH:mm:ss"): String {
    val sdf = dateFormat.get() ?: SimpleDateFormat(pattern, Locale("id")).apply {
        dateFormat.set(this)
    }
    sdf.applyPattern(pattern)
    with(sdf) {
        return format(this@formatDate)
    }
}