package com.mixture.packagecheck.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.mixture.packagecheck.R
import com.mixture.packagecheck.databinding.FragmentPackageCheckBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class PackageCheckFragment : Fragment(R.layout.fragment_package_check) {
    lateinit var viewBinder: FragmentPackageCheckBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinder = FragmentPackageCheckBinding.bind(view)
        context?.let { ctx ->
            if (ctx is AppCompatActivity) {
                ctx.setSupportActionBar(viewBinder.profileToolbar)
            }
        }
        setHasOptionsMenu(true)
        viewBinder.packageEdit.addTextChangedListener {
            viewBinder.packageWrong.visibility = View.GONE
            viewBinder.packageOk.visibility = View.GONE
        }
        viewBinder.packageCheck.setOnClickListener {
            MainScope().launch {
                viewBinder.packageCheck.visibility = View.INVISIBLE
                viewBinder.packageProgress.visibility = View.VISIBLE
                delay(2000)
                viewBinder.packageCheck.visibility = View.VISIBLE
                viewBinder.packageProgress.visibility = View.GONE
                if (Random.nextBoolean()) {
                    viewBinder.packageOk.visibility = View.VISIBLE
                    viewBinder.packageWrong.visibility = View.GONE
                } else {
                    viewBinder.packageWrong.visibility = View.VISIBLE
                    viewBinder.packageOk.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(com.mixture.common.R.menu.info_menu, menu)
        menu.findItem(com.mixture.common.R.id.menu_info).setOnMenuItemClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("")
                .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
            true
        }
    }
}