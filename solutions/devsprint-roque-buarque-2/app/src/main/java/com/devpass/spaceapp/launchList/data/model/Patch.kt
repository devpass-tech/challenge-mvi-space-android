package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class Patch(@SerializedName("small")
                 val small: String = "",
                 @SerializedName("large")
                 val large: String = "")