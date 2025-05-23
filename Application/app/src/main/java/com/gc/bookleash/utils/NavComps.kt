package com.gc.bookleash.utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.gc.bookleash.ui.theme.Blue40
import com.gc.bookleash.ui.theme.Blue80
import com.gc.bookleash.ui.theme.Gray
import com.gc.bookleash.ui.theme.poppinsFontFamily


@Composable
fun CustomNavigationBar(
    currentDestination: NavDestination?,
    items: List<NavItems>,
    onItemClick: (NavItems) -> Unit
) {

    val bottomFade = Brush.verticalGradient(0f to Color.Transparent, 0.2f to Color.Black.copy(0.2f))
    Box(
        modifier = Modifier.fillMaxWidth().background(bottomFade),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                CustomNavigationBarItem(
                    item = item,
                    isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onItemClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
fun CustomNavigationBarItem(
    item: NavItems,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    val size by animateDpAsState(
        targetValue = if (isSelected) 60.dp else 30.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 30.dp else 24.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Blue40 else Color.White,
        animationSpec = tween(durationMillis = 500)
    )
    val iconTint by animateColorAsState(
        targetValue = if (isSelected) Color.White else Gray,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                onClick = onItemClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = item.icon),
                contentDescription = item.label,
                modifier = Modifier.size(iconSize),
                tint = iconTint
            )
        }
        if (!isSelected) {
            Text(
                text = item.label,
                color = Gray,
                fontSize = 10.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold)
        }
    }
}