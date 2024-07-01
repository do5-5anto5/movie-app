package com.do55anto5.movieapp.presenter.main.bottom_bar.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.do55anto5.movieapp.R
import com.do55anto5.movieapp.databinding.ItemUserProfileBinding
import com.do55anto5.movieapp.domain.model.MenuProfile
import com.do55anto5.movieapp.domain.model.MenuProfileType

class ProfileMenuAdapter(
    private val items: List<MenuProfile>,
    private val context: Context,
    private val onClick: (MenuProfileType) -> Unit
): RecyclerView.Adapter<ProfileMenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUserProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]

       with(holder) {
           binding.txtItemProfile.apply {
               text = context.getString(item.text)
               if (item.type == MenuProfileType.LOGOUT) {
                   setTextColor(ContextCompat.getColor(context, R.color.color_default))
               }
           }
           binding.imgItemProfile.setImageDrawable(
               ContextCompat.getDrawable(context, item.icon)
           )
           itemView.setOnClickListener {
               onClick(item.type)
           }
       }
    }

    inner class MyViewHolder(val binding: ItemUserProfileBinding): RecyclerView.ViewHolder(binding.root)

}