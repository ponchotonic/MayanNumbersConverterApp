package com.alfonsocastro.mayannumbersconverter.ui.decimal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfonsocastro.mayannumbersconverter.adapters.MayanNumberAdapter
import com.alfonsocastro.mayannumbersconverter.databinding.ToDecimalFragmentBinding
import com.alfonsocastro.mayannumbersconverter.utils.showAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class ToDecimalFragment : Fragment() {

    private lateinit var viewModel: ToDecimalViewModel
    private var _binding: ToDecimalFragmentBinding? = null
    private var adapter: MayanNumberAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ToDecimalFragmentBinding.inflate(inflater, container, false)

        binding.mayanRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)

        binding.apply {

            m00Button.setOnClickListener{onNumberButtonPressed(0)}
            m01Button.setOnClickListener{onNumberButtonPressed(1)}
            m02Button.setOnClickListener{onNumberButtonPressed(2)}
            m03Button.setOnClickListener{onNumberButtonPressed(3)}
            m04Button.setOnClickListener{onNumberButtonPressed(4)}
            m05Button.setOnClickListener{onNumberButtonPressed(5)}
            m06Button.setOnClickListener{onNumberButtonPressed(6)}
            m07Button.setOnClickListener{onNumberButtonPressed(7)}
            m08Button.setOnClickListener{onNumberButtonPressed(8)}
            m09Button.setOnClickListener{onNumberButtonPressed(9)}
            m10Button.setOnClickListener{onNumberButtonPressed(10)}
            m11Button.setOnClickListener{onNumberButtonPressed(11)}
            m12Button.setOnClickListener{onNumberButtonPressed(12)}
            m13Button.setOnClickListener{onNumberButtonPressed(13)}
            m14Button.setOnClickListener{onNumberButtonPressed(14)}
            m15Button.setOnClickListener{onNumberButtonPressed(15)}
            m16Button.setOnClickListener{onNumberButtonPressed(16)}
            m17Button.setOnClickListener{onNumberButtonPressed(17)}
            m18Button.setOnClickListener{onNumberButtonPressed(18)}
            m19Button.setOnClickListener{onNumberButtonPressed(19)}

            deleteImageButton.setOnClickListener { onDeleteButtonPressed() }
            acButton.setOnClickListener { onAcButtonClicked() }
            equalButton.setOnClickListener { onEqualButtonClicked() }
        }

        displayAds()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ToDecimalViewModel::class.java)
        adapter = MayanNumberAdapter(layoutInflater)
        viewModel.mayanStringLiveData.observe(this.viewLifecycleOwner) { string ->
            adapter?.submitList(string.toList())
        }
        viewModel.decimalResultLiveData.observe(this.viewLifecycleOwner) { int ->
            binding.decimalResultTextView.text = int.toString()
        }
        binding.mayanRecycler.adapter = adapter

    }

    private fun displayAds() {
        if (showAds) {
            MobileAds.initialize(requireContext()) {}

            val adRequest = AdRequest.Builder().build()
            binding.decimalBottomBanner.apply {
                visibility = View.VISIBLE
                loadAd(adRequest)
            }
        } else {
            binding.decimalBottomBanner.visibility = View.GONE
        }
    }

    private fun onNumberButtonPressed(numberPressed: Int) {
        viewModel.addNumber(numberPressed)
        // Log.d(TAG, "Number pressed: $numberPressed. Complete number: ${viewModel.mayanStringLiveData.value}")
    }

    private fun onDeleteButtonPressed() {
        viewModel.deleteNumber()
        // Log.d(TAG, "Delete pressed. New string: $mayanString")
    }

    private fun onAcButtonClicked() {
        viewModel.clearNumbers()
        // Log.d(TAG, "AC pressed. New string: $mayanString")
    }

    private fun onEqualButtonClicked() {
        viewModel.convert()
        // Log.d(TAG, "Equal pressed.")
    }

}