package com.mixture.idcheck.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mixture.common.utils.setVisibility
import com.mixture.idcheck.R
import com.mixture.idcheck.databinding.FragmentPackageCheckBinding
import com.mixture.idcheck.di.DaggerDIComponent
import javax.inject.Inject

class IdCheckFragment : Fragment(R.layout.fragment_package_check) {
    lateinit var viewBinder: FragmentPackageCheckBinding

    @Inject
    lateinit var viewModelProvider: IdCheckViewModelProvider
    private val viewModel: IdCheckViewModel by lazy {
        viewModelProvider.getViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDIComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinder = FragmentPackageCheckBinding.bind(view)
        context?.let { ctx ->
            if (ctx is AppCompatActivity) {
                ctx.setSupportActionBar(viewBinder.packageToolbar)
            }
        }
        setHasOptionsMenu(true)
        viewBinder.packageToolbar.setNavigationOnClickListener {
            viewModel.navigationClicked()
        }
        viewBinder.packageEdit.addTextChangedListener {
            viewModel.textChanged()
        }
        viewBinder.packageCheck.setOnClickListener {
            viewModel.checkClicked(viewBinder.packageEdit.text?.toString() ?: "")
        }
        viewModel.navigateUpLiveData().observe(viewLifecycleOwner) {
            Navigation.findNavController(requireView()).navigateUp()
        }
        viewModel.okVisibility().observe(viewLifecycleOwner) {
            viewBinder.packageOk.setVisibility(it)
        }
        viewModel.wrongVisibility().observe(viewLifecycleOwner) {
            viewBinder.packageWrong.setVisibility(it)
        }
        viewModel.checkVisibility().observe(viewLifecycleOwner) {
            viewBinder.packageCheck.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }
        viewModel.progressVisibility().observe(viewLifecycleOwner) {
            viewBinder.packageProgress.setVisibility(it)
        }
        viewModel.showInfoLiveData().observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(R.string.package_info)
                .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
        }
        viewModel.showError().observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.package_error, it.errorMessage),
                Toast.LENGTH_SHORT
            )
                .show()
        }
        viewModel.shortId().observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.package_short, it),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(com.mixture.common.R.menu.info_menu, menu)
        menu.findItem(com.mixture.common.R.id.menu_info).setOnMenuItemClickListener {
            viewModel.infoClicked()
            true
        }
    }
}