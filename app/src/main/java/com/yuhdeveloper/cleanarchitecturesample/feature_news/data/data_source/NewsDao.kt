package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("select * from News")
    fun getNews():Flow<List<News>>

    @Query("select * from News where id = :id")
    suspend fun getNewsById(id:Int):News

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(news:News)

    @Delete
    suspend fun deleteNews(news:News)
}