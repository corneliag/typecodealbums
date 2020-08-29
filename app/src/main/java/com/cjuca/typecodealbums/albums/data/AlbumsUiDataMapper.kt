package com.cjuca.typecodealbums.albums.data

import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.core.model.Album

class AlbumsUiDataMapper {

    fun mapToUiData(albumList: List<Album>): List<AlbumRecyclerItem> {
        if (albumList.isEmpty()) {
            return listOf(EmptyItem(R.string.empty_title_for_album))
        }
        return albumList.map {
            AlbumUiItem(it.id, it.title)
        }
    }
}