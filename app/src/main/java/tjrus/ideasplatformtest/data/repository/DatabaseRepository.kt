package tjrus.ideasplatformtest.data.repository

import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoEntity
import javax.inject.Singleton

@Singleton
interface DatabaseRepository {
    suspend fun getAllItems(): List<ItemInfoEntity>
    suspend fun searchItemsByName(query: String): List<ItemInfoEntity>
    suspend fun updateItem(item: ItemInfoEntity)
    suspend fun deleteItem(item: ItemInfoEntity)
}