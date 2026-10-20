package com.example.phixo.data

enum class ServiceHealth {
    ACTIVE,
    WARNING,
    PENDING,
    ERROR
}

data class ServiceStatusItem(
    val id: String,
    val title: String,
    val category: String,
    val statusText: String,
    val health: ServiceHealth,
    val detail: String,
    val fixAction: String? = null
)

data class CryptoPortfolioItem(
    val id: String,
    val name: String,
    val symbol: String,
    val holdingsFormatted: String,
    val unitPriceFormatted: String,
    val totalValueFormatted: String,
    val change24hFormatted: String,
    val isPositiveChange: Boolean
)

data class ProjectItem(
    val id: String,
    val title: String,
    val category: String,
    val description: String,
    val techStack: List<String>,
    val repoOrBranch: String,
    val status: String
)

data class PhixoNeologism(
    val id: String,
    val term: String,
    val category: String,
    val definition: String,
    val ceremonialVerse: String,
    val hashtag: String
)

data class CosmicPowerNode(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val role: String,
    val status: String,
    val activationRitual: String,
    val isActivated: Boolean
)

data class RitualItem(
    val id: String,
    val title: String,
    val purpose: String,
    val ceremonialText: String,
    val frequency: String
)

// Master Log / Bitácora Models
data class MatutinaTopic(
    val subtitle: String,
    val speaker: String,
    val points: List<String>,
    val metricHighlight: String? = null
)

data class ConferenciaMatutinaEntry(
    val eventId: String,
    val date: String,
    val title: String,
    val videoId: String,
    val status: String,
    val topics: List<MatutinaTopic>
)

data class OperativeMonthlyAxis(
    val number: Int,
    val iconEmoji: String,
    val title: String,
    val summary: String,
    val tags: List<String>,
    val mediaUrl: String? = null
)

data class MultimediaVectorItem(
    val id: String,
    val title: String,
    val artistOrEntity: String,
    val videoUrl: String,
    val status: String,
    val concept: String,
    val visualHighlights: List<String>
)
