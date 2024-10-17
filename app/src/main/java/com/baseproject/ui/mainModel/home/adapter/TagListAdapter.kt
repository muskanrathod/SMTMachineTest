package com.baseproject.ui.mainModel.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.baseproject.R
import com.baseproject.data.model.Post
import com.baseproject.databinding.ItemTagListBinding
import kotlin.collections.ArrayList


class TagListAdapter(
    private val mList: ArrayList<String>,
    var context: Context
) :
    RecyclerView.Adapter<TagListAdapter.ViewHolder>() {

    lateinit var binder: ItemTagListBinding

    class ViewHolder(@NonNull binding: ItemTagListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemTagListBinding = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tag_list,
            parent,
            false
        )

        return ViewHolder(binder)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("RecyclerView", "SetTextI18n", "SimpleDateFormat", "NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder._binding.tvTag.text = "#$item"
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}