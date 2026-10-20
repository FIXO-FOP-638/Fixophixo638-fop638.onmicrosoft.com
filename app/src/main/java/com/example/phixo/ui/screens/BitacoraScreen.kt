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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phixo.data.MatutinaTopic
import com.example.phixo.data.MultimediaVectorItem
import com.example.phixo.data.OperativeMonthlyAxis
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
import com.example.phixo.ui.theme.SolarGoldBright
import com.example.phixo.ui.theme.StatusActive
import com.example.phixo.ui.theme.StatusWarning

@Composable
fun BitacoraScreen(
    state: MainUiState,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val filterOptions = listOf(
        "Todos",
        "Conferencia Matutina",
        "Ejes Operativos",
        "Multimedia",
        "DN-III Defensa"
    )

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                BitacoraMasterHeaderCard(state = state, viewModel = viewModel)
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Filters
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    filterOptions.forEach { option ->
                        val isSelected = state.bitacoraFilter == option
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) ImperialMagenta else CosmicSurfaceVariant)
                                .border(
                                    1.dp,
                                    if (isSelected) SolarGold else CosmicBorder,
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable { viewModel.setBitacoraFilter(option) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = option,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else CosmicTextSecondary
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // DN-III-DEFENSA section
            if (state.bitacoraFilter == "Todos" || state.bitacoraFilter == "DN-III Defensa") {
                item {
                    DefenseProtocolCard(
                        isActive = state.isDefenseProtocolActive,
                        onToggle = { viewModel.toggleDefenseProtocol() }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Conferencia Matutina Section
            if (state.bitacoraFilter == "Todos" || state.bitacoraFilter == "Conferencia Matutina") {
                item {
                    ConferenciaMatutinaHeroCard(
                        entry = state.matutina,
                        onSelectTopic = { viewModel.selectMatutinaTopic(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Monthly Operational Axes
            if (state.bitacoraFilter == "Todos" || state.bitacoraFilter == "Ejes Operativos") {
                item {
                    Text(
                        text = "RESUMEN MENSUAL Y BITÁCORA OPERATIVA (OCTUBRE 2026)",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = ImperialMagentaLight,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Marco de Gobernanza: Imperio Magenta Queen Universal // Citizens Protección",
                        style = MaterialTheme.typography.bodySmall,
                        color = CosmicTextMuted
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                items(state.monthlyAxes) { axis ->
                    OperativeAxisCard(axis = axis)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            // Multimedia Vector Section
            if (state.bitacoraFilter == "Todos" || state.bitacoraFilter == "Multimedia") {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "VECTOR DE INTELIGENCIA MULTIMEDIA",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = SolarGoldBright,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Extracción y análisis conceptual audiovisual asistido por IA",
                        style = MaterialTheme.typography.bodySmall,
                        color = CosmicTextMuted
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                items(state.multimediaVectors) { vector ->
                    MultimediaVectorCard(vector = vector)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // Topic Detail Modal
        state.selectedMatutinaTopic?.let { topic ->
            TopicDetailDialog(
                topic = topic,
                onDismiss = { viewModel.selectMatutinaTopic(null) }
            )
        }
    }
}

@Composable
private fun BitacoraMasterHeaderCard(
    state: MainUiState,
    viewModel: MainViewModel
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            Brush.horizontalGradient(
                listOf(ImperialMagenta, SolarGold, CyberCyan)
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(CosmicSurfaceVariant, CosmicDark)
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(StatusActive.copy(alpha = 0.2f))
                            .border(1.dp, StatusActive, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.VerifiedUser,
                            contentDescription = null,
                            tint = StatusActive,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "STATUS: LOGGED & SYNCHRONIZED",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Black,
                            color = StatusActive,
                            letterSpacing = 0.8.sp
                        )
                        Text(
                            text = "TARGET: @#FIXOFOP638.md · 19 Octubre 2026",
                            style = MaterialTheme.typography.bodySmall,
                            color = CosmicTextMuted
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(ImperialMagenta.copy(alpha = 0.2f))
                        .border(1.dp, ImperialMagenta.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "FIXO MX12 #8943",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = SolarGoldBright
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Nodo: Heroica Cd. Juárez, Chih. // Cuenta: fixolunarshift@gmail.com",
                style = MaterialTheme.typography.bodySmall,
                color = CyberCyan
            )

            Text(
                text = "Imperio Magenta Queen Universal // Citizens Protección",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = ImperialMagentaLight
            )
        }
    }
}

@Composable
private fun DefenseProtocolCard(
    isActive: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isActive) ImperialMagenta.copy(alpha = 0.7f) else CosmicBorder
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = if (isActive) ImperialMagentaLight else CosmicTextMuted,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "PROTOCOLO DE RESILIENCIA Y DEFENSA",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = CosmicTextPrimary
                        )
                        Text(
                            text = "DN-III-DEFENSA / DN-III-E",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isActive) SolarGold else CosmicTextMuted
                        )
                    }
                }

                Switch(
                    checked = isActive,
                    onCheckedChange = { onToggle() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = SolarGold,
                        checkedTrackColor = ImperialMagenta,
                        uncheckedTrackColor = CosmicSurface
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isActive) {
                    "Protocolo de máxima alerta y defensa sistémica ACTIVO. Integridad de flujos de datos garantizada y nodo central #8943 blindado bajo tutela imperial."
                } else {
                    "Protocolo en modo de reposo reactivo. Haz clic para activar la defensa sistémica del nodo."
                },
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary
            )
        }
    }
}

@Composable
private fun ConferenciaMatutinaHeroCard(
    entry: com.example.phixo.data.ConferenciaMatutinaEntry,
    onSelectTopic: (MatutinaTopic) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.2.dp,
            SolarGold.copy(alpha = 0.4f)
        )
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccountBalance,
                        contentDescription = null,
                        tint = SolarGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "NODO INSTITUCIONAL // PRESIDENCIA",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = SolarGold,
                        letterSpacing = 0.8.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(StatusActive.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "OFICIAL CEPROPIE",
                        style = MaterialTheme.typography.labelSmall,
                        color = StatusActive,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = entry.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = CosmicTextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Fecha: ${entry.date} · Event ID: ${entry.eventId} · Video ID: ${entry.videoId}",
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
                color = CyberCyan
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Puntos Clave y Síntesis de la Sesión:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = CosmicTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            entry.topics.forEach { topic ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(CosmicSurface)
                        .border(0.8.dp, CosmicBorder, RoundedCornerShape(10.dp))
                        .clickable { onSelectTopic(topic) }
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = topic.subtitle,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = ImperialMagentaLight
                            )
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = CyberCyan,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Text(
                            text = "Expositor: ${topic.speaker}",
                            style = MaterialTheme.typography.labelSmall,
                            color = CosmicTextMuted
                        )

                        if (topic.metricHighlight != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = topic.metricHighlight,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = SolarGold
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OperativeAxisCard(axis: OperativeMonthlyAxis) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, CosmicBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = axis.iconEmoji,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${axis.number}. ${axis.title}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = CosmicTextPrimary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = axis.summary,
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                axis.tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(CosmicSurface)
                            .border(0.8.dp, CosmicBorder, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall,
                            color = CyberCyan
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MultimediaVectorCard(vector: MultimediaVectorItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            SolarGold.copy(alpha = 0.35f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Movie,
                        contentDescription = null,
                        tint = SolarGold,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${vector.artistOrEntity} — ${vector.title}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = CosmicTextPrimary
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(StatusActive.copy(alpha = 0.15f))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "PROCESSED",
                        style = MaterialTheme.typography.labelSmall,
                        color = StatusActive,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "URL: ${vector.videoUrl}",
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
                color = CyberCyan
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = vector.concept,
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Elementos Visuales Analizados:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = SolarGold
            )

            Spacer(modifier = Modifier.height(4.dp))

            vector.visualHighlights.forEach { highlight ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "• ",
                        style = MaterialTheme.typography.bodySmall,
                        color = ImperialMagentaLight
                    )
                    Text(
                        text = highlight,
                        style = MaterialTheme.typography.bodySmall,
                        color = CosmicTextPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun TopicDetailDialog(
    topic: MatutinaTopic,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CosmicSurface,
        shape = RoundedCornerShape(20.dp),
        title = {
            Column {
                Text(
                    text = topic.subtitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = CosmicTextPrimary
                )
                Text(
                    text = "Expositor: ${topic.speaker}",
                    style = MaterialTheme.typography.labelSmall,
                    color = SolarGold
                )
            }
        },
        text = {
            Column {
                if (topic.metricHighlight != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(CosmicBlack)
                            .border(1.dp, SolarGold.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = topic.metricHighlight,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = SolarGoldBright
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Text(
                    text = "Detalles y Registro Oficial:",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = CosmicTextSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))

                topic.points.forEach { pt ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "▸ ",
                            style = MaterialTheme.typography.bodySmall,
                            color = ImperialMagentaLight,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = pt,
                            style = MaterialTheme.typography.bodySmall,
                            color = CosmicTextPrimary,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = ImperialMagenta)
            ) {
                Text("Entendido", color = Color.White)
            }
        }
    )
}
