package com.crypto.moto.ui.services

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.crypto.moto.R
import com.crypto.moto.databinding.FragmentServicesBinding
import com.crypto.moto.domain.ServiceItem
import com.crypto.moto.ui.details.ServiceDetailsActivity
import com.crypto.moto.ui.details.ServiceDetailsActivity.Companion.SERVICE_ITEM
import com.crypto.moto.ui.services.adapter.ServiceItemsAdapter
import com.crypto.moto.viewmodel.ServicesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ServicesFragment : Fragment() {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    private val servicesViewModel by viewModel<ServicesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                servicesViewModel.state.collect {
                    setupRecycleView(it.services)
                    if (it.isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                    }
                    if (!it.isConnected) {
                        showToastMessageNetworkError()
                    }
                }
            }
        }

        return binding.root
    }

    private fun setupRecycleView(serviceListItems: List<ServiceItem>) {
        binding.servicesItemsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ServiceItemsAdapter(serviceListItems,
                avatarOrNameItemClickListener = {
                    openUserProfile(it)
                },
                otherItemClickListener = {
                    showToastMessageUnderConstruction()
                }
            )
        }
    }

    private fun openUserProfile(serviceItem: ServiceItem) {
        val intent = Intent(requireContext(), ServiceDetailsActivity::class.java)
        intent.putExtra(SERVICE_ITEM, serviceItem)
        startActivity(intent)
    }

    private fun showToastMessageUnderConstruction() {
        Toast.makeText(requireContext(), getString(R.string.under_construction), Toast.LENGTH_SHORT).show()
    }

    private fun showToastMessageNetworkError() {
        Toast.makeText(requireContext(), getString(R.string.network_not_available), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}