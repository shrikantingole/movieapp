package com.network.shared.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.network.shared.domain.video.VideoItem

@Dao
interface DaoClass {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(deletedMedia: VideoItem): Long

    @Query("SELECT * FROM VideoItem")
    fun listofAll(): LiveData<List<VideoItem>>
//
//    @Delete
//    fun deleteChat(deletedMedia: VideoItem)
//
//
//    @Query("delete FROM videoitem where artistId == :itemId")
//    fun deleteItemById(itemId: Int)


}