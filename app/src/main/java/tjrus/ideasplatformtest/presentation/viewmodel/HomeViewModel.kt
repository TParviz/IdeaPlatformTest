package tjrus.ideasplatformtest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tjrus.ideasplatformtest.data.models.ItemInfo
import tjrus.ideasplatformtest.data.models.toEntity
import tjrus.ideasplatformtest.domain.useCase.DeleteItemParam
import tjrus.ideasplatformtest.domain.useCase.DeleteItemUseCase
import tjrus.ideasplatformtest.domain.useCase.GetAllItemsUseCase
import tjrus.ideasplatformtest.domain.useCase.SearchByNameUseCase
import tjrus.ideasplatformtest.domain.useCase.UpdateItemParam
import tjrus.ideasplatformtest.domain.useCase.UpdateItemUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val searchByNameUseCase: SearchByNameUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
) : ViewModel() {

    private val _itemsList = MutableStateFlow<List<ItemInfo>>(emptyList())
    val itemsList: StateFlow<List<ItemInfo>> = _itemsList.asStateFlow()

    init {
        getAllItems()
    }

    private fun getAllItems() {
        viewModelScope.launch {
            getAllItemsUseCase(Unit).collect { result ->
                result.onSuccess { itemList ->
                    _itemsList.value = itemList
                }
            }
        }
    }

    fun searchItems(query: String) {
        viewModelScope.launch {
            searchByNameUseCase(query).collect { result ->
                result.onSuccess { itemList ->
                    _itemsList.value = itemList
                }
            }
        }
    }

    fun updateItem(item: ItemInfo) {
        viewModelScope.launch {
            updateItemUseCase(UpdateItemParam(item.toEntity())).collect { result ->
                result.onSuccess {
                    getAllItems()
                }
            }
        }
    }

    fun deleteItem(item: ItemInfo) {
        viewModelScope.launch {
            deleteItemUseCase(DeleteItemParam(item.toEntity())).collect { result ->
                result.onSuccess {
                    getAllItems()
                }
            }
        }
    }
}