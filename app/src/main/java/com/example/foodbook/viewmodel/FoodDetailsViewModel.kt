    package com.example.foodbook.viewmodel

    import android.app.Application
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.example.foodbook.model.Food
    import com.example.foodbook.services.BesinDatabase
    import kotlinx.coroutines.launch

    class FoodDetailsViewModel(application: Application):BaseViewModel(application)
    {
        var besinliveData=MutableLiveData<Food>()

        fun roomVerisiniAl(uuid : Int){
            launch {

                val dao = BesinDatabase(getApplication()).foodDao()
                val besin = dao.getBesin(uuid)
                besinliveData.value = besin
            }
        }

    }