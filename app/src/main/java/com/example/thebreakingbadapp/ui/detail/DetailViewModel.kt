package com.example.thebreakingbadapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thebreakingbadapp.data.Character
import com.example.thebreakingbadapp.data.Message
import com.example.thebreakingbadapp.data.Resource
import com.example.thebreakingbadapp.remote.Repository
import com.example.thebreakingbadapp.vm.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _characterLiveData = MutableLiveData<Character>()
    val characterLiveData: LiveData<Character>
        get() = _characterLiveData

    fun subscribe(character: Character) {
        // show the initial data from the list request
        _characterLiveData.value = character

        // get additional data from REST service
        disposable.add(repository.getCharacterById(character.charId).subscribe { resource ->
            when (resource) {
                is Resource.Success -> _characterLiveData.value = resource.data.first()
                is Resource.Error -> resource.message?.let { showErrorDialog(body = Message.Text(it)) }
            }
        })
    }

}
