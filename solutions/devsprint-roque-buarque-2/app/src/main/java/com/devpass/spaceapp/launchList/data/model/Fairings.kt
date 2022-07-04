package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class Fairings(@SerializedName("recovered")
                    val recovered: Boolean = false,
                    @SerializedName("ships")
                    val ships: List<String>?,
                    @SerializedName("recovery_attempt")
                    val recoveryAttempt: Boolean = false,
                    @SerializedName("reused")
                    val reused: Boolean = false)