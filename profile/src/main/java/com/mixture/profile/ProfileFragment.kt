package com.mixture.profile

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mixture.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    lateinit var viewBinder: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinder = FragmentProfileBinding.bind(view)
        context?.let { ctx ->
            if (ctx is AppCompatActivity) {
                ctx.setSupportActionBar(viewBinder.profileToolbar)
            }
        }
        viewBinder.profileToolbar.setNavigationIcon(R.drawable.ic_linkedin)
        viewBinder.profileToolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view?.let {
            Navigation.findNavController(it).popBackStack()
        }
        return true
    }
}