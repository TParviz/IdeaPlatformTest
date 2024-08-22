package tjrus.ideasplatformtest.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoDAO
import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoEntity

@Database(entities = [ItemInfoEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemInfoDAO(): ItemInfoDAO
}
