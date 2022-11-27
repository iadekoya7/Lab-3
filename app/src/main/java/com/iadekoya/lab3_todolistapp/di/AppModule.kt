package com.iadekoya.lab3_todolistapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iadekoya.lab3_todolistapp.data.TodoDatabase
import com.iadekoya.lab3_todolistapp.data.TodoRepo
import com.iadekoya.lab3_todolistapp.data.TodoRepoImpl
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
    fun provideTodoDb(
        app: Application
    ) : TodoDatabase{
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(database: TodoDatabase): TodoRepo{
        return TodoRepoImpl(database.todoDao)
    }

}