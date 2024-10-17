package com.baseproject.ui.mainModel.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baseproject.R
import com.baseproject.data.model.Post
import com.baseproject.databinding.ItemListBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DataListAdapter(
    private val mList: ArrayList<Post>,
    var context: Context
) :
    RecyclerView.Adapter<DataListAdapter.ViewHolder>() {

    lateinit var binder: ItemListBinding
    lateinit var tagListAdapter: TagListAdapter

    class ViewHolder(@NonNull binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemListBinding = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false
        )

        return ViewHolder(binder)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("RecyclerView", "SetTextI18n", "SimpleDateFormat", "NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder._binding.tvTitle.text = item.title
        holder._binding.tvBody.text = item.body
        holder._binding.tvLike.text = item.reactions.likes.toString()
        holder._binding.tvDislike.text = item.reactions.dislikes.toString()
        holder._binding.tvViews.text = item.views.toString()
        tagListAdapter = TagListAdapter(item.tags, context)
        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder._binding.rvTags.layoutManager = layoutManager
        holder._binding.rvTags.adapter = tagListAdapter
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val date = inputFormat.parse(dateString)
        return outputFormat.format(date!!)
    }

}