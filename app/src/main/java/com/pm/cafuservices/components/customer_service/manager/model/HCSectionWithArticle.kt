package com.pm.cafuservices.components.customer_service.manager.model

data class HCSectionWithArticle (
    val id: Long,
    val title: String,
    var articlesList: List<HCArticle> = emptyList()
)