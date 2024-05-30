package com.example.seasons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seasons.ui.theme.SeasonsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeasonsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentSeason by remember { mutableStateOf(1) }
                    var winterClicks by remember { mutableStateOf(0) }
                    val requiredClicks = remember { Random.nextInt(1, 6) }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(getBackgroundColor(currentSeason))
                    ) {
                        when (currentSeason) {
                            1 -> {
                                SeasonTextAndImage(
                                    textResource = R.string.spring_select,
                                    imageResource = R.drawable.spring_tree,
                                    contentDescription = R.string.spring_tree_content,
                                    infoResource = R.string.spring_info,
                                    additionalInfo = R.string.spring_additional_info,
                                    onImageClick = { currentSeason = 2 }
                                )
                            }
                            2 -> {
                                SeasonTextAndImage(
                                    textResource = R.string.summer_select,
                                    imageResource = R.drawable.summer_sun,
                                    contentDescription = R.string.summer_sun_content,
                                    infoResource = R.string.summer_info,
                                    additionalInfo = R.string.summer_additional_info,
                                    onImageClick = { currentSeason = 3 }
                                )
                            }
                            3 -> {
                                SeasonTextAndImage(
                                    textResource = R.string.fall_select,
                                    imageResource = R.drawable.fall_leaf,
                                    contentDescription = R.string.fall_leaf_content,
                                    infoResource = R.string.fall_info,
                                    additionalInfo = R.string.fall_additional_info,
                                    onImageClick = { currentSeason = 4 }
                                )
                            }
                            4 -> {
                                SeasonTextAndImage(
                                    textResource = R.string.winter_select,
                                    imageResource = R.drawable.winter_snowflake,
                                    contentDescription = R.string.winter_snowflake_content,
                                    infoResource = R.string.winter_info,
                                    additionalInfo = R.string.winter_additional_info,
                                    onImageClick = {
                                        winterClicks++
                                        if (winterClicks >= requiredClicks) {
                                            currentSeason = 1
                                            winterClicks = 0
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SeasonTextAndImage(
        textResource: Int,
        imageResource: Int,
        contentDescription: Int,
        infoResource: Int,
        additionalInfo: Int? = null,
        onImageClick: () -> Unit
    ) {
        Text(
            text = stringResource(id = textResource),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(bottom = 16.dp)
                .clickable(onClick = onImageClick)
        )
        Text(
            text = stringResource(id = infoResource),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        additionalInfo?.let {
            Text(
                text = stringResource(id = it),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }

    @Composable
    fun getBackgroundColor(season: Int): Color {
        return when (season) {
            1 -> Color(0xFFE0F7FA)
            2 -> Color(0xFFFFF9C4)
            3 -> Color(0xFFFFCCBC)
            4 -> Color(0xFFECEFF1)
            else -> Color.White
        }
    }
}
