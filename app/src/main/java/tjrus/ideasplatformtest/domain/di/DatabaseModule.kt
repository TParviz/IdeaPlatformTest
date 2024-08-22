package tjrus.ideasplatformtest.domain.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tjrus.ideasplatformtest.domain.db.AppDatabase
import tjrus.ideasplatformtest.domain.db.itemInfo.ItemInfoDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).createFromAsset("db/data.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideItemDao(database: AppDatabase): ItemInfoDAO {
        return database.itemInfoDAO()
    }
}