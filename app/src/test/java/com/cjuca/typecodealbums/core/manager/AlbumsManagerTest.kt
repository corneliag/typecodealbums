package com.cjuca.typecodealbums.core.manager

import com.cjuca.typecodealbums.core.repository.IAlbumsRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.amshove.kluent.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verifyNoMoreInteractions

class AlbumsManagerTest {

    companion object {
        private const val ID = 1L
    }

    private var repository = mock<IAlbumsRepository>()
    private lateinit var manager: IAlbumsManager

    @Before
    fun setUp() {
        manager = AlbumsManager(repository)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getUsers() {
        whenever(repository.getUsers()).thenReturn(Single.just(emptyList()))
        val testObserver = manager.getUsers().test().assertSubscribed()
            .assertValue(emptyList())

        //THEN
        verify(repository).getUsers()
        testObserver.dispose()
    }

    @Test
    fun getAlbums() {
        whenever(repository.getAlbums(ID)).thenReturn(Single.just(emptyList()))
        val testObserver = manager.getAlbums(ID).test().assertSubscribed()
            .assertValue(emptyList())

        //THEN
        verify(repository).getAlbums(ID)
        testObserver.dispose()
    }

    @Test
    fun getPhotos() {
        whenever(repository.getPhotos(ID)).thenReturn(Single.just(emptyList()))
        val testObserver = manager.getPhotos(ID).test().assertSubscribed()
            .assertValue(emptyList())

        //THEN
        verify(repository).getPhotos(ID)
        testObserver.dispose()
    }
}