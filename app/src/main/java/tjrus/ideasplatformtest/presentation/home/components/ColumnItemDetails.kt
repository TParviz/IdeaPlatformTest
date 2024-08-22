package tjrus.ideasplatformtest.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tjrus.ideasplatformtest.R

@Composable
fun ColumnItemDetails(amount: Int, dateAdded: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.in_stock),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
            )
            Text(
                text = amount.toString(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400)
            )
        }

        Column(
            modifier = Modifier.padding(end = 40.dp)
        ) {
            Text(
                text = stringResource(id = R.string.date_added),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
            )
            Text(
                text = dateAdded,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400)
            )
        }
    }
}
