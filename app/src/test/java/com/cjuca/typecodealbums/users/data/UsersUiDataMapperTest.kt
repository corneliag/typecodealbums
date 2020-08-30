package com.cjuca.typecodealbums.users.data

import com.cjuca.typecodealbums.core.model.User
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Test

class UsersUiDataMapperTest {

    private val mapper = UsersUiDataMapper()

    @Test
    fun map_emptyList_emptySearch() {
        val result = mapper.mapToUiData(emptyList(), "")
        result.size.shouldBe(1)
        result[0].shouldBeInstanceOf(EmptyItem::class.java)
    }

    @Test
    fun map_emptySearch() {
        val list =
            listOf(User(1L, "TEST", "tester", "test@gmail.com", "01234567890", "website.com"))
        val result = mapper.mapToUiData(list, "")
        result.size.shouldBe(1)
        result[0].shouldBeInstanceOf(UserUiItem::class.java)
        (result[0] as UserUiItem).name.shouldEqual("TEST")
    }

    @Test
    fun map() {
        val list =
            listOf(
                User(1L, "TEST", "tester", "test@gmail.com", "01234567890", "website.com"),
                User(2L, "TEST2", "tester2", "test2@gmail.com", "0445500000", "website.com")
            )
        val result = mapper.mapToUiData(list, "er2")
        result.size.shouldBe(1)
        result[0].shouldBeInstanceOf(UserUiItem::class.java)
        (result[0] as UserUiItem).name.shouldEqual("TEST2")
    }
}
