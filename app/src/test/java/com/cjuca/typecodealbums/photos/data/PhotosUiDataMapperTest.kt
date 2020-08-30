package com.cjuca.typecodealbums.photos.data

import com.cjuca.typecodealbums.core.model.Photo
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Test


class PhotosUiDataMapperTest {

    private val mapper = PhotosUiDataMapper()

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
                Photo(
                    1L,
                    1L,
                    "photo description 1",
                    "https://via.placeholder.com/600/a393af",
                    "https://via.placeholder.com/150/a393af"
                ),
                Photo(
                    2L,
                    2L,
                    "photo description 2",
                    "https://via.placeholder.com/600/a393ee",
                    "https://via.placeholder.com/150/a393ee"
                )
            )
        val result = mapper.mapToUiData(list)
        result.size.shouldBe(2)
        result[0].shouldBeInstanceOf(PhotoUiItem::class.java)
        (result[0] as PhotoUiItem).title.shouldEqual("photo description 1")
        (result[1] as PhotoUiItem).title.shouldEqual("photo description 2")
    }
}
