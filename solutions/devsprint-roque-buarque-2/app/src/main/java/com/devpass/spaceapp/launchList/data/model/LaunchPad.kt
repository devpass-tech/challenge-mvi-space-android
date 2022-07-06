package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class LaunchPad(@SerializedName("launch_attempts")
                     val launchAttempts: Int = 0,
                     @SerializedName("timezone")
                     val timezone: String = "",
                     @SerializedName("latitude")
                     val latitude: Double = 0.0,
                     @SerializedName("locality")
                     val locality: String = "",
                     @SerializedName("rockets")
                     val rockets: List<String>?,
                     @SerializedName("full_name")
                     val fullName: String = "",
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("id")
                     val id: String = "",
                     @SerializedName("region")
                     val region: String = "",
                     @SerializedName("launch_successes")
                     val launchSuccesses: Int = 0,
                     @SerializedName("launches")
                     val launches: List<String>?,
                     @SerializedName("longitude")
                     val longitude: Double = 0.0,
                     @SerializedName("status")
                     val status: String = "")