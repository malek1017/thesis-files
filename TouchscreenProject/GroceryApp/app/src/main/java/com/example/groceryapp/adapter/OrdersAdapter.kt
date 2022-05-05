package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.database.ProductDao
import com.example.groceryapp.databinding.LayoutOrderItemBinding
import com.example.groceryapp.model.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class OrdersAdapter(
    private val orderList: MutableList<Order>,
    private val productDao: ProductDao,
) :
    RecyclerView.Adapter<OrdersAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LayoutOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = orderList[position]
        holder.setData(current)
    }

    override fun getItemCount(): Int = orderList.size

    inner class MyViewHolder(
        private val binding: LayoutOrderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {


        fun setData(current: Order) {
            CoroutineScope(IO).launch {
                val product = productDao.getProductById(current.productId)
                CoroutineScope(Main).launch {

                    binding.ivProductImg.setImageDrawable(
                        ContextCompat.getDrawable(binding.root.context, product.image)
                    )

                    binding.tvTitle.text = product.title
                    binding.tvDescription.text = product.description
                    binding.tvPrice.text = product.cost
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Order)
    }
}
