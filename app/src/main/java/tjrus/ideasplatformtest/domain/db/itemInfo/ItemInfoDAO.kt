package tjrus.ideasplatformtest.domain.db.itemInfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemInfoDAO {
    @Query("SELECT * FROM item WHERE name LIKE '%' || :query || '%'")
    fun searchItemsByName(query: String): List<ItemInfoEntity>

    @Query("SELECT * FROM item ORDER BY time")
    fun getAllItems(): List<ItemInfoEntity>

    @Update
    suspend fun updateItem(item: ItemInfoEntity)

    @Delete
    suspend fun deleteItem(item: ItemInfoEntity)
}
