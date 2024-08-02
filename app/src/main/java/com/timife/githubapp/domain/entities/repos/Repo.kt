package com.timife.githubapp.domain.entities.repos

data class Repo(
    val id: Int,
    val name: String,
    val private: Boolean,
    val description: String,
    val fork: Boolean,
    val gitUrl: String,
    val language: String,
    val forksCount: Int,
    val visibility: String,
    val stars:Int,
    )