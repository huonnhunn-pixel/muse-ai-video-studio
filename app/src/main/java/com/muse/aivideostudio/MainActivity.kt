
package com.muse.aivideostudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF090914)
private val Panel = Color(0xFF171729)
private val Purple = Color(0xFF8957FF)
private val White = Color(0xFFF5F3FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MuseStudio()
        }
    }
}

@Composable
fun MuseStudio() {
    var prompt by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf("Text to Video") }
    var status by remember { mutableStateOf("") }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Purple,
            background = Background,
            surface = Panel,
            onBackground = White,
            onSurface = White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(
                "MUSE AI",
                color = Purple,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "VIDEO STUDIO",
                color = White,
                fontSize = 29.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                "Create your imagination with AI",
                color = Color.LightGray,
                fontSize = 13.sp
            )

            Spacer(Modifier.height(30.dp))

            Text(
                "CREATE VIDEO",
                color = White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(15.dp))

            Text(
                "Choose creation mode",
                color = Color.LightGray
            )

            Spacer(Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Text to Video", "Image to Video").forEach {
                    FilterChip(
                        selected = mode == it,
                        onClick = {
                            mode = it
                            status = ""
                        },
                        label = { Text(it, fontSize = 11.sp) }
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Describe your video",
                color = White,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = prompt,
                onValueChange = {
                    prompt = it
                    status = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = {
                    Text("Describe the video you want to create...")
                },
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(Modifier.height(22.dp))

            Text(
                "VIDEO SETTINGS",
                color = White,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Panel
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Aspect ratio", color = Color.LightGray)
                    Text(
                        "9:16  •  Vertical video",
                        color = White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(16.dp))

                    Text("Maximum duration", color = Color.LightGray)
                    Text(
                        "10 seconds",
                        color = White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(25.dp))

            Button(
                onClick = {
                    status = if (prompt.isBlank()) {
                        "Please enter your video description."
                    } else {
                        "Interface ready. Muse AI API connection is not configured yet."
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    "✦  GENERATE VIDEO",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (status.isNotEmpty()) {
                Spacer(Modifier.height(15.dp))
                Text(status, color = Color(0xFFBBA5FF))
            }

            Spacer(Modifier.height(30.dp))

            HorizontalDivider(color = Color.DarkGray)

            Spacer(Modifier.height(20.dp))

            Text(
                "MY VIDEOS",
                color = White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            Text(
                "Your generated videos will appear here.",
                color = Color.LightGray,
                fontSize = 13.sp
            )

            Spacer(Modifier.height(30.dp))

            Text(
                "MUSE AI VIDEO STUDIO © 2026",
                color = Color.Gray,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
