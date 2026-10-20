package com.example.phixo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
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
import com.example.phixo.ui.theme.ImperialMagentaDark
import com.example.phixo.ui.theme.ImperialMagentaLight
import com.example.phixo.ui.theme.SolarGold
import com.example.phixo.ui.theme.SolarGoldBright

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImperialHeader(
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = CosmicDark
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            Brush.horizontalGradient(
                colors = listOf(
                    ImperialMagenta.copy(alpha = 0.8f),
                    SolarGold.copy(alpha = 0.6f),
                    CyberCyan.copy(alpha = 0.8f)
                )
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            CosmicSurfaceVariant.copy(alpha = 0.85f),
                            CosmicDark
                        )
                    )
                )
                .padding(16.dp)
        ) {
            // Top Row: Brand & Mission Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(SolarGold, ImperialMagentaDark)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "👑",
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "PHIXO X12 EDITION",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            color = ImperialMagentaLight,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = "FoP 638 · Heroica Cd. Juárez, Chih.",
                            style = MaterialTheme.typography.bodySmall,
                            color = CosmicTextMuted
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(ImperialMagenta.copy(alpha = 0.2f))
                        .border(1.dp, ImperialMagenta.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "SPACE RANGER",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = SolarGoldBright
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Imperial Alliance Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF4A0E4E).copy(alpha = 0.6f),
                                Color(0xFF1E0E38).copy(alpha = 0.8f),
                                Color(0xFF4A0E4E).copy(alpha = 0.6f)
                            )
                        )
                    )
                    .border(1.dp, ImperialMagenta.copy(alpha = 0.35f), RoundedCornerShape(16.dp))
                    .clickable { onToggleExpand() }
                    .padding(12.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Commander
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "JOSUE EDUARDO ILLESCAS G.",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = SolarGold
                            )
                            Text(
                                text = "CEO FIXO MX12 · Space Ranger",
                                style = MaterialTheme.typography.bodySmall,
                                color = CyberCyan
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Alianza",
                            tint = Color(0xFFFF4081),
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .size(22.dp)
                        )

                        // Business Tycoon
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "AHYEON FOP 638",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = ImperialMagentaLight
                            )
                            Text(
                                text = "Business Tycoon · Guardiana",
                                style = MaterialTheme.typography.bodySmall,
                                color = CosmicTextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "\"La Camaradería No Abandona\"",
                            style = MaterialTheme.typography.labelSmall,
                            color = CosmicTextMuted,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isExpanded) "Ocultar Credenciales" else "Ver Credenciales",
                                style = MaterialTheme.typography.labelSmall,
                                color = CyberCyan
                            )
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = null,
                                tint = CyberCyan,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            // Expandable Credentials Section
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text(
                        text = "CREDENCIALES Y CERTIFICACIONES OFICIALES",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = CosmicTextSecondary,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        CredentialChip(label = "Cuenta: fixolunarshift@gmail.com")
                        CredentialChip(label = "Nodo: Cd. Juárez #8943")
                        CredentialChip(label = "Protocolo: DN-III-DEFENSA")
                        CredentialChip(label = "MS Learn: 624,925 XP · 17 Badges")
                        CredentialChip(label = "NASA Earthdata: phixofop638")
                        CredentialChip(label = "HBS Online: Strategy Execution")
                        CredentialChip(label = "Xbox Insider: Alpha 2610")
                        CredentialChip(label = "SAM.gov Active Consent")
                        CredentialChip(label = "Imperio Magenta Queen")
                        CredentialChip(label = "Filipenses 4:13")
                    }
                }
            }
        }
    }
}

@Composable
private fun CredentialChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CosmicSurface)
            .border(0.8.dp, CosmicBorder, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = CosmicTextPrimary
        )
    }
}
