package com.example.mceban.kotlinproject.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
  abstract fun bind(data: D)
}


