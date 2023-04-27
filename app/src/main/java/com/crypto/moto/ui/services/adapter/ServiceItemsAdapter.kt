package com.crypto.moto.ui.services.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crypto.moto.R
import com.crypto.moto.domain.ServiceItem
import com.crypto.moto.databinding.ServiceItemBinding

class ServiceItemsAdapter(
    private val serviceListItems: List<ServiceItem>,
    private val avatarOrNameItemClickListener: ((serviceItem: ServiceItem) -> Unit)?,
    private var otherItemClickListener: () -> Unit?
) : RecyclerView.Adapter<ServiceItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val v = ServiceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return ViewHolder(v, avatarOrNameItemClickListener, otherItemClickListener)
    }
    override fun getItemCount(): Int {
        return serviceListItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItem(serviceListItems[position])
    }

    class ViewHolder(private var transferItemBinding: ServiceItemBinding,
                     private var avatarOrNameItemClickListener: ((serviceItem: ServiceItem) -> Unit)?,
                     private var otherItemClickListener: () -> Unit?) :
        RecyclerView.ViewHolder(transferItemBinding.root) {

            fun bindItem(serviceItem: ServiceItem) {
                transferItemBinding.iconIm.setOnClickListener {
                    avatarOrNameItemClickListener?.invoke(serviceItem)
                }
                transferItemBinding.nameTv.setOnClickListener {
                    avatarOrNameItemClickListener?.invoke(serviceItem)
                }
                transferItemBinding.descrTv.setOnClickListener {
                    otherItemClickListener.invoke()
                }
                transferItemBinding.rightIv.setOnClickListener {
                    otherItemClickListener.invoke()
                }
                transferItemBinding.locationTv.setOnClickListener {
                    otherItemClickListener.invoke()
                }
                transferItemBinding.rateTv.setOnClickListener {
                    otherItemClickListener.invoke()
                }
                transferItemBinding.ratingView.setOnClickListener {
                    otherItemClickListener.invoke()
                }

                transferItemBinding.descrTv.text = serviceItem.type

                val fullName = serviceItem.author.firstName
                    .plus(" ")
                    .plus(serviceItem.author.lastName ?: "")
                val countryCityName = serviceItem.country.nicename
                    .plus(", ")
                    .plus(serviceItem.city)

                transferItemBinding.nameTv.text = fullName
                transferItemBinding.descrTv.text = serviceItem.type
                transferItemBinding.locationTv.text = countryCityName
                Glide.with(transferItemBinding.root)
                    .load(serviceItem.photo.downloadUrl)
                    .error(R.drawable.ic_biker)
                    .into(transferItemBinding.iconIm)
                transferItemBinding.rateTv.text = serviceItem.rating?.toString() ?: ""
                transferItemBinding.ratingView.setRating(serviceItem.rating?.toFloat() ?: 0f)
            }
    }
}