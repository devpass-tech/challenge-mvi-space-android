package com.devpass.spaceapp.launchList.data

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchModel(
    val name: String,
    val number: String,
    var date: String,
    val status: String,
    val image: String,
    val id: String
    ): Parcelable {
    companion object : DiffUtil.ItemCallback<LaunchModel>() {
        override fun areItemsTheSame(oldItem: LaunchModel, newItem: LaunchModel): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: LaunchModel, newItem: LaunchModel): Boolean {
            return oldItem == newItem
        }
    }
}