package com.gc.bookleash.utils

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gc.bookleash.R
import com.gc.bookleash.ui.theme.Blue80
import com.gc.bookleash.ui.theme.poppinsFontFamily

@Composable
fun DigitalLibraryCard(
    name: String,
    cardIssueNumber: String,
    libraryName: String,
    libraryUid: String,
    isUserEnrolledInLibrary: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.6f))
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column (
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = name,
                            fontSize = 24.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                        )
                        Text(
                            text = cardIssueNumber,
                            fontSize = 28.sp,
                            style = TextStyle(
                                fontFamily = poppinsFontFamily
                            ),
                            color = Color.Gray,
                            modifier = Modifier.height(30.dp).horizontalScroll(rememberScrollState())
                        )
                        Text(
                            text = "card-issue-number",
                            color = Color.Gray,
                            style = TextStyle(
                                lineHeight = 1.sp,
                                fontFamily = poppinsFontFamily
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Library Name : $libraryName",
                    color = Color.Gray,
                    style = TextStyle(
                        fontFamily = poppinsFontFamily
                    )
                )
                Text(
                    text = "Library UID : $libraryUid",
                    color = Color.Gray,
                    style = TextStyle(
                        fontFamily = poppinsFontFamily
                    )
                )
            }
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(if (!isUserEnrolledInLibrary) Color.White else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = !isUserEnrolledInLibrary,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "Please enroll in a library",
                        color = Blue80,
                        style = TextStyle(
                            fontFamily = poppinsFontFamily
                        ),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}