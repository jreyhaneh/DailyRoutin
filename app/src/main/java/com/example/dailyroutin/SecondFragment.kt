package com.example.dailyroutin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.findNavController
import com.example.dailyroutin.database.DatabaseHelper
import com.example.dailyroutin.database.DatabaseManager
import com.example.dailyroutin.databinding.FragmentFirstBinding
import com.example.dailyroutin.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val database = DatabaseManager(requireContext())
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)


        binding.buttonAdd.setOnClickListener {

            val todoText = binding.editTextTodo.text.toString()
            val detailText = binding.editTextDetail.text.toString()
            binding.editTextTodo.text?.clear()
            database.open()
            val row: Long = database.insert(todoText,detailText,false )
            database.close()

            requireActivity().onBackPressedDispatcher.onBackPressed()

        }


        return binding.root
    }



}