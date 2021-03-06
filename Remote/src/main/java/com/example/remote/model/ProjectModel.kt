package com.example.remote.model

import com.google.gson.annotations.SerializedName

class ProjectModel(val id: String,
                   val name: String,
                   @SerializedName("full_name") val fullName: String,
                   @SerializedName("startgazers_count") val startCount: Int,
                   @SerializedName("created_at") val dateCreated: String,
                   val owner: OwnerModel)