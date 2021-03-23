package com.mixture.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mixture.about.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {
    lateinit var viewBinder: FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinder = FragmentAboutBinding.bind(view)
        viewBinder.aboutProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(com.mixture.R.id.nav_to_profile)
        }
        viewBinder.aboutPackage.setOnClickListener {
            Navigation.findNavController(it).navigate(com.mixture.R.id.nav_to_package)
        }
    }
}