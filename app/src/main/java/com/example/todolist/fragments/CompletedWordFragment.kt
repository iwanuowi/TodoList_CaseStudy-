package com.example.todolist.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.RecycleViewAdapters.TodoRecycleViewAdapter
import com.example.todolist.models.DatabaseInstance
import com.example.todolist.models.TodoDetails

class CompletedWordFragment : Fragment(R.layout.completed_word_fragment) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodoRecycleViewAdapter

    private var fullList = mutableListOf<TodoDetails>()
    private var currentList = mutableListOf<TodoDetails>()

    private var isTitleSort = true
    private var isAscending = true

    private var searchQuery = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DatabaseInstance.getDatabase(requireContext())

        recyclerView = view.findViewById(R.id.recyclerView)
        val emptyLayout = view.findViewById<View>(R.id.emptyLayout)
        val imageButton3 = view.findViewById<View>(R.id.imageButton3)
        val searchInput = view.findViewById<android.widget.EditText>(R.id.searchbar)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = TodoRecycleViewAdapter(emptyList()) { item ->

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
            val isEmpty = currentList.isEmpty()
            emptyLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
            recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        }

        // =========================
        // ROOM DATA (FIXED)
        // =========================
        db.todoDao().getAll().observe(viewLifecycleOwner) { list ->

            fullList = list.filter { it.status.name == "DONE" }.toMutableList()

            applySearchAndSort()
        }

        // =========================
        // SEARCH
        // =========================
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchQuery = s.toString()
                applySearchAndSort()
            }



            override fun afterTextChanged(s: Editable?) {}
        })

        // =========================
        // SORT
        // =========================
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

                isAscending = rbAscOrder.isChecked

                // FIX: properly set sort mode
                isTitleSort = rbTitle.isChecked

                applySearchAndSort()

                dialog.dismiss()
            }

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    // =========================
    // CORE LOGIC (STABLE VERSION)
    // =========================
    private fun applySearchAndSort() {

        var list = fullList.toMutableList()

        // SEARCH
        if (searchQuery.isNotEmpty()) {
            list = list.filter {
                it.title.contains(searchQuery, ignoreCase = true)
            }.toMutableList()
        }

        // SORT
        list = if (isTitleSort) {
            if (isAscending) {
                list.sortedBy { it.title }.toMutableList()
            } else {
                list.sortedByDescending { it.title }.toMutableList()
            }
        } else {
            if (isAscending) {
                list.sortedBy { it.id }.toMutableList()
            } else {
                list.sortedByDescending { it.id }.toMutableList()
            }
        }

        currentList = list
        adapter.updateList(currentList)

        view?.findViewById<View>(R.id.emptyLayout)?.visibility =
            if (currentList.isEmpty()) View.VISIBLE else View.GONE

        view?.findViewById<RecyclerView>(R.id.recyclerView)?.visibility =
            if (currentList.isEmpty()) View.GONE else View.VISIBLE
    }
}