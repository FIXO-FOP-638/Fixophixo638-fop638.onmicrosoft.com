package com.example.phixo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.phixo.data.ServiceHealth
import com.example.phixo.data.ServiceStatusItem
import com.example.phixo.ui.MainUiState
import com.example.phixo.ui.MainViewModel
import com.example.phixo.ui.components.ImperialHeader
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
import com.example.phixo.ui.theme.StatusError
import com.example.phixo.ui.theme.StatusPending
import com.example.phixo.ui.theme.StatusWarning

@Composable
fun DashboardScreen(
    state: MainUiState,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ImperialHeader(
                    isExpanded = state.isAllianceExpanded,
                    onToggleExpand = { viewModel.toggleAllianceExpanded() }
                )
            }

            item {
                QuickMetricsRow(state)
            }

            item {
                BitacoraBanner(
                    onOpenBitacora = { viewModel.selectTab(com.example.phixo.ui.PhixoTab.BITACORA) }
                )
            }

            item {
                PaddingHeader(
                    title = "ESTADO DE LOS SERVICIOS Y WORKFLOWS",
                    subtitle = "Monitoreo en vivo de plataformas, dominios y pipelines"
                )
            }

            items(state.services) { service ->
                ServiceCard(
                    service = service,
                    onClick = { viewModel.showServiceFix(service) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // Diagnostic / Solution Dialog
        state.selectedServiceForFix?.let { service ->
            ServiceDetailDialog(
                service = service,
                onDismiss = { viewModel.dismissServiceFix() },
                onResolve = { viewModel.markServiceResolved(service.id) }
            )
        }
    }
}

@Composable
private fun QuickMetricsRow(state: MainUiState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MetricTile(
            title = "Patrimonio",
            value = "$14.28T",
            subtext = "USD Consolidado",
            icon = Icons.Default.MonetizationOn,
            accentColor = SolarGold,
            modifier = Modifier.weight(1f)
        )
        MetricTile(
            title = "Nodos Cósmicos",
            value = "${state.nodes.count { it.isActivated }}/${state.nodes.size}",
            subtext = "Activos en Red",
            icon = Icons.Default.Hub,
            accentColor = CyberCyan,
            modifier = Modifier.weight(1f)
        )
        MetricTile(
            title = "Servicios",
            value = "${state.services.count { it.health == ServiceHealth.ACTIVE }}/${state.services.size}",
            subtext = "Operacionales",
            icon = Icons.Default.Speed,
            accentColor = ImperialMagentaLight,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun MetricTile(
    title: String,
    value: String,
    subtext: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, CosmicBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = CosmicTextMuted
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = CosmicTextPrimary
            )
            Text(
                text = subtext,
                style = MaterialTheme.typography.labelSmall,
                color = accentColor.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
private fun PaddingHeader(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = ImperialMagentaLight,
            letterSpacing = 1.sp
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = CosmicTextMuted
        )
    }
}

@Composable
private fun ServiceCard(
    service: ServiceStatusItem,
    onClick: () -> Unit
) {
    val (statusColor, statusIcon) = when (service.health) {
        ServiceHealth.ACTIVE -> StatusActive to Icons.Default.CheckCircle
        ServiceHealth.WARNING -> StatusWarning to Icons.Default.WarningAmber
        ServiceHealth.PENDING -> StatusPending to Icons.Default.HourglassEmpty
        ServiceHealth.ERROR -> StatusError to Icons.Default.ErrorOutline
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (service.health == ServiceHealth.WARNING) StatusWarning.copy(alpha = 0.5f) else CosmicBorder
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
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = service.category.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = CosmicTextMuted,
                        letterSpacing = 0.8.sp
                    )
                    Text(
                        text = service.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = CosmicTextPrimary
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(statusColor.copy(alpha = 0.15f))
                        .border(1.dp, statusColor.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = statusIcon,
                            contentDescription = null,
                            tint = statusColor,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = when (service.health) {
                                ServiceHealth.ACTIVE -> "Activo"
                                ServiceHealth.WARNING -> "Atención"
                                ServiceHealth.PENDING -> "Pendiente"
                                ServiceHealth.ERROR -> "Error"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = statusColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = service.detail,
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary
            )

            if (service.fixAction != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        tint = SolarGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Ver Diagnóstico & Reparación",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = SolarGold
                    )
                }
            }
        }
    }
}

@Composable
private fun ServiceDetailDialog(
    service: ServiceStatusItem,
    onDismiss: () -> Unit,
    onResolve: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CosmicSurface,
        shape = RoundedCornerShape(20.dp),
        title = {
            Column {
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = CosmicTextPrimary
                )
                Text(
                    text = service.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = ImperialMagentaLight
                )
            }
        },
        text = {
            Column {
                Text(
                    text = "Diagnóstico Técnico:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = CosmicTextSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = service.detail,
                    style = MaterialTheme.typography.bodyMedium,
                    color = CosmicTextPrimary
                )

                if (service.fixAction != null) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Plan de Solución Ceremonial:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = SolarGold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(CosmicBlack)
                            .border(1.dp, CosmicBorder, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = service.fixAction,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                            color = CyberCyan
                        )
                    }
                }
            }
        },
        confirmButton = {
            if (service.health != ServiceHealth.ACTIVE) {
                Button(
                    onClick = onResolve,
                    colors = ButtonDefaults.buttonColors(containerColor = ImperialMagenta)
                ) {
                    Text("Aplicar y Resolver", color = Color.White)
                }
            } else {
                TextButton(onClick = onDismiss) {
                    Text("Cerrar", color = ImperialMagentaLight)
                }
            }
        },
        dismissButton = {
            if (service.health != ServiceHealth.ACTIVE) {
                TextButton(onClick = onDismiss) {
                    Text("Cerrar", color = CosmicTextMuted)
                }
            }
        }
    )
}

@Composable
fun BitacoraBanner(
    onOpenBitacora: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onOpenBitacora() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.2.dp,
            Brush.horizontalGradient(listOf(ImperialMagenta, SolarGold))
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(ImperialMagenta.copy(alpha = 0.2f))
                        .border(1.dp, SolarGold.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🏛️", fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "NUEVA BITÁCORA SINCRONIZADA",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = SolarGoldBright,
                            letterSpacing = 0.8.sp
                        )
                    }
                    Text(
                        text = "Conferencia Matutina 19 Oct 2026 (-KaSFGDcllE)",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = CosmicTextPrimary
                    )
                    Text(
                        text = "Profeco · Becas Rita Cetina · DN-III-DEFENSA",
                        style = MaterialTheme.typography.labelSmall,
                        color = CosmicTextMuted
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ImperialMagenta)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Ver",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

