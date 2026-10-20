package com.example.phixo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phixo.ui.MainViewModel
import com.example.phixo.ui.PhixoTab
import com.example.phixo.ui.screens.BitacoraScreen
import com.example.phixo.ui.screens.CosmicGridScreen
import com.example.phixo.ui.screens.DashboardScreen
import com.example.phixo.ui.screens.DictionaryScreen
import com.example.phixo.ui.screens.PortfolioScreen
import com.example.phixo.ui.screens.ProjectsScreen
import com.example.phixo.ui.theme.CosmicBlack
import com.example.phixo.ui.theme.CosmicDark
import com.example.phixo.ui.theme.CosmicSurface
import com.example.phixo.ui.theme.CosmicTextMuted
import com.example.phixo.ui.theme.PhixoTheme
import com.example.phixo.ui.theme.SolarGold

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhixoTheme {
                val uiState by viewModel.uiState.collectAsState()

                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                    containerColor = CosmicBlack,
                    bottomBar = {
                        NavigationBar(
                            containerColor = CosmicDark,
                            tonalElevation = 8.dp,
                            modifier = Modifier.background(CosmicDark)
                        ) {
                            NavigationBarItem(
                                selected = uiState.selectedTab == PhixoTab.DASHBOARD,
                                onClick = { viewModel.selectTab(PhixoTab.DASHBOARD) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Dashboard,
                                        contentDescription = "Dashboard",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Dashboard",
                                        fontSize = 9.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = SolarGold,
                                    selectedTextColor = SolarGold,
                                    unselectedIconColor = CosmicTextMuted,
                                    unselectedTextColor = CosmicTextMuted,
                                    indicatorColor = CosmicSurface
                                )
                            )

                            NavigationBarItem(
                                selected = uiState.selectedTab == PhixoTab.BITACORA,
                                onClick = { viewModel.selectTab(PhixoTab.BITACORA) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Shield,
                                        contentDescription = "Bitácora",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Bitácora",
                                        fontSize = 9.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = SolarGold,
                                    selectedTextColor = SolarGold,
                                    unselectedIconColor = CosmicTextMuted,
                                    unselectedTextColor = CosmicTextMuted,
                                    indicatorColor = CosmicSurface
                                )
                            )

                            NavigationBarItem(
                                selected = uiState.selectedTab == PhixoTab.PORTFOLIO,
                                onClick = { viewModel.selectTab(PhixoTab.PORTFOLIO) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.AccountBalanceWallet,
                                        contentDescription = "Portafolio",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Portafolio",
                                        fontSize = 9.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = SolarGold,
                                    selectedTextColor = SolarGold,
                                    unselectedIconColor = CosmicTextMuted,
                                    unselectedTextColor = CosmicTextMuted,
                                    indicatorColor = CosmicSurface
                                )
                            )

                            NavigationBarItem(
                                selected = uiState.selectedTab == PhixoTab.PROJECTS,
                                onClick = { viewModel.selectTab(PhixoTab.PROJECTS) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Terminal,
                                        contentDescription = "Proyectos",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Proyectos",
                                        fontSize = 9.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = SolarGold,
                                    selectedTextColor = SolarGold,
                                    unselectedIconColor = CosmicTextMuted,
                                    unselectedTextColor = CosmicTextMuted,
                                    indicatorColor = CosmicSurface
                                )
                            )

                            NavigationBarItem(
                                selected = uiState.selectedTab == PhixoTab.COSMIC_GRID,
                                onClick = { viewModel.selectTab(PhixoTab.COSMIC_GRID) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Hub,
                                        contentDescription = "Nodos",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Nodos",
                                        fontSize = 9.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = SolarGold,
                                    selectedTextColor = SolarGold,
                                    unselectedIconColor = CosmicTextMuted,
                                    unselectedTextColor = CosmicTextMuted,
                                    indicatorColor = CosmicSurface
                                )
                            )

                            NavigationBarItem(
                                selected = uiState.selectedTab == PhixoTab.DICTIONARY,
                                onClick = { viewModel.selectTab(PhixoTab.DICTIONARY) },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.AutoStories,
                                        contentDescription = "Diccionario",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Diccionario",
                                        fontSize = 9.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = SolarGold,
                                    selectedTextColor = SolarGold,
                                    unselectedIconColor = CosmicTextMuted,
                                    unselectedTextColor = CosmicTextMuted,
                                    indicatorColor = CosmicSurface
                                )
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Crossfade(
                            targetState = uiState.selectedTab,
                            label = "TabTransition"
                        ) { tab ->
                            when (tab) {
                                PhixoTab.DASHBOARD -> DashboardScreen(
                                    state = uiState,
                                    viewModel = viewModel
                                )
                                PhixoTab.BITACORA -> BitacoraScreen(
                                    state = uiState,
                                    viewModel = viewModel
                                )
                                PhixoTab.PORTFOLIO -> PortfolioScreen(
                                    state = uiState,
                                    viewModel = viewModel
                                )
                                PhixoTab.PROJECTS -> ProjectsScreen(
                                    state = uiState,
                                    viewModel = viewModel
                                )
                                PhixoTab.COSMIC_GRID -> CosmicGridScreen(
                                    state = uiState,
                                    viewModel = viewModel
                                )
                                PhixoTab.DICTIONARY -> DictionaryScreen(
                                    state = uiState,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
