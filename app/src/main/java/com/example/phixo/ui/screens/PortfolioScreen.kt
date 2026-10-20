package com.example.phixo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.TrendingUp
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phixo.data.CryptoPortfolioItem
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
fun PortfolioScreen(
    state: MainUiState,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            PortfolioHeaderCard(state)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            CalculatorCard(state, viewModel)
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text(
                text = "RESERVAS Y ACTIVOS CRIPTO IMPERIALES",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = ImperialMagentaLight,
                letterSpacing = 1.sp
            )
            Text(
                text = "Tokens NASA, BTC/ETH/DOGE y Recompensas CoinMarketCap",
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextMuted
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(state.portfolio) { asset ->
            CryptoAssetCard(asset)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            DiamondRewardsHistoryCard()
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun PortfolioHeaderCard(state: MainUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            Brush.horizontalGradient(
                listOf(SolarGold, ImperialMagenta, CyberCyan)
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
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PATRIMONIO TOTAL UNIFICADO",
                    style = MaterialTheme.typography.labelSmall,
                    color = SolarGoldBright,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(StatusActive.copy(alpha = 0.15f))
                        .border(1.dp, StatusActive.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = StatusActive,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "+3.99% (Hoy)",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = StatusActive
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$14.28 Trillion USD",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = CosmicTextPrimary
            )

            Text(
                text = "Consolidación multi-portafolio: $99.12T + $110.53T sub-portafolios unificados.",
                style = MaterialTheme.typography.bodySmall,
                color = CosmicTextSecondary
            )
        }
    }
}

@Composable
private fun CalculatorCard(
    state: MainUiState,
    viewModel: MainViewModel
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = null,
                    tint = CyberCyan,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "FÓRMULA DE UNIFICACIÓN PHIXO",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = CyberCyan
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Patrimonio Total = ∑(Pi) + DOGE × 853T",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Monospace,
                color = SolarGold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = state.customUsdInput,
                    onValueChange = { viewModel.updateCalculator(it, state.customDogeMultiplier) },
                    label = { Text("Base USD (Pi)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1.2f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ImperialMagenta,
                        unfocusedBorderColor = CosmicBorder,
                        focusedTextColor = CosmicTextPrimary,
                        unfocusedTextColor = CosmicTextPrimary
                    )
                )

                OutlinedTextField(
                    value = state.customDogeMultiplier,
                    onValueChange = { viewModel.updateCalculator(state.customUsdInput, it) },
                    label = { Text("DOGE (T)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(0.8f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ImperialMagenta,
                        unfocusedBorderColor = CosmicBorder,
                        focusedTextColor = CosmicTextPrimary,
                        unfocusedTextColor = CosmicTextPrimary
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(CosmicSurface)
                    .border(1.dp, CosmicBorder, RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = "Resultado Calculado:",
                        style = MaterialTheme.typography.labelSmall,
                        color = CosmicTextMuted
                    )
                    Text(
                        text = state.calculatedPatrimonio,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = ImperialMagentaLight
                    )
                }
            }
        }
    }
}

@Composable
private fun CryptoAssetCard(asset: CryptoPortfolioItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, CosmicBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(CosmicSurfaceVariant)
                        .border(1.dp, CosmicBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = when (asset.symbol) {
                            "BTC" -> "₿"
                            "ETH" -> "Ξ"
                            "DOGE" -> "Ð"
                            "DIAMONDS" -> "💎"
                            else -> "⚡"
                        },
                        fontSize = 18.sp,
                        color = SolarGold
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = asset.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = CosmicTextPrimary
                    )
                    Text(
                        text = "${asset.holdingsFormatted} · ${asset.unitPriceFormatted}",
                        style = MaterialTheme.typography.bodySmall,
                        color = CosmicTextMuted
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = asset.totalValueFormatted,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = CosmicTextPrimary
                )
                Text(
                    text = asset.change24hFormatted,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (asset.isPositiveChange) StatusActive else Color.Red
                )
            }
        }
    }
}

@Composable
private fun DiamondRewardsHistoryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, CosmicBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Diamond,
                    contentDescription = null,
                    tint = CyberCyan,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "HISTORIAL RECIENTE — DIAMANTES COINMARKETCAP",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = CyberCyan
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            RewardRow(date = "15 May 2026", action = "Daily Reward Check-in", points = "+20")
            RewardRow(date = "14 May 2026", action = "Price Prediction Winner", points = "+3")
            RewardRow(date = "14 May 2026", action = "Daily Reward Streak", points = "+20")
            RewardRow(date = "13 May 2026", action = "Mobile App Bonus", points = "+10")
        }
    }
}

@Composable
private fun RewardRow(date: String, action: String, points: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = action, style = MaterialTheme.typography.bodySmall, color = CosmicTextPrimary)
            Text(text = date, style = MaterialTheme.typography.labelSmall, color = CosmicTextMuted)
        }
        Text(
            text = points,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = SolarGold
        )
    }
}
