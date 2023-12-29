package com.cevdetkilickeser.holdthenote.di

import android.content.Context
import com.cevdetkilickeser.holdthenote.data.repo.NoteRepository
import com.cevdetkilickeser.holdthenote.database.NoteDao
import com.cevdetkilickeser.holdthenote.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNoteRepository(notedao: NoteDao) : NoteRepository {
        return NoteRepository(notedao)
    }

    @Provides
    @Singleton
    fun provideNoteDao(@ApplicationContext context: Context) : NoteDao {
        val db = NoteDatabase.getDatabase(context)
        return db.getNoteDao()
    }
}