package com.blogspot.alansolisflores.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.blogspot.alansolisflores.dao.NoteDAO
import com.blogspot.alansolisflores.entities.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class],version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDAO(): NoteDAO

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope):NoteDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                                                NoteDatabase::class.java,
                                          "note_Database")
                                                .fallbackToDestructiveMigration()
                                                .addCallback(NotesDatabaseCallback(scope))
                                                .build()

                INSTANCE = instance
                return instance
            }

        }

        private class NotesDatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {
                    database -> scope.launch {

                        val noteDAO = database.noteDAO()
                        noteDAO.insert(Note(null,"Title 1","Description 1",1))
                        noteDAO.insert(Note(null,"Title 2","Description 2",2))
                        noteDAO.insert(Note(null,"Title 3","Description 3",3))
                    }
                }
            }
        }
    }
}