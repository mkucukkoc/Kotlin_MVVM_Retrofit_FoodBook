package com.example.foodbook.services
import com.example.foodbook.model.Food
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
public abstract   interface FoodDAO {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg food : Food) : List<Long>

    //Insert -> Room, insert into
    // suspend -> coroutine scope
    // vararg -> birden fazla ve istediğimiz sayıda besin
    // List<Long> -> long, id'ler

    @Query("SELECT * FROM food")
    suspend fun getAllBesin() : List<Food>

    @Query("SELECT * FROM food WHERE uuid = :besinId")
    suspend fun getBesin(besinId : Int) : Food

    @Query("DELETE FROM food")
    suspend fun deleteAllBesin()



}
