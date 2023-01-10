package com.example.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodbook.R
import com.example.foodbook.adapter.FoodRecyclerAdapter
import com.example.foodbook.model.Food
import com.example.foodbook.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*

class FoodListFragment : Fragment() {
    private lateinit var viewModel:FoodListViewModel
    private var recyclerFoodAdapter=FoodRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()
        foodListRecycler.layoutManager=LinearLayoutManager(context)
        foodListRecycler.adapter=recyclerFoodAdapter
        swipeRefreshLayout.setOnRefreshListener{
         foodProgressBar.visibility=View.VISIBLE
         foodHataMessage.visibility=View.GONE
         foodListRecycler.visibility=View.GONE
         viewModel.refreshData()
         swipeRefreshLayout.isRefreshing=false
        }
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.besinler.observe(viewLifecycleOwner,Observer{
            it.let {
                foodListRecycler.visibility=View.VISIBLE
                recyclerFoodAdapter.besinListesiniGuncelle(it)

            }
        })
        viewModel.besinHataMesajı.observe(viewLifecycleOwner,Observer{hata->
            hata?.let {
             if (it===true)
             {
                 foodHataMessage.visibility=View.VISIBLE
                 foodListRecycler.visibility=View.GONE
             }
             else
             {
                foodHataMessage.visibility=View.GONE
             }
            }
        })
        viewModel.besinYukleniyor.observe(viewLifecycleOwner,Observer{yukleniyor->
            yukleniyor?.let {
                if (it==true)
                {
                    foodListRecycler.visibility=View.GONE
                    foodHataMessage.visibility=View.GONE
                    foodProgressBar.visibility=View.VISIBLE

                }else
                {
                    foodProgressBar.visibility=View.GONE
                }

            }

        })
    }

}