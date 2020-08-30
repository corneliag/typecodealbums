package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.User
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.amshove.kluent.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class AlbumsRepositoryTest {

    companion object {
        private const val ID = 1L
    }

    private var localRepository = mock<ILocalRepository>()
    private var remoteRepository = mock<IRemoteRepository>()
    private lateinit var repository: IAlbumsRepository

    @Before
    fun setUp() {
        repository = AlbumsRepository(localRepository, remoteRepository)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(localRepository, remoteRepository)
    }

    @Test
    fun getUser_emptyDatabase() {
        val userList =
            listOf(User(1L, "TEST", "tester", "test@gmail.com", "01234567890", "website.com"))

        whenever(localRepository.getUsers()).thenReturn(Single.just(emptyList()))
        whenever(remoteRepository.getUsers()).thenReturn(Single.just(userList))

        val testObserver = repository.getUsers().test().assertSubscribed()
            .assertValue(userList)

        //THEN
        verify(localRepository).getUsers()
        verify(remoteRepository).getUsers()
        verify(localRepository).addUsers(userList)
        testObserver.dispose()
    }

    @Test
    fun getUser_noEmptyDatabase() {
        val userList =
            listOf(User(1L, "TEST", "tester", "test@gmail.com", "01234567890", "website.com"))

        whenever(localRepository.getUsers()).thenReturn(Single.just(userList))
        whenever(remoteRepository.getUsers()).thenReturn(Single.just(userList))

        val testObserver = repository.getUsers().test().assertSubscribed()
            .assertValue(userList)

        //THEN
        verify(localRepository).getUsers()
        verify(remoteRepository).getUsers()
        testObserver.dispose()
    }
}