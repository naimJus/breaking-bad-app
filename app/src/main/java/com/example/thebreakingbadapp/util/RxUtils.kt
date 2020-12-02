package com.example.thebreakingbadapp.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.applySchedulers(): Observable<T> = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
