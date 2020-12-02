package com.example.thebreakingbadapp.ui.list

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thebreakingbadapp.data.Character
import com.example.thebreakingbadapp.data.Event
import com.example.thebreakingbadapp.data.Message
import com.example.thebreakingbadapp.data.Resource
import com.example.thebreakingbadapp.remote.Repository
import com.example.thebreakingbadapp.vm.BaseViewModel
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _charactersListData = MutableLiveData<List<Character>>()
    val charactersListData: LiveData<List<Character>>
        get() = _charactersListData

    private val _navigateToDetailData = MutableLiveData<Event<Pair<ImageView, Character>>>()
    val navigateToDetailData: LiveData<Event<Pair<ImageView, Character>>>
        get() = _navigateToDetailData

    fun subscribe() {
        disposable.add(repository.getCharacters().subscribe { resource ->
            when (resource) {
                is Resource.Success -> _charactersListData.value = resource.data
                is Resource.Error -> resource.message?.let { showErrorDialog(body = Message.Text(it)) }
            }
        })
    }

    fun itemSelected(imageView: ImageView, character: Character) {
        _navigateToDetailData.value = Event(imageView to character)
    }

}