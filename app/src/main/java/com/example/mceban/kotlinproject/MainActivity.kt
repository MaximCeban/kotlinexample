package com.example.mceban.kotlinproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.mceban.kotlinproject.list.ListActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
   var subject = PublishSubject.create<Unit>()

  internal var subscription = CompositeDisposable()
  private var count = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
//    btnStop.setOnClickListener { v -> subject.onNext(null) }
    btnNext.setOnClickListener {
      startActivity(Intent(this, ListActivity::class.java))
    }
    subscription.add(Observable.just("1").retryWhen { observable -> subject }.flatMap { s -> Observable.just(count++) }.subscribe { s -> btn_call.text = "clicked " + s })
  }

  override fun onDestroy() {
    subscription.clear()
    super.onDestroy()
  }
}
