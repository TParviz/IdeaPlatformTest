package tjrus.ideasplatformtest.domain.db.itemInfo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import tjrus.ideasplatformtest.domain.db.Converters

@TypeConverters(Converters::class)
@Entity(tableName = "item")
data class ItemInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)