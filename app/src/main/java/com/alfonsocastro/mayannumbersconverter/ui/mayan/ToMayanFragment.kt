package com.alfonsocastro.mayannumbersconverter.ui.mayan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfonsocastro.mayannumbersconverter.adapters.MayanNumberAdapter
import com.alfonsocastro.mayannumbersconverter.databinding.ToMayanFragmentBinding
import com.alfonsocastro.mayannumbersconverter.utils.showAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class ToMayanFragment : Fragment() {

    private lateinit var viewModel: ToMayanViewModel
    private var _binding: ToMayanFragmentBinding? = null
    private var adapter: MayanNumberAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ToMayanFragmentBinding.inflate(inflater, container, false)

        binding.convertButton.setOnClickListener { onConvertButtonClicked() }

        binding.mayanResultView.layoutManager = LinearLayoutManager(requireContext())

        displayAds()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ToMayanViewModel::class.java]
        viewModel.mayanNumberLiveData.observe(this.viewLifecycleOwner) {mayanString ->
            adapter?.submitList(mayanString.toList())
        }
        adapter = MayanNumberAdapter(layoutInflater)

        binding.mayanResultView.adapter = adapter

    }

    private fun onConvertButtonClicked(){
        val decimalString = binding.numberInput.text.toString()
        // Log.d("ToMayanFragment", "String decimal: $decimalString")
        if (decimalString.isNotEmpty()) {
            val decimal = decimalString.toIntOrNull()
            if (decimal != null) {
                viewModel.convert(decimal)
            }
        }
    }

    private fun displayAds() {
        if (showAds) {
            MobileAds.initialize(requireContext()) {}

            val adRequest = AdRequest.Builder().build()
            binding.mayanBottomBanner.apply {
                visibility = View.VISIBLE
                loadAd(adRequest)
            }
        } else {
            binding.mayanBottomBanner.visibility = View.GONE
        }
    }
}