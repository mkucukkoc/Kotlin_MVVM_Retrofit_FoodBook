        package com.example.foodbook.services

        import com.example.foodbook.model.Food
        import io.reactivex.Single
        import retrofit2.http.GET

        interface FoodApi
        {
            @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
            fun getFood():Single<List<Food>>
        }