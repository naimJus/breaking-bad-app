package com.example.thebreakingbadapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.thebreakingbadapp.R
import com.example.thebreakingbadapp.data.EventObserver
import com.example.thebreakingbadapp.data.Message
import com.example.thebreakingbadapp.databinding.FragmentListBinding
import com.example.thebreakingbadapp.di.ViewModelFactory
import com.example.thebreakingbadapp.ui.info.InfoFragmentArgs
import com.example.thebreakingbadapp.ui.list.ListFragmentDirections.Companion.toDetailFragment
import dagger.android.support.DaggerFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ListViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.subscribe()
        postponeEnterTransition(50L, TimeUnit.MILLISECONDS)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentListBinding.inflate(inflater, container, false).apply {
        viewModel = this@ListFragment.viewModel
        lifecycleOwner = this@ListFragment.viewLifecycleOwner
    }.root

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

        viewModel.navigateToDetailData.observe(viewLifecycleOwner, EventObserver {
            //create shared element transition extra
            val fragmentNavigatorExtras = FragmentNavigatorExtras(it.first to it.second.name)
            // navigate to details fragment
            findNavController().navigate(toDetailFragment(it.second), fragmentNavigatorExtras)
        })
    }
}