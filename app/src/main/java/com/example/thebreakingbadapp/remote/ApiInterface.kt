package com.example.thebreakingbadapp.remote

import com.example.thebreakingbadapp.data.Character
import com.example.thebreakingbadapp.util.CHARACTERS_ENDPOINT
import com.example.thebreakingbadapp.util.CHARACTER_BY_ID_ENDPOINT
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET(CHARACTERS_ENDPOINT)
    fun getCharacters(): Observable<Response<List<Character>>>

    @GET(CHARACTER_BY_ID_ENDPOINT)
    fun getCharacterById(@Path("ID") characterId: Int): Observable<Response<List<Character>>>
}
