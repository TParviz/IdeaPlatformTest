package tjrus.ideasplatformtest.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tjrus.ideasplatformtest.data.models.ItemInfo
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreenContent(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    itemsList: List<ItemInfo>,
    onDeleteItem: (ItemInfo) -> Unit,
    onUpdateItem: (ItemInfo, Int) -> Unit
) {
    Column(modifier = Modifier.padding(12.dp)) {
        SearchBar(searchQuery = searchQuery, onSearchQueryChange = onSearchQueryChange)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn {
            items(itemsList) { item ->
                ColumnItem(
                    item = item,
                    onDeleteClick = onDeleteItem,
                    onUpdateClick = { updatedAmount -> onUpdateItem(item, updatedAmount) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}