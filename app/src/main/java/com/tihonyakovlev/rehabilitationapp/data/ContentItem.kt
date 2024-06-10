package com.tihonyakovlev.rehabilitationapp.data

data class ContentItem(
    val type: String,
    val value: String
)

data class Content(
    val title: String,
    val content: List<ContentItem>
)
