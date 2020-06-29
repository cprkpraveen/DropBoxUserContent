package com.task.dropboxusercontent.network.model

import androidx.annotation.NonNull

data class CanadaFactsDataModel(
    val title: String,
    val rows: List<CanadaFactsRow>
)
data class CanadaFactsRow(
    @NonNull val title: String,
    @NonNull val description: String,
    @NonNull val imageHref: String
)