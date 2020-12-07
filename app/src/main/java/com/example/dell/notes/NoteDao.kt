package com.example.dell.notes

import androidx.lifecycle.LiveData
import androidx.room.*

//Dao is Data access object
@Dao
interface NoteDao {

    /**
     *suspend is Coroutine in kotlin which makes function to run in background thread
     *
     *and we can only call this function using background thread OR by another suspend function
     *
     * basically purpose of making function suspend is to run in background bcz IO functions are heavy to run on MAIN THREAD
    and app will going to lag all the time
     *
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)


    @Delete
    suspend fun delete(note: Note)


    @Query("Select * From notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
//    LiveData is basically life cycle aware i.e. Whenever data updated activity knows

}