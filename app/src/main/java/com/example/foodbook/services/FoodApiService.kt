        package com.example.foodbook.services

        import com.example.foodbook.model.Food
        import io.reactivex.Single
        import retrofit2.Retrofit
        import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
        import retrofit2.converter.gson.GsonConverterFactory

        class FoodApiService
        {
            private var BASE_URL="https://raw.githubusercontent.com/"
            private var api=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(FoodApi::class.java)
            fun getData():Single<List<Food>>
            {
                return api.getFood()
            }
        }