package tjrus.ideasplatformtest.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tjrus.ideasplatformtest.R
import tjrus.ideasplatformtest.data.models.ItemInfo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColumnItem(
    item: ItemInfo,
    onDeleteClick: (ItemInfo) -> Unit,
    onUpdateClick: (Int) -> Unit
) {
    var updatedAmount by remember(key1 = item.id) { mutableIntStateOf(item.amount) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUpdateDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W600, fontSize = 18.sp
                    )
                )

                ColumnItemActions(
                    onUpdateClick = { showUpdateDialog = true },
                    onDeleteClick = { showDeleteDialog = true }
                )
            }

            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    onConfirmation = {
                        onDeleteClick(item)
                        showDeleteDialog = false
                    },
                    dialogTitle = stringResource(id = R.string.delete_item),
                    dialogText = stringResource(id = R.string.delete_item_dialog_text),
                    icon = Icons.Default.Warning
                )
            }

            if (showUpdateDialog) {
                ShowUpdateAmountDialog(
                    updatedAmount = updatedAmount,
                    onAmountChange = { updatedAmount = it },
                    onDismissRequest = {
                        showUpdateDialog = false
                        updatedAmount = item.amount
                    },
                    onConfirm = {
                        onUpdateClick(updatedAmount)
                        showUpdateDialog = false
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy((-10).dp)
            ) {
                item.tags.forEach {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = it
                            )
                        })
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            ColumnItemDetails(
                amount = item.amount,
                dateAdded = SimpleDateFormat(
                    "dd.MM.yyyy",
                    Locale.getDefault()
                ).format(Date(item.time))
            )
        }
    }
}

@Composable
fun ColumnItemActions(
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onUpdateClick,
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.edit_icon_description),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onDeleteClick,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_icon_description),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun ShowUpdateAmountDialog(
    updatedAmount: Int,
    onAmountChange: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                Icons.Default.Settings,
                contentDescription = stringResource(id = R.string.settings_icon_description)
            )
        },
        title = { Text(text = stringResource(id = R.string.item_amount)) },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (updatedAmount > 0) onAmountChange(updatedAmount - 1)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(id = R.string.minus_icon_description),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Text(
                    text = updatedAmount.toString(),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W500)
                )

                IconButton(
                    onClick = { onAmountChange(updatedAmount + 1) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowForward,
                        contentDescription = stringResource(id = R.string.plus_icon_description),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(id = R.string.accept))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}