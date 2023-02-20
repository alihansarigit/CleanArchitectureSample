package com.yuhdeveloper.cleanarchitecturesample.di

import android.app.Application
import androidx.room.Room
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.data_source.NewsDatabase
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.repository.NewsRepositoryImpl
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun executeNewsDB(app:Application):NewsDatabase{
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            NewsDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun executeRepository(database: NewsDatabase): NewsRepository{
        return NewsRepositoryImpl(database.newsDao)
    }

    @Provides
    @Singleton
    fun executeNewsUseCases(repository: NewsRepository): NewsUseCases{
        return NewsUseCases(
            addNews = AddNews(repository),
            deleteNews = DeleteNews(repository),
            getNews = GetNews(repository),
            getNewsById = GetNewsById(repository)
        )
    }

}