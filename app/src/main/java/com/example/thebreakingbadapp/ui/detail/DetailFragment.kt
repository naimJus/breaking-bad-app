package com.example.thebreakingbadapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionInflater
import com.example.thebreakingbadapp.R
import com.example.thebreakingbadapp.data.EventObserver
import com.example.thebreakingbadapp.data.Message
import com.example.thebreakingbadapp.databinding.FragmentDetailBinding
import com.example.thebreakingbadapp.di.ViewModelFactory
import com.example.thebreakingbadapp.ui.info.InfoFragmentArgs
import dagger.android.support.DaggerFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }
    private val navArgs by navArgs<DetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.subscribe(navArgs.argCharacter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailFragment.viewLifecycleOwner
            viewModel = this@DetailFragment.viewModel

            // set up toolbar
            toolbar.setupWithNavController(
                findNavController(),
                AppBarConfiguration(setOf(R.id.navigation_list_fragment))
            )
        }.root

        //postpone animation
        postponeEnterTransition(50L, TimeUnit.MILLISECONDS)

        // inflate transition
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.view_shared_enter)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorData.observe(viewLifecycleOwner, EventObserver {
            val title = when (val titleMessage = it.first) {
                is Message.Text -> titleMessage.message
                is Message.Resource -> getString(titleMessage.messageRes)
            }

            val body = when (val bodyMessage = it.second) {
                is Message.Text -> bodyMessage.message
                is Message.Resource -> getString(bodyMessage.messageRes)
            }

            findNavController().navigate(
                R.id.navigation_info_dialog,
                InfoFragmentArgs(
                    title,
                    body,
                    getString(R.string.ok),
                    R.id.navigation_list_fragment
                ).toBundle()
            )
        })
    }
}