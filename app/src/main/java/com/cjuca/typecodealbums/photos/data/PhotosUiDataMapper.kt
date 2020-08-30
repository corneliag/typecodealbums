package com.cjuca.typecodealbums.photos.data

import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.core.model.Photo

class PhotosUiDataMapper {

    fun mapToUiData(photoList: List<Photo>): List<PhotoRecyclerItem> {
        if (photoList.isEmpty()) {
            return listOf(EmptyItem(R.string.empty_title_for_photo))
        }
        return photoList.map {
            PhotoUiItem(it.id, it.title, it.thumbnailUrl, it.url)
        }
    }
}