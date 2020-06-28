package com.task.dropboxusercontent.network.model

data class CanadaFactsDataModel(
    val title: String,
    val rows: List<CanadaFactsRow>
)
data class CanadaFactsRow(
    val title: String,
    val description: String,
    val imageHref: String
)