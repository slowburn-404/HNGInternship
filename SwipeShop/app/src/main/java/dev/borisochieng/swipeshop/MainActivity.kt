package dev.borisochieng.swipeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import dev.borisochieng.swipeshop.ui.BottomNavItem
import dev.borisochieng.swipeshop.ui.NavigationComponent
import dev.borisochieng.swipeshop.ui.ProductScreen
import dev.borisochieng.swipeshop.ui.theme.SwipeShopTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwipeShopTheme {                
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val navItems = listOf(
                        BottomNavItem(
                            title = "Products",
                            selectedIcon = Icons.Filled.Home,
                            unselectedIcon = Icons.Outlined.Home,
                            hasItem = false
                        ),
                        BottomNavItem(
                            title = "Checkout",
                            selectedIcon = Icons.Filled.ShoppingCart,
                            unselectedIcon = Icons.Outlined.ShoppingCart,
                            hasItem = false,
                            badgeCount = 5
                        )
                    )
                    var selectedItemIndex by rememberSaveable {
                        mutableIntStateOf(0)
                    }
                    Scaffold(
                        topBar = {
                            LargeTopAppBar(
                                title = {
                                    Text(
                                        "SwipeShop",
                                        style = MaterialTheme.typography.displaySmall
                                    )
                                }
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                navItems.forEachIndexed { index, bottomNavItem ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            navController.navigate(bottomNavItem.title) {
                                                popUpTo(navController.graph.startDestinationId)
                                                launchSingleTop = true
                                            }
                                        },
                                        label = {
                                            Text(text = bottomNavItem.title)
                                        },
                                        alwaysShowLabel = false,
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    bottomNavItem.badgeCount?.let {
                                                        Badge {
                                                            Text(text = it.toString())
                                                        }
                                                    }
                                                }) {
                                                Icon(
                                                    imageVector = if (index == selectedItemIndex) {
                                                        bottomNavItem.selectedIcon
                                                    } else bottomNavItem.unselectedIcon,
                                                    contentDescription = bottomNavItem.title
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ){ innerPadding ->
                        NavigationComponent(navController = navController, modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    SwipeShopTheme {
        ProductScreen(modifier = Modifier)
    }
}