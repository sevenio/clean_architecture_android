package com.example.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject(
        @PrimaryKey
        var id: String,
        var name: String,
        var fullName: String,
        var starCount: String,
        var dateCreated: String,
        var ownerName: String,
        var ownerAvatar: String,
        var isBookmarked: Boolean
)


