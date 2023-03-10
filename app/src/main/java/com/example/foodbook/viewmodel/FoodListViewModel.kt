        package com.example.foodbook.viewmodel

        import OzelSharedPreferences
        import android.app.Application
        import android.widget.Toast
        import androidx.lifecycle.MutableLiveData
        import androidx.lifecycle.ViewModel
        import com.example.foodbook.model.Food
        import com.example.foodbook.services.BesinDatabase
        import com.example.foodbook.services.FoodApiService
        import io.reactivex.android.schedulers.AndroidSchedulers
        import io.reactivex.disposables.CompositeDisposable
        import io.reactivex.disposables.Disposable
        import io.reactivex.observers.DisposableSingleObserver
        import io.reactivex.schedulers.Schedulers
        import kotlinx.coroutines.launch
        import org.w3c.dom.ls.LSInput

        class FoodListViewModel(application: Application):BaseViewModel(application) {
            var besinler = MutableLiveData<List<Food>>()
            var besinHataMesaj─▒ = MutableLiveData<Boolean>()
            var besinYukleniyor = MutableLiveData<Boolean>()
            private var guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

            private val besinapiServis = FoodApiService()
            private val disposable = CompositeDisposable()
            private val ozelSharedPreferences=OzelSharedPreferences(getApplication())
            fun refreshData() {
                val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
                if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani){
                    //Sqlite'tan ├žek
                    verileriSQLitetanAl()
                } else {
                    verileriInternettenAl()
                }            }
            fun refreshFromInternet(){
                verileriInternettenAl()
            }

            private fun verileriSQLitetanAl(){
                besinYukleniyor.value = true

                launch {

                    val besinLites = BesinDatabase(getApplication()).foodDao().getAllBesin()
                    besinleriGoster(besinLites)
                    Toast.makeText(getApplication(),"Besinleri Room'dan Ald─▒k",Toast.LENGTH_LONG).show()

                }

            }

            private fun verileriInternettenAl() {
                besinYukleniyor.value = true
                //
                disposable.add(
                    besinapiServis.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Food>>() {
                            override fun onSuccess(t: List<Food>) {

                                sqllitesakla(t)
                                Toast.makeText(getApplication(),"Besinleri Internet'ten Ald─▒k",Toast.LENGTH_LONG).show()

                            }
                            override fun onError(e: Throwable) {
                            besinHataMesaj─▒.value=true
                                besinYukleniyor.value=true
                                e.printStackTrace()
                            }

                        })
                )
            }
            private  fun besinleriGoster(besinlerListesi:List<Food>)
            {
                besinler.value=besinlerListesi
                besinHataMesaj─▒.value=false
                besinYukleniyor.value=false
            }
            private fun sqllitesakla(besinListesi: List<Food>)
            {
                launch {
                    var dao=BesinDatabase(getApplication()).foodDao()
                    dao.deleteAllBesin()
                   var uuidListesi= dao.insertAll(*besinListesi.toTypedArray())
                    var i=0
                    while (i<besinListesi.size)
                    {
                        besinListesi[i].uuid=uuidListesi[i].toInt()
                        i=i+1
                    }
                    besinleriGoster(besinListesi)
                }
                ozelSharedPreferences.zamaniKaydet(System.nanoTime())
            }
        }