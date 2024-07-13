package dev.borisochieng.timbushop.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.presentation.ui.theme.MalltiverseTheme

@Composable
fun LazyRowWithScrollIndicator(
    products: List<DomainProduct>,
    categoryName: String
) {
    val lazyListState = rememberLazyListState()

    val scrollProgress = remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset to lazyListState.firstVisibleItemIndex }
            .collect { (offset, index) ->
                val totalItems = lazyListState.layoutInfo.totalItemsCount

                if (totalItems > 0) {
                    val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
                    val totalVisibleItemWidth = visibleItems.sumOf { it.size }.toFloat()
                    val totalScrollableWidth =
                        totalVisibleItemWidth * totalItems / visibleItems.size
                    scrollProgress.value =
                        (index * totalVisibleItemWidth + offset) / totalScrollableWidth
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = categoryName,
            modifier = Modifier
                .fillMaxWidth(),
            style = MalltiverseTheme.typography.titleLarge
        )

        LazyRow(
            modifier = Modifier
                .height(360.dp)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            state = lazyListState
        ) {
            items(products) { product ->
                ProductCard(
                    modifier = Modifier,
                    product = product,
                    onClick = {}
                )
            }
        }
    }

    // Scroll indicator as dots
    val dotCount = products.size
    val dotSize = 10.dp
    val dotSpacing = 4.dp
    val dotBorderWidth = 1.dp
    val activeDotColor = MalltiverseTheme.colorScheme.primary
    val inactiveDotColor = Color.Gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(dotSpacing)
        ) {
            repeat(dotCount) { index ->
                val isActive = scrollProgress.value >= index / (dotCount - 1).toFloat()
                Box(
                    modifier = Modifier
                        .size(dotSize)
                        .border(
                            width = dotBorderWidth,
                            color = if(isActive) Color.Transparent else inactiveDotColor,
                            shape = CircleShape
                        )
                        .background(
                            color = if(isActive) activeDotColor else Color.Transparent,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
