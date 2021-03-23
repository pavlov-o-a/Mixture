package com.mixture.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mixture.profile.databinding.FragmentProfileBinding

private const val GMAIL_URL = "mailto:forpoststuff@gmail.com"
private const val LINKEDIN_URL = "https://www.linkedin.com/in/pavlov-oleg/"
private const val GIT_URL = "https://github.com/pavlov-o-a"
private const val DYNAMIC_FEATURE_LINK =
    "https://developer.android.com/guide/app-bundle/play-feature-delivery"

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
        setHasOptionsMenu(true)
        viewBinder.profileToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
        viewBinder.gmailLink.setOnClickListener {
            sendIntentSafely(Intent(Intent.ACTION_SENDTO, Uri.parse(GMAIL_URL)))
        }
        viewBinder.linkedinLink.setOnClickListener {
            sendIntentSafely(Intent(Intent.ACTION_VIEW, Uri.parse(LINKEDIN_URL)))
        }
        viewBinder.gitLink.setOnClickListener {
            sendIntentSafely(Intent(Intent.ACTION_VIEW, Uri.parse(GIT_URL)))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(com.mixture.common.R.menu.info_menu, menu)
        menu.findItem(com.mixture.common.R.id.menu_info).setOnMenuItemClickListener {
            val linkSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    sendIntentSafely(Intent(Intent.ACTION_VIEW, Uri.parse(DYNAMIC_FEATURE_LINK)))
                }
            }
            val message = SpannableStringBuilder(getString(R.string.profile_info)).apply {
                setSpan(linkSpan, 17, 31, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            AlertDialog.Builder(requireContext())
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
            true
        }
    }

    private fun sendIntentSafely(intent: Intent) {
        try {
            startActivity(intent)
        } catch (exc: Exception) {
            Toast.makeText(
                requireContext(),
                getString(R.string.profile_link_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}