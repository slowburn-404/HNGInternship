package dev.borisochieng.swipeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dev.borisochieng.swipeshop.data.ProductRepository
import dev.borisochieng.swipeshop.ui.SharedViewModel
import dev.borisochieng.swipeshop.ui.SharedViewModelFactory
import dev.borisochieng.swipeshop.ui.screens.MainScreen
import dev.borisochieng.swipeshop.ui.theme.SwipeShopTheme

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels {
        SharedViewModelFactory((application as SwipeShopApplication).productsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwipeShopTheme {
                MainScreen(viewModel = sharedViewModel)
            }
        }
    }
}