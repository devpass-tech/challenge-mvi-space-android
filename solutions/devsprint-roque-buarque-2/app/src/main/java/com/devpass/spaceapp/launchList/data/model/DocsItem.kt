package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class DocsItem(@SerializedName("launchpad")
                    val launchpad: String = "",
                    @SerializedName("payloads")
                    val payloads: List<String>?,
                    @SerializedName("rocket")
                    val rocket: String = "",
                    @SerializedName("date_unix")
                    val dateUnix: Int = 0,
                    @SerializedName("cores")
                    val cores: List<CoresItem>?,
                    @SerializedName("auto_update")
                    val autoUpdate: Boolean = false,
                    @SerializedName("date_precision")
                    val datePrecision: String = "",
                    @SerializedName("links")
                    val links: Links,
                    @SerializedName("details")
                    val details: String = "",
                    @SerializedName("id")
                    val id: String = "",
                    @SerializedName("net")
                    val net: Boolean = false,
                    @SerializedName("static_fire_date_utc")
                    val staticFireDateUtc: String = "",
                    @SerializedName("date_local")
                    val dateLocal: String = "",
                    @SerializedName("flight_number")
                    val flightNumber: Int = 0,
                    @SerializedName("fairings")
                    val fairings: Fairings,
                    @SerializedName("ships")
                    val ships: List<String>?,
                    @SerializedName("tdb")
                    val tdb: Boolean = false,
                    @SerializedName("date_utc")
                    val dateUtc: String = "",
                    @SerializedName("static_fire_date_unix")
                    val staticFireDateUnix: Int = 0,
                    @SerializedName("success")
                    val success: Boolean = false,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("window")
                    val window: Int = 0,
                    @SerializedName("upcoming")
                    val upcoming: Boolean = false)