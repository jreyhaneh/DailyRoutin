package com.example.dailyroutin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dailyroutin.database.DatabaseManager
import com.example.dailyroutin.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    lateinit var database: DatabaseManager

    val args: BottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = DatabaseManager(requireContext())
        binding = FragmentBottomSheetBinding.inflate(layoutInflater, container, false)

        val argTodo = args.passedTodo
        binding.etUpdate.setText(argTodo.title)

        val argId = argTodo.id
        val argChecked = argTodo.isChecked


        binding.butSave.setOnClickListener {
            val editeText = binding.etUpdate.text.toString()
            database.open()
            database.update(argId!!.toLong(), editeText, argChecked)
            database.close()

//            setFragmentResult(
//                "edite", bundleOf(
//                    Pair("ardTitle", editText)
////                "isChecked" to true
//                )
//            )

//            requireActivity().onBackPressedDispatcher.onBackPressed()
            findNavController().navigate(R.id.action_bottomSheetFragment_to_firstFragment)
        }

        binding.butDelete.setOnClickListener {
            database.open()
            database.delete(argId!!.toLong())
            database.close()

            findNavController().navigate(R.id.action_bottomSheetFragment_to_firstFragment)
        }

        return binding.root
    }


}