package com.example.todolist.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.RecycleViewAdapters.TodoRecycleViewAdapter
import com.example.todolist.models.TodoDetails
import com.example.todolist.models.TodoManager

class NewTodoFragment : Fragment(R.layout.new_todo_fragment) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodoRecycleViewAdapter
    private var currentList = mutableListOf<TodoDetails>()
    private fun updateUI() {
        if (currentList.isEmpty()) {
            recyclerView.visibility = View.GONE
            view?.findViewById<View>(R.id.emptyLayout)?.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            view?.findViewById<View>(R.id.emptyLayout)?.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        val emptyLayout = view.findViewById<View>(R.id.emptyLayout)
        val imageButton3 = view.findViewById<View>(R.id.imageButton3)
        val btnAddWord = view.findViewById<View>(R.id.btnAddWord)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        parentFragmentManager.setFragmentResultListener("refresh", this) { _, _ ->

            currentList = TodoManager.getNewTodos().toMutableList()
            adapter.updateList(currentList)
            updateUI()
        }

        // 🔹 Load NEW todos from TodoManager
        currentList = TodoManager.getNewTodos().toMutableList()

        adapter = TodoRecycleViewAdapter(currentList) { item ->

            val bundle = Bundle().apply {
                putInt("id", item.id)
            }

            val fragment = WordDetailsFragment().apply {
                arguments = bundle
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter

        fun updateUI() {
            if (currentList.isEmpty()) {
                emptyLayout.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                emptyLayout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        updateUI()

        // 🔹 Sorting dialog
        imageButton3.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_sort, null)

            val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            val rbAscOrder = dialogView.findViewById<android.widget.RadioButton>(R.id.rbAscOrder)
            val rbDescOrder = dialogView.findViewById<android.widget.RadioButton>(R.id.rbDescOrder)
            val rbTitle = dialogView.findViewById<android.widget.RadioButton>(R.id.rbTitle)
            val btnDone = dialogView.findViewById<android.widget.Button>(R.id.btnDone)

            btnDone.setOnClickListener {

                val isAscending = rbAscOrder.isChecked
                val isTitle = rbTitle.isChecked

                currentList = if (isTitle) {
                    if (isAscending) {
                        currentList.sortedBy { it.title }.toMutableList()
                    } else {
                        currentList.sortedByDescending { it.title }.toMutableList()
                    }
                } else {
                    if (isAscending) {
                        currentList.sortedBy { it.id }.toMutableList()
                    } else {
                        currentList.sortedByDescending { it.id }.toMutableList()
                    }
                }

                adapter.updateList(currentList)
                updateUI()
                dialog.dismiss()
            }

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        // 🔹 Navigate to Add screen
        btnAddWord.setOnClickListener {
            val fragment = AddWordFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    //Refresh when returning from Add/Edit screen
//    override fun onResume() {
//        super.onResume()
//
//        currentList = TodoManager.getNewTodos().toMutableList()
//
//        if (::adapter.isInitialized) {
//            adapter.updateList(currentList)
//        }
//
//        updateUI()
//    }
}