package com.example.foodbook.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodbook.model.Food

@Dao
    interface FoodDao
    {
        @Insert
        suspend fun insertAll(vararg food:Food):List<Long>
        //@Insert SQL LİTE DA KULLANILAN İNSERT İNTO DUR.
        //vararg ->birden fazla isteigimiz sayıda besin oluşturur.
        //List<long> lonf->long id leri temsil ediyor.
        //tüm besinleri getirecek query
        @Query("SELECT * FROM food")
        suspend fun getAllBesin():List<Food>
        //id ye göre besin getirecek
        @Query("Select * from food where uuid=:BesinId")
        suspend fun getByIdBesin(BesinId:Int):Food
        //bütün tablodaki verileri silecek query
        @Query("DELETE FROM food Where uuid=:BesinId")
        suspend fun deleteAllBesin(BesinId:Int):Food
    }