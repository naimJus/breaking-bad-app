package com.example.thebreakingbadapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import com.example.thebreakingbadapp.data.Character
import com.example.thebreakingbadapp.ui.list.CharacterAdapter
import com.example.thebreakingbadapp.ui.list.ListViewModel
import okhttp3.internal.format

@BindingAdapter(value = ["items", "viewModel"], requireAll = true)
fun items(
    recyclerView: RecyclerView,
    list: List<Character>?,
    viewModel: ListViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = CharacterAdapter(viewModel, list)
    } else {
        (recyclerView.adapter as CharacterAdapter).list = list
        (recyclerView.adapter as CharacterAdapter).notifyDataSetChanged()
    }
    recyclerView.isVisible = !list.isNullOrEmpty()
}

@BindingAdapter(value = ["drawableUrl"])
fun setDrawableUrl(view: ImageView, url: String?) {
    url?.let { view.load(it) }
}

@BindingAdapter(value = ["backgroundImageUrl"])
fun setBackgroundImageUrl(view: ImageView, url: String?) {
    url?.let {
        view.load(it) {
            crossfade(true)
            transformations(
                BlurTransformation(
                    context = view.context.applicationContext,
                    radius = 20f
                )
            )
        }
    }
}

@BindingAdapter(value = ["setListText", "setFormat"], requireAll = true)
fun setListText(view: TextView, list: List<String>, format: String) {
    if (list.isNotEmpty()) {
        view.visibility = View.VISIBLE
        view.text = format(format, list.joinToString())
    } else {
        view.visibility = View.GONE
    }
}