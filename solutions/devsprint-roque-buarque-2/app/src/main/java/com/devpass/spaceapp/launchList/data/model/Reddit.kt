package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class Reddit(@SerializedName("campaign")
                  val campaign: String = "",
                  @SerializedName("launch")
                  val launch: String = "",
                  @SerializedName("media")
                  val media: String = "",
                  @SerializedName("recovery")
                  val recovery: String? = null)