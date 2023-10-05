package com.example.dailyroutin

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.dailyroutin.database.DatabaseHelper
import com.example.dailyroutin.database.DatabaseManager
import com.example.dailyroutin.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    var id: String? = null
    lateinit var database: DatabaseManager


    private lateinit var binding: FragmentFirstBinding
    lateinit var todoAdapter: RoutinAdapter

    private val routineList = mutableListOf<Todo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        database = DatabaseManager(requireContext())

        todoAdapter = RoutinAdapter(routineList)
        binding.recyclerView.adapter = todoAdapter

        binding.buttonAdd.setOnClickListener {

            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        
        todoAdapter.setOnClick(object : OnClickListenerTodo {
            override fun onClick(todo: Todo) {
                val action = FirstFragmentDirections.actionFirstFragmentToBottomSheetFragment(todo)
                findNavController().navigate(action)
            }
        })


//        setBottomSheetListener()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchFromDatabase()
    }

    private fun fetchFromDatabase() {
        database.open()
        routineList.clear()
        val cursor: Cursor? = database.fetch()
        if (cursor!!.moveToFirst()) {
            do {
                val indexId = cursor.getColumnIndex(DatabaseHelper.TODO_ID)
                val id = cursor.getString(if (indexId >= 0) indexId else 0)
                val indexRoutine = cursor.getColumnIndex(DatabaseHelper.TODO)
                val routine =
                    cursor.getString(if (indexRoutine >= 0) indexRoutine else 0)
                val indexIscheck = cursor.getColumnIndex(DatabaseHelper.IS_CHECKED)
                val isChecked =
                    cursor.getString(if (indexIscheck >= 0) indexIscheck else 0)

                val indexDetail = cursor.getColumnIndex(DatabaseHelper.DETAIL)
                val detail = cursor.getString(if (indexDetail >= 0) indexDetail else 0)

                routineList.add(Todo(id.toLong(), routine, detail, isChecked.toBoolean()))

            } while (cursor.moveToNext())

        }
        database.close()
    }

}
