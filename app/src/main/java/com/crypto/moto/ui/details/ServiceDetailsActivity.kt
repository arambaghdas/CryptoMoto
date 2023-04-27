package com.crypto.moto.ui.details

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.crypto.moto.R
import com.crypto.moto.databinding.ActivityServiceDetailsBinding
import com.crypto.moto.domain.ServiceItem

@Suppress("DEPRECATION")
class ServiceDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityServiceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(SERVICE_ITEM,  ServiceItem::class.java)
        } else {
            intent.getSerializableExtra(SERVICE_ITEM) as ServiceItem
        }

        Glide.with(binding.root)
            .load(serviceItem?.photo?.downloadUrl)
            .error(R.drawable.ic_biker)
            .into(binding.iconIm)

        binding.nameTv.text = serviceItem?.author?.firstName
        binding.titleTv.text = serviceItem?.title
        binding.descriptionTv.text = serviceItem?.description
        binding.cityTv.text = serviceItem?.city
        binding.addressTv.text = serviceItem?.address
        binding.phoneTv.text = serviceItem?.phone
        binding.publicEmailTv.text = serviceItem?.publicEmail
        binding.websiteTv.text = serviceItem?.website
        binding.workingHoursTv.text = serviceItem?.workingHours
        binding.ratesTv.text = serviceItem?.rating?.toString()
    }

    companion object {
        const val SERVICE_ITEM = "serviceItem"
    }
}