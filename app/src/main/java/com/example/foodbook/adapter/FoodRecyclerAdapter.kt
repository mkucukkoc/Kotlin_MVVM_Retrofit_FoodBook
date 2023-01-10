    package com.example.foodbook.adapter
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.databinding.DataBindingUtil
    import androidx.navigation.Navigation
    import androidx.recyclerview.widget.RecyclerView
    import com.example.foodbook.R
    import com.example.foodbook.databinding.BesinRecyclerRowBinding
    import com.example.foodbook.model.Food
    import com.example.foodbook.util.gorselIndir
    import com.example.foodbook.util.placeholderYap
    import com.example.foodbook.view.FoodListFragmentDirections
    import kotlinx.android.synthetic.main.besin_recycler_row.view.*

    class FoodRecyclerAdapter(var beinListesi:ArrayList<Food>) :RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>(),BesinClickListener{
        class FoodViewHolder (var view: BesinRecyclerRowBinding):RecyclerView.ViewHolder(view.root){

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
            var inflater=LayoutInflater.from(parent.context)
            //var view=inflater.inflate(R.layout.besin_recycler_row,parent,false)
            val view =DataBindingUtil.inflate<BesinRecyclerRowBinding>(inflater,R.layout.besin_recycler_row,parent,false)
            return FoodViewHolder(view)
        }
        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

            holder.view.besin=beinListesi[position]
            holder.view.listener=this
           /* holder.itemView.foodName.text=beinListesi.get(position).besinisim
            holder.itemView.foodCalory.text=beinListesi.get(position).besinKalori
            holder.itemView.setOnClickListener {
                var action=FoodListFragmentDirections.actionFoodListToFoodDetails()
                Navigation.findNavController(it).navigate(action)
            }
            //bu kod ile verileri yüklerken görseller gelirken yuklenıyor ibaresi gelmesini sağlıyor
            holder.itemView.imageView.gorselIndir(beinListesi.get(position).besinGorsel,
                placeholderYap(holder.itemView.context)
            )*/

        }
        override fun getItemCount(): Int {
            return beinListesi.size
        }
        fun besinListesiniGuncelle(yeniBesinLsitesi:List<Food>)
        {
            beinListesi.clear()
            beinListesi.addAll(yeniBesinLsitesi)
            //NotifyDataSetChanged() : Adaptore verinin değiştiğini bildirmek için kullanıyoruz
            notifyDataSetChanged()
        }

        override fun besinTiklandi(view: View) {
            var uuid=view.uuid.text.toString().toIntOrNull()
            uuid?.let {
                var action=FoodListFragmentDirections.actionFoodListToFoodDetails(it)
                Navigation.findNavController(view).navigate(action)
            }

        }
    }