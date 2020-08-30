package com.cjuca.typecodealbums.albums.data

import com.cjuca.typecodealbums.core.model.User
import com.cjuca.typecodealbums.albums.data.EmptyItem
import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.users.data.UserUiItem
import com.cjuca.typecodealbums.users.data.UsersUiDataMapper
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Test


class AlbumsUiDataMapperTest {

    private val mapper = AlbumsUiDataMapper()

    @Test
    fun map_emptyList() {
        val result = mapper.mapToUiData(emptyList())
        result.size.shouldBe(1)
        result[0].shouldBeInstanceOf(EmptyItem::class.java)
    }

    @Test
    fun map() {
        val list =
            listOf(
                Album(1L, 1L, "album1"),
                Album(2L, 2L, "album2")
            )
        val result = mapper.mapToUiData(list)
        result.size.shouldBe(2)
        result[0].shouldBeInstanceOf(AlbumUiItem::class.java)
        (result[0] as AlbumUiItem).title.shouldEqual("album1")
        (result[1] as AlbumUiItem).title.shouldEqual("album2")
    }
}
