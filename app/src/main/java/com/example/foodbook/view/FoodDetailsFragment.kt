package com.example.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodbook.R
import com.example.foodbook.databinding.FragmentFoodDetailsBinding
import com.example.foodbook.viewmodel.FoodDetailsViewModel
import kotlinx.android.synthetic.main.besin_recycler_row.*
import kotlinx.android.synthetic.main.fragment_food_details.*


class FoodDetailsFragment : Fragment() {
    private var foodId=0
    private lateinit var dataBinding:FragmentFoodDetailsBinding
    private lateinit var viewmodel:FoodDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_food_details,container,false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            foodId=FoodDetailsFragmentArgs.fromBundle(
                it
            ).besinId
        }
        viewmodel = ViewModelProviders.of(this).get(FoodDetailsViewModel::class.java)
        viewmodel.roomVerisiniAl(foodId)
        observeLiveData()
    }
    fun observeLiveData()
    {
        viewmodel.besinliveData.observe(viewLifecycleOwner,Observer{besin->
            besin?.let{

                dataBinding.secilenBesin=it
                /*
                foodName.text=it.besinisim
                foodCalory.text=it.besinKalori
                foodDetailsCarbonhidrat.text=it.besinKarbonhidrat
                foodDetailsProtein.text=it.besinProtein
                foodDetailsYag.text=it.besinYag*/
            }
        })
    }
}