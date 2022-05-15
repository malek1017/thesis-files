package com.example.groceryapp.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.app.MyApplication
import com.example.groceryapp.databinding.LayoutProductItemBinding
import com.example.groceryapp.model.Product


class ProductsAdapter(var productList: MutableList<Product>, private val interaction: Interaction? = null) :
    RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = productList[position]
        holder.setData(current, position)
    }

    override fun getItemCount(): Int = productList.size

    inner class MyViewHolder(
        private val binding: LayoutProductItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(current: Product, position: Int) {
            binding.ivProductImg.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context, current.image)
            )

            binding.tvTitle.text = current.title
            binding.tvDescription.text = current.description
            binding.tvPrice.text = current.cost

            binding.root.setOnClickListener {
                interaction?.onItemSelected(position, current)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Product)
    }
}
