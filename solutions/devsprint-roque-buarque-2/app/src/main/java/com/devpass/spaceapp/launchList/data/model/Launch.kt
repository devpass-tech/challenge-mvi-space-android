package com.devpass.spaceapp.launchList.data.model

import com.google.gson.annotations.SerializedName

data class Launch(@SerializedName("hasPrevPage")
                  val hasPrevPage: Boolean = false,
                  @SerializedName("docs")
                  val docs: List<DocsItem>?,
                  @SerializedName("hasNextPage")
                  val hasNextPage: Boolean = false,
                  @SerializedName("pagingCounter")
                  val pagingCounter: Int = 0,
                  @SerializedName("nextPage")
                  val nextPage: Int = 0,
                  @SerializedName("limit")
                  val limit: Int = 0,
                  @SerializedName("totalPages")
                  val totalPages: Int = 0,
                  @SerializedName("prevPage")
                  val prevPage: Int = 0,
                  @SerializedName("page")
                  val page: Int = 0,
                  @SerializedName("totalDocs")
                  val totalDocs: Int = 0)