package com.timife.githubapp.data.model.users


import com.squareup.moshi.Json


data class SearchResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    @Json(name = "items")
    val userDtos: List<UserDto>
)