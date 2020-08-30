package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.dao.AlbumDao
import com.cjuca.typecodealbums.core.dao.PhotoDao
import com.cjuca.typecodealbums.core.dao.UserDao
import com.cjuca.typecodealbums.core.model.User
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBe
import org.junit.After
import org.junit.Before
import org.junit.Test

class LocalRepositoryTest {

    private val userDao = mock<UserDao>()
    private val albumDao = mock<AlbumDao>()
    private val photoDao = mock<PhotoDao>()

    private lateinit var localRepository: ILocalRepository

    @Before
    fun setUp() {
        localRepository = LocalRepository(userDao, albumDao, photoDao)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(userDao, albumDao, photoDao)
    }

    @Test
    fun getUsers_emptyList() {
        //GIVEN
        whenever(userDao.getUsers()).thenReturn(emptyList())

        //WHEN
        val testObserver = localRepository.getUsers().test()

        //THEN
        verify(userDao).getUsers()
        testObserver.assertSubscribed()
            .assertNoErrors()
            .valueCount()
            .shouldBe(0)
    }

    @Test
    fun getUsers() {
        //GIVEN
        val userList =
            listOf(User(1L, "TEST", "tester", "test@gmail.com", "01234567890", "website.com"))
        whenever(userDao.getUsers()).thenReturn(userList)

        //WHEN
        val testObserver = localRepository.getUsers().test()

        //THEN
        verify(userDao).getUsers()
        testObserver.assertSubscribed()
            .assertNoErrors()
            .valueCount()
            .shouldBe(1)
    }

    @Test
    fun insertUsers() {
        //TODO
    }
}