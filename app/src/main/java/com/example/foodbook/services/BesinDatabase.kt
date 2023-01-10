package com.example.foodbook.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodbook.model.Food

    @Database(entities = arrayOf(Food::class), version = 1)
    abstract class BesinDatabase:RoomDatabase()
    {
        abstract fun foodDao():FoodDAO
        //Singleton
        //Özünde herhangi bir class'tan birden fazla obje oluşturduğumuzda
        // bunlar memory'de ayrı ayrı yer kaplar.
        // Singleton ise bir class'ın dışarıdan birden fazla instance'ını oluşturmayı
        // engeller.
        companion object
        {
            @Volatile private var instance:BesinDatabase?=null
            private var lock=Any()
            operator fun invoke(context: Context)= instance?: synchronized(lock)
            {
                instance?:databaseOlustur(context).also{
                    instance=it
                }
            }
            private fun databaseOlustur(context: Context)= Room.databaseBuilder(
            context.applicationContext ,BesinDatabase::class.java,"besindatabase").build()
        }

    }