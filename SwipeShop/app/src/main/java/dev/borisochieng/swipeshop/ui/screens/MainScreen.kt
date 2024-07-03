package dev.borisochieng.swipeshop.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.borisochieng.swipeshop.ui.SharedViewModel
import dev.borisochieng.swipeshop.ui.theme.SwipeShopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: SharedViewModel) {
    val navController = rememberNavController()
    val screens = listOf(
        BottomNavScreen.Products,
        BottomNavScreen.CheckOut
    )

    //get current route from back stack entry
    val navCurrentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navCurrentBackStackEntry?.destination?.route ?: BottomNavScreen.Products.route

    //get selected index based on current route
    val selectedItemIndex = screens.indexOfFirst {
        it.route == currentRoute
    }. takeIf { it >= 0 } ?: 0
    val badgeCount by viewModel.badgeCount.observeAsState(initial = 0)

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        "SwipeShop",
                        style = typography.headlineMedium
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, bottomNavScreen ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            if (selectedItemIndex != index) {
                                navController.navigate(bottomNavScreen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                }
                            }
                        },
                        label = {
                            Text(text = bottomNavScreen.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            //show badge only on checkout
                            if (bottomNavScreen.route == BottomNavScreen.CheckOut.route && badgeCount > 0) {
                                BadgedBox(
                                    badge = {
                                        Badge {
                                            Text(text = "$badgeCount")
                                        }
                                    }) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            bottomNavScreen.selectedIcon
                                        } else bottomNavScreen.unselectedIcon,
                                        contentDescription = bottomNavScreen.title
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        bottomNavScreen.selectedIcon
                                    } else bottomNavScreen.unselectedIcon,
                                    contentDescription = bottomNavScreen.title
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        BottomNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenScreenPreview() {
    SwipeShopTheme {
        //MainScreen()
    }
}