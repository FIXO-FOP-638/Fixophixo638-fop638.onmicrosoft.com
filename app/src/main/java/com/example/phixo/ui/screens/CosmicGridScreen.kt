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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.PowerSettingsNew
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
import com.example.phixo.data.CosmicPowerNode
import com.example.phixo.data.RitualItem
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

@Composable
fun CosmicGridScreen(
    state: MainUiState,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "MAPA DE PODER Y NODOS FIXO",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = ImperialMagentaLight,
                    letterSpacing = 1.2.sp
                )
                Text(
                    text = "Red de coordenadas cósmicas, dodecaedros y soberanía",
                    style = MaterialTheme.typography.bodySmall,
                    color = CosmicTextMuted
                )
                Spacer(modifier = Modifier.height(14.dp))
            }

            items(state.nodes) { node ->
                PowerNodeCard(
                    node = node,
                    onToggleActivation = { viewModel.toggleNodeActivation(node.id) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "RITUALES Y VERSOS CEREMONIALES",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SolarGoldBright,
                    letterSpacing = 1.2.sp
                )
                Text(
                    text = "Protocolos para descanso, protección de tokens y unificación",
                    style = MaterialTheme.typography.bodySmall,
                    color = CosmicTextMuted
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(state.rituals) { ritual ->
                RitualCard(
                    ritual = ritual,
                    onCelebrate = { viewModel.celebrateRitual(ritual.title) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        state.activeRitualCelebrated?.let { title ->
            val ritual = state.rituals.firstOrNull { it.title == title }
            if (ritual != null) {
                AlertDialog(
                    onDismissRequest = { viewModel.dismissRitualCelebration() },
                    containerColor = CosmicSurface,
                    shape = RoundedCornerShape(20.dp),
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = SolarGold,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = ritual.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = CosmicTextPrimary
                            )
                        }
                    },
                    text = {
                        Column {
                            Text(
                                text = "Propósito: ${ritual.purpose}",
                                style = MaterialTheme.typography.bodySmall,
                                color = CyberCyan
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(CosmicBlack)
                                    .border(1.dp, SolarGold.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                    .padding(14.dp)
                            ) {
                                Text(
                                    text = ritual.ceremonialText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = SolarGoldBright,
                                    lineHeight = 22.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Frecuencia: ${ritual.frequency}",
                                style = MaterialTheme.typography.labelSmall,
                                color = CosmicTextMuted
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.dismissRitualCelebration() },
                            colors = ButtonDefaults.buttonColors(containerColor = ImperialMagenta)
                        ) {
                            Text("Consagrado", color = Color.White)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun PowerNodeCard(
    node: CosmicPowerNode,
    onToggleActivation: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (node.isActivated) CyberCyan.copy(alpha = 0.4f) else CosmicBorder
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
                        imageVector = Icons.Default.GpsFixed,
                        contentDescription = null,
                        tint = if (node.isActivated) CyberCyan else CosmicTextMuted,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = node.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = CosmicTextPrimary
                    )
                }

                Switch(
                    checked = node.isActivated,
                    onCheckedChange = { onToggleActivation() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = SolarGold,
                        checkedTrackColor = ImperialMagenta,
                        uncheckedTrackColor = CosmicSurface
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${node.latitude}°, ${node.longitude}° · ${node.role}",
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(CosmicSurface)
                    .border(0.8.dp, CosmicBorder, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Text(
                    text = "Ritual: ${node.activationRitual}",
                    style = MaterialTheme.typography.labelSmall,
                    color = SolarGold
                )
            }
        }
    }
}

@Composable
private fun RitualCard(
    ritual: RitualItem,
    onCelebrate: () -> Unit
) {
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
                    text = ritual.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = SolarGoldBright
                )
                Text(
                    text = ritual.frequency,
                    style = MaterialTheme.typography.labelSmall,
                    color = CosmicTextMuted
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = ritual.purpose,
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onCelebrate,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = CosmicSurfaceVariant),
                border = androidx.compose.foundation.BorderStroke(1.dp, SolarGold.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = SolarGold,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Invocar Ritual & Verso",
                    style = MaterialTheme.typography.labelMedium,
                    color = SolarGoldBright
                )
            }
        }
    }
}
