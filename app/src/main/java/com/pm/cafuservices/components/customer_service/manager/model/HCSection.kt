package com.pm.cafuservices.components.customer_service.manager.model

data class HCSection(
    val id: Long,
    val title: String,
    val articlesList: List<HCArticle>
)
