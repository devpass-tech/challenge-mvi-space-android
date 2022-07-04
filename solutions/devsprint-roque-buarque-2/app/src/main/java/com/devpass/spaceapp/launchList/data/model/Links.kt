package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class Links(@SerializedName("patch")
                 val patch: Patch,
                 @SerializedName("webcast")
                 val webcast: String = "",
                 @SerializedName("flickr")
                 val flickr: Flickr,
                 @SerializedName("reddit")
                 val reddit: Reddit,
                 @SerializedName("wikipedia")
                 val wikipedia: String = "",
                 @SerializedName("youtube_id")
                 val youtubeId: String = "",
                 @SerializedName("presskit")
                 val presskit: String = "",
                 @SerializedName("article")
                 val article: String = "")