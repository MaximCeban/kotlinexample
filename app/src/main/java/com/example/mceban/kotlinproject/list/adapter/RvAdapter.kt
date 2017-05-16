package com.example.mceban.kotlinproject.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mceban.kotlinproject.list.viewholder.ViewHolder
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject

abstract class RvAdapter<D, VH : ViewHolder<D>> : RecyclerView.Adapter<VH>() {
  private var list = mutableListOf<D>()
  private var subject = PublishSubject.create<View>()
  override fun getItemCount() = list.size
  final override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
    val view = LayoutInflater.from(parent?.context).inflate(getLayoutId(), parent)
    RxView.clicks(view).takeUntil<View> { RxView.detaches(parent?.rootView!!) }.map { view }.subscribe { subject }
    return createViewHolder(view)
  }

  abstract fun getLayoutId(): Int

  abstract fun createViewHolder(root: View): VH

  override fun onBindViewHolder(holder: VH, position: Int) {
    holder.bind(getItem(position))
  }

  fun setItems(items: List<D>) {
    list.clear()
    list.addAll(items)
    notifyDataSetChanged()
  }

  open fun getItem(position: Int) = list[position]
}

