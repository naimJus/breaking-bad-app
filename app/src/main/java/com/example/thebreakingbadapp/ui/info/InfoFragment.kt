package com.example.thebreakingbadapp.ui.info

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class InfoFragment : DialogFragment() {
    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog =
        AlertDialog.Builder(context).apply {
            isCancelable = false
            args.argTitle?.let { setTitle(it) }
            args.argBody?.let { setMessage(it) }
            args.argButtonConfirmation?.let {
                setPositiveButton(it) { _, _ ->
                    findNavController().popBackStack(args.argPopTo, false)
                }
            }
        }.create()
}