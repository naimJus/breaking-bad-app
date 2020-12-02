package com.example.thebreakingbadapp.remote

import androidx.annotation.VisibleForTesting
import com.example.thebreakingbadapp.data.Character
import com.example.thebreakingbadapp.data.Resource
import com.example.thebreakingbadapp.util.applySchedulers
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) : Repository {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val cachedCharacterList: MutableList<Character> by lazy { mutableListOf() }

    override fun getCharacters(): Observable<Resource<List<Character>>> {
        if (cachedCharacterList.isNullOrEmpty()) {
            return apiInterface.getCharacters()
                .map {
                    if (it.isSuccessful && it.body() != null) {
                        cachedCharacterList.clear()
                        cachedCharacterList.addAll(it.body()!!)
                        Resource.Success(it.body()!!)
                    } else {
                        Resource.Error(it.message())
                    }
                }
                .doOnError {
                    Resource.Error(it.message, it)
                }
                .onErrorReturn {
                    Resource.Error(it.message, it)
                }.applySchedulers()
        } else {
            return Observable.just(Resource.Success(cachedCharacterList))
        }
    }

    override fun getCharacterById(id: Int): Observable<Resource<List<Character>>> {
        return apiInterface.getCharacterById(id)
            .map {
                if (it.isSuccessful && it.body() != null) {
                    Resource.Success(it.body()!!)
                } else {
                    Resource.Error(it.message())
                }
            }
            .doOnError {
                Resource.Error(it.message, it)
            }
            .onErrorReturn {
                Resource.Error(it.message, it)
            }.applySchedulers()
    }

}

interface Repository {
    fun getCharacters(): Observable<Resource<List<Character>>>

    fun getCharacterById(id: Int): Observable<Resource<List<Character>>>
}