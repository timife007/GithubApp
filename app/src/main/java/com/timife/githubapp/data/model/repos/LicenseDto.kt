package com.timife.githubapp.data.model.repos


import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable


data class LicenseDto(
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "spdx_id")
    val spdxId: String,
    @Json(name = "url")
    val url: String
)