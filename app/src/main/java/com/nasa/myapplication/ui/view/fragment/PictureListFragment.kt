package com.nasa.myapplication.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nasa.myapplication.databinding.FragmentPictureListBinding
import com.nasa.myapplication.ui.adapter.PictureRvAdapter
import com.nasa.myapplication.ui.view.viewmodel.SharedViewModel

class PictureListFragment : Fragment() {
    private lateinit var binding : FragmentPictureListBinding
    private lateinit var viewModel : SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPictureListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        initObservers()
    }

    private fun initObservers() {
        viewModel.pictureList.observe(viewLifecycleOwner) { pictureList ->
            val adapter = PictureRvAdapter(pictureList, requireContext())
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerView.adapter = adapter
        }
    }
}