package com.example.thebreakingbadapp.remote

import com.example.thebreakingbadapp.data.Character
import io.reactivex.Observable
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

class TestRepository {

    @Mock
    lateinit var apiInterface: ApiInterface

    lateinit var repository: RepositoryImpl //object under test

    private val dummyData = listOf(
        Character(
            1,
            "Walter White", "09-07-1958",
            listOf("High School Chemistry Teacher", "Meth King Pin"),
            "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg",
            "Presumed dead",
            "Heisenberg",
            listOf("1", "2", "3", "4", "5"),
            "Bryan Cranston",
            "Breaking Bad",
            emptyList()
        ),
        Character(
            2,
            "Jesse Pinkman",
            "09-24-1984",
            listOf("Meth Dealer"),
            "https://vignette.wikia.nocookie.net/breakingbad/images/9/95/JesseS5.jpg/revision/latest?cb=20120620012441",
            "Alive",
            "Cap n' Cook",
            listOf("1", "2", "3", "4", "5"),
            "Aaron Paul",
            "Breaking Bad",
            emptyList()
        )
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = RepositoryImpl(apiInterface)
    }

    @Test
    fun properSetUp_notNull() {
        assertNotNull(apiInterface)
        assertNotNull(repository)
    }


    @Test
    fun getCharacterList_invokesRequest() {
        // given
        val expectedResult = Observable.just(Response.success(emptyList<Character>()))

        `when`(apiInterface.getCharacters()).thenReturn(expectedResult)

        // when
        repository.getCharacters()

        // then
        verify(apiInterface, times(1)).getCharacters()
    }


    @Test
    fun getCharacters() {
        // given
        val expectedResult = Observable.just(Response.success(dummyData))
        `when`(apiInterface.getCharacters()).thenReturn(expectedResult)

        // when
        repository.getCharacters()

        // then
        verify(apiInterface, times(1)).getCharacters()
    }

    @Test
    fun getCharacterById() {
        // given
        val expectedResult = Observable.just(Response.success(dummyData.subList(0, 1)))
        `when`(apiInterface.getCharacterById(1)).thenReturn(expectedResult)

        // when
        repository.getCharacterById(1)

        // then
        verify(apiInterface, times(1)).getCharacterById(1)
        verify(apiInterface, times(0)).getCharacters()
    }

}