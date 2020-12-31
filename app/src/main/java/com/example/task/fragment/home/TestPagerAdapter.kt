package com.example.task.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entity.localmodels.DataModel
import com.example.task.R
import kotlinx.android.synthetic.main.item_data.view.*


class TestPagerAdapter(private var list: List<DataModel>) :
    RecyclerView.Adapter<TestPagerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(itemList: MutableList<DataModel>) {
        list = itemList
        notifyDataSetChanged()
    }

    fun transformListAndAddTwo(itemList: List<DataModel>): MutableList<DataModel> {
        val size: Int = itemList.size
        val listTemp = mutableListOf<DataModel>()
        for (i in 0..size + 2) {
            listTemp.add(itemList[(i + size - 2) % size])
        }
        return listTemp
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(item: DataModel) {
            with(itemView) {
                Glide.with(context)
                    .load(item.url)
                    .circleCrop()
                    .placeholder(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_launcher_foreground
                        )
                    )
                    .into(icon)
            }
        }
    }
}