package com.example.thebreakingbadapp.vm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thebreakingbadapp.R
import com.example.thebreakingbadapp.data.Event
import com.example.thebreakingbadapp.data.Message

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    private val disposableDelegate = lazy { CompositeDisposable() }
    protected val disposable by disposableDelegate

    private val _errorData = MutableLiveData<Event<Pair<Message, Message>>>()
    val errorData: LiveData<Event<Pair<Message, Message>>>
        get() = _errorData

    override fun onCleared() {
        super.onCleared()
        if (disposableDelegate.isInitialized()) {
            disposable.clear()
        }
    }

    protected fun showErrorDialog(title: Message = Message.Resource(R.string.something_went_wrong), body: Message) {
        _errorData.postValue(Event(title to body))
    }
}