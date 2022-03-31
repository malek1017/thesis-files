package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.app.MyApplication
import com.example.groceryapp.app.UtilFunctions.Companion.databaseDao
import com.example.groceryapp.databinding.LayoutOrderItemBinding
import com.example.groceryapp.databinding.LayoutProductItemBinding
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class OrdersAdapter(var orderList: MutableList<Order>, private val interaction: Interaction? = null) :
    RecyclerView.Adapter<OrdersAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayoutOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = orderList[position]
        holder.setData(current, position)

    }

    override fun getItemCount(): Int = orderList.size

    inner class MyViewHolder(
        private val binding: LayoutOrderItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {


        fun setData(current: Order, position: Int) {

            current.let {

                binding.tvFullName.text = current.fullName
                binding.tvAddress.text = current.address

                CoroutineScope(IO).launch {
                    val product = databaseDao.getProductById(current.productId)
                    CoroutineScope(Main).launch {

                        binding.ivProductImg.setImageDrawable(ContextCompat.getDrawable(MyApplication.instance, product.image))

                        binding.tvTitle.text = product.title
                        binding.tvPrice.text = product.cost
                    }
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Order)
    }
}
