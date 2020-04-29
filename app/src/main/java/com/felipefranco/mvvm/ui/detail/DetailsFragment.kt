package com.felipefranco.mvvm.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.felipefranco.mvvm.R
import com.felipefranco.mvvm.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )

        val args = arguments?.let { mbid ->
            DetailsFragmentArgs.fromBundle(mbid)
        }

        viewModel = ViewModelProviders.of(
            this, DetailsViewModel.Factory(
                requireActivity().application, args!!.mbid
            )
        ).get(DetailsViewModel::class.java)

        viewModel.artist.observe(viewLifecycleOwner, Observer {

            binding.name.text = it.name
            binding.summary.text = it.summary
        })

        return binding.root
    }
}
