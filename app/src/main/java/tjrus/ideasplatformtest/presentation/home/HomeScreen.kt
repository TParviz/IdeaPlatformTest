package tjrus.ideasplatformtest.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import tjrus.ideasplatformtest.presentation.viewmodel.HomeViewModel
import tjrus.ideasplatformtest.presentation.home.components.AppBar
import tjrus.ideasplatformtest.presentation.home.components.HomeScreenContent
import tjrus.ideasplatformtest.ui.theme.White20

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    var searchQuery by remember { mutableStateOf("") }
    val itemsList by viewModel.itemsList.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White20)
    ) {
        Column {
            AppBar()
            HomeScreenContent(
                searchQuery = searchQuery,
                onSearchQueryChange = {
                    searchQuery = it
                    viewModel.searchItems(searchQuery)
                },
                itemsList = itemsList.filter { it.name.contains(searchQuery, ignoreCase = true) },
                onDeleteItem = { viewModel.deleteItem(it) },
                onUpdateItem = { item, updatedAmount ->
                    viewModel.updateItem(item.copy(amount = updatedAmount))
                }
            )
        }
    }
}