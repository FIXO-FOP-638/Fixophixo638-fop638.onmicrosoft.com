package com.example.phixo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phixo.data.ProjectItem
import com.example.phixo.ui.MainUiState
import com.example.phixo.ui.MainViewModel
import com.example.phixo.ui.theme.CosmicBlack
import com.example.phixo.ui.theme.CosmicBorder
import com.example.phixo.ui.theme.CosmicDark
import com.example.phixo.ui.theme.CosmicSurface
import com.example.phixo.ui.theme.CosmicSurfaceVariant
import com.example.phixo.ui.theme.CosmicTextMuted
import com.example.phixo.ui.theme.CosmicTextPrimary
import com.example.phixo.ui.theme.CosmicTextSecondary
import com.example.phixo.ui.theme.CyberCyan
import com.example.phixo.ui.theme.ImperialMagenta
import com.example.phixo.ui.theme.ImperialMagentaLight
import com.example.phixo.ui.theme.SolarGold
import com.example.phixo.ui.theme.StatusActive

@Composable
fun ProjectsScreen(
    state: MainUiState,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        "Todos",
        "Generative AI",
        "Cloud & Media",
        "Blockchain & Solidity",
        "ERC-20 Tokenomics",
        "Web Automation",
        "DevOps & Infrastructure",
        "Hardware & Telemetry"
    )

    val filteredProjects = state.projects.filter { project ->
        val matchesCategory = state.selectedProjectCategory == "Todos" ||
                project.category.equals(state.selectedProjectCategory, ignoreCase = true)
        val matchesSearch = state.projectSearchQuery.isBlank() ||
                project.title.contains(state.projectSearchQuery, ignoreCase = true) ||
                project.description.contains(state.projectSearchQuery, ignoreCase = true) ||
                project.techStack.any { it.contains(state.projectSearchQuery, ignoreCase = true) }
        matchesCategory && matchesSearch
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "MATRIZ DE PROYECTOS PHIXO",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = ImperialMagentaLight,
                letterSpacing = 1.2.sp
            )
            Text(
                text = "Ecosistema de IA, contratos inteligentes, automatización y gaming",
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextMuted
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Search Box
            OutlinedTextField(
                value = state.projectSearchQuery,
                onValueChange = { viewModel.setProjectSearchQuery(it) },
                placeholder = { Text("Buscar proyecto, tecnología o repo...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = CosmicTextMuted
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ImperialMagenta,
                    unfocusedBorderColor = CosmicBorder,
                    focusedTextColor = CosmicTextPrimary,
                    unfocusedTextColor = CosmicTextPrimary
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Category Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { cat ->
                    val isSelected = state.selectedProjectCategory == cat
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) ImperialMagenta else CosmicSurfaceVariant)
                            .border(
                                1.dp,
                                if (isSelected) SolarGold else CosmicBorder,
                                RoundedCornerShape(12.dp)
                            )
                            .clickable { viewModel.setProjectCategory(cat) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = cat,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else CosmicTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        items(filteredProjects) { project ->
            ProjectCard(project)
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProjectCard(project: ProjectItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, CosmicBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = CyberCyan,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(StatusActive.copy(alpha = 0.15f))
                        .border(1.dp, StatusActive.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = project.status,
                        style = MaterialTheme.typography.labelSmall,
                        color = StatusActive,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = CosmicTextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = project.description,
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                project.techStack.forEach { tech ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(CosmicSurface)
                            .border(0.8.dp, CosmicBorder, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = tech,
                            style = MaterialTheme.typography.labelSmall,
                            color = SolarGold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Code,
                    contentDescription = null,
                    tint = CosmicTextMuted,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = project.repoOrBranch,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    color = CosmicTextMuted
                )
            }
        }
    }
}
