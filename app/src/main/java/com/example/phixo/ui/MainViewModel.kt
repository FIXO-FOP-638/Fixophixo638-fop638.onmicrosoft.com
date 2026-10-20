package com.example.phixo.ui

import androidx.lifecycle.ViewModel
import com.example.phixo.data.ConferenciaMatutinaEntry
import com.example.phixo.data.CosmicPowerNode
import com.example.phixo.data.CryptoPortfolioItem
import com.example.phixo.data.MatutinaTopic
import com.example.phixo.data.MultimediaVectorItem
import com.example.phixo.data.OperativeMonthlyAxis
import com.example.phixo.data.PhixoNeologism
import com.example.phixo.data.PhixoRepository
import com.example.phixo.data.ProjectItem
import com.example.phixo.data.RitualItem
import com.example.phixo.data.ServiceHealth
import com.example.phixo.data.ServiceStatusItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class PhixoTab(val title: String) {
    DASHBOARD("Dashboard"),
    BITACORA("Bitácora"),
    PORTFOLIO("Portafolio"),
    PROJECTS("Proyectos"),
    COSMIC_GRID("Nodos"),
    DICTIONARY("Diccionario")
}

data class MainUiState(
    val selectedTab: PhixoTab = PhixoTab.DASHBOARD,
    val services: List<ServiceStatusItem> = PhixoRepository.getServiceStatuses(),
    val portfolio: List<CryptoPortfolioItem> = PhixoRepository.getCryptoPortfolio(),
    val projects: List<ProjectItem> = PhixoRepository.getFeaturedProjects(),
    val neologisms: List<PhixoNeologism> = PhixoRepository.getNeologisms(),
    val nodes: List<CosmicPowerNode> = PhixoRepository.getCosmicNodes(),
    val rituals: List<RitualItem> = PhixoRepository.getRituals(),

    // Master Log & Bitácora 19 Octubre 2026
    val matutina: ConferenciaMatutinaEntry = PhixoRepository.getConferenciaMatutina(),
    val monthlyAxes: List<OperativeMonthlyAxis> = PhixoRepository.getOperativeMonthlyAxes(),
    val multimediaVectors: List<MultimediaVectorItem> = PhixoRepository.getMultimediaVectors(),
    val isDefenseProtocolActive: Boolean = true,
    val selectedMatutinaTopic: MatutinaTopic? = null,
    val bitacoraFilter: String = "Todos",
    
    // Search and filter
    val projectSearchQuery: String = "",
    val selectedProjectCategory: String = "Todos",
    val dictionarySearchQuery: String = "",
    val selectedDictionaryCategory: String = "Todos",
    
    // Interactive Calculator
    val customUsdInput: String = "13275540",
    val customDogeMultiplier: String = "853",
    val calculatedPatrimonio: String = "$153.54 Trillion USD",
    
    // Diagnostics dialog/sheet
    val selectedServiceForFix: ServiceStatusItem? = null,
    val isAllianceExpanded: Boolean = false,
    val activeRitualCelebrated: String? = null
)

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun selectTab(tab: PhixoTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun setBitacoraFilter(filter: String) {
        _uiState.update { it.copy(bitacoraFilter = filter) }
    }

    fun selectMatutinaTopic(topic: MatutinaTopic?) {
        _uiState.update { it.copy(selectedMatutinaTopic = topic) }
    }

    fun toggleDefenseProtocol() {
        _uiState.update { it.copy(isDefenseProtocolActive = !it.isDefenseProtocolActive) }
    }

    fun setProjectSearchQuery(query: String) {
        _uiState.update { it.copy(projectSearchQuery = query) }
    }

    fun setProjectCategory(category: String) {
        _uiState.update { it.copy(selectedProjectCategory = category) }
    }

    fun setDictionarySearchQuery(query: String) {
        _uiState.update { it.copy(dictionarySearchQuery = query) }
    }

    fun setDictionaryCategory(category: String) {
        _uiState.update { it.copy(selectedDictionaryCategory = category) }
    }

    fun updateCalculator(usd: String, dogeMultiplier: String) {
        val usdVal = usd.toDoubleOrNull() ?: 0.0
        val dogeMul = dogeMultiplier.toDoubleOrNull() ?: 0.0
        val dogePartTrillion = dogeMul * 0.18
        val totalTrillionFormatted = String.format("%.2f", dogePartTrillion)
        val formattedResult = "$$totalTrillionFormatted Trillion USD (Base: $${String.format("%,.2f", usdVal)})"

        _uiState.update {
            it.copy(
                customUsdInput = usd,
                customDogeMultiplier = dogeMultiplier,
                calculatedPatrimonio = formattedResult
            )
        }
    }

    fun toggleNodeActivation(nodeId: String) {
        _uiState.update { current ->
            val updatedNodes = current.nodes.map { node ->
                if (node.id == nodeId) {
                    node.copy(isActivated = !node.isActivated)
                } else {
                    node
                }
            }
            current.copy(nodes = updatedNodes)
        }
    }

    fun showServiceFix(service: ServiceStatusItem) {
        _uiState.update { it.copy(selectedServiceForFix = service) }
    }

    fun dismissServiceFix() {
        _uiState.update { it.copy(selectedServiceForFix = null) }
    }

    fun markServiceResolved(serviceId: String) {
        _uiState.update { current ->
            val updatedServices = current.services.map { service ->
                if (service.id == serviceId) {
                    service.copy(
                        statusText = "Reparado y Validado",
                        health = ServiceHealth.ACTIVE,
                        fixAction = null
                    )
                } else {
                    service
                }
            }
            current.copy(
                services = updatedServices,
                selectedServiceForFix = null
            )
        }
    }

    fun celebrateRitual(title: String) {
        _uiState.update { it.copy(activeRitualCelebrated = title) }
    }

    fun dismissRitualCelebration() {
        _uiState.update { it.copy(activeRitualCelebrated = null) }
    }

    fun toggleAllianceExpanded() {
        _uiState.update { it.copy(isAllianceExpanded = !it.isAllianceExpanded) }
    }
}
