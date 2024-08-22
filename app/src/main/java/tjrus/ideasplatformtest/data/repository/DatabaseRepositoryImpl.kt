package tjrus.ideasplatformtest.data.repository

import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoDAO
import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepositoryImpl @Inject constructor(
    private val itemInfoDAO: ItemInfoDAO,
) : DatabaseRepository {

    override suspend fun getAllItems(): List<ItemInfoEntity> {
        return itemInfoDAO.getAllItems()
    }

    override suspend fun searchItemsByName(query: String): List<ItemInfoEntity> {
        return itemInfoDAO.searchItemsByName(query)
    }

    override suspend fun updateItem(item: ItemInfoEntity) {
        itemInfoDAO.updateItem(item)
    }

    override suspend fun deleteItem(item: ItemInfoEntity) {
        itemInfoDAO.deleteItem(item)
    }
}