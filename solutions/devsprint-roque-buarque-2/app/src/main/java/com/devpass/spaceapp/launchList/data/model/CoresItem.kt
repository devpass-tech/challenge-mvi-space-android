package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class CoresItem(@SerializedName("core")
                     val core: String = "",
                     @SerializedName("flight")
                     val flight: Int = 0,
                     @SerializedName("landing_type")
                     val landingType: String = "",
                     @SerializedName("gridfins")
                     val gridfins: Boolean = false,
                     @SerializedName("landing_attempt")
                     val landingAttempt: Boolean = false,
                     @SerializedName("legs")
                     val legs: Boolean = false,
                     @SerializedName("landpad")
                     val landpad: String = "",
                     @SerializedName("reused")
                     val reused: Boolean = false,
                     @SerializedName("landing_success")
                     val landingSuccess: Boolean = false)