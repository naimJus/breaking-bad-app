package com.example.thebreakingbadapp.remote

import com.example.thebreakingbadapp.data.Character
import com.example.thebreakingbadapp.data.Resource
import io.reactivex.Observable

/**
 * Test double which will be provide fake data for Testing purposes
 */
class FakeTestRepository(
    var charactersList: List<Character>,
    var charactersObservable: Observable<Resource<List<Character>>>,
    var characterObservable: Observable<Resource<List<Character>>>
) : Repository {
    override fun getCharacters(): Observable<Resource<List<Character>>> = charactersObservable


    override fun getCharacterById(id: Int): Observable<Resource<List<Character>>> =
        characterObservable


}