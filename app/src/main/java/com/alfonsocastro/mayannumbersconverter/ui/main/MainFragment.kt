package com.alfonsocastro.mayannumbersconverter.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alfonsocastro.mayannumbersconverter.R
import com.alfonsocastro.mayannumbersconverter.databinding.MainFragmentBinding
import com.alfonsocastro.mayannumbersconverter.utils.PLAY_STORE_LINK
import com.alfonsocastro.mayannumbersconverter.utils.PRIVACY_POLICY_LINK
import com.alfonsocastro.mayannumbersconverter.utils.TWITTER_PROFILE_LINK
import com.alfonsocastro.mayannumbersconverter.utils.showAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.decimalToMayanBtn.setOnClickListener { findNavController().navigate(R.id.action_to_mayan) }
        binding.mayanToDecimalBtn.setOnClickListener { findNavController().navigate(R.id.action_to_decimal) }

        displayAds()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.share_action -> {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            val message = String.format(getString(R.string.share_app_text), PLAY_STORE_LINK)
                            putExtra(Intent.EXTRA_TEXT, message)
                            type = "text/plain"
                        }


                        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_app_title))
                        startActivity(shareIntent)
                        true
                    }
                    R.id.rate_action -> {
                        val reviewIntent: Intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(PLAY_STORE_LINK)
                        }
                        startActivity(reviewIntent)
                        true
                    }
                    R.id.about_action -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle(R.string.about_title)
                            .setMessage(R.string.about_description)
                            .setNeutralButton(R.string.about_contact_link) { _, _ ->
                                val contactIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_VIEW
                                    data = Uri.parse(TWITTER_PROFILE_LINK)
                                }
                                startActivity(contactIntent)
                            }
                            .setPositiveButton(android.R.string.ok, null)
                            .create()
                        dialog.show()
                        true
                    }
                    R.id.privacy_policy_action -> {
                        val privacyIntent: Intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(PRIVACY_POLICY_LINK)
                        }
                        startActivity(privacyIntent)
                        true
                    }
                    else -> false
                }
            }
            
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun displayAds() {
        if (showAds) {
            MobileAds.initialize(requireContext()) {}

            val adRequest = AdRequest.Builder().build()
            binding.mainBottomBanner.apply {
                visibility = View.VISIBLE
                loadAd(adRequest)
            }
        } else {
            binding.mainBottomBanner.visibility = View.GONE
        }
    }

}