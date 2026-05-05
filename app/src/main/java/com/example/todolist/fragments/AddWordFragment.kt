package com.example.todolist.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.models.TodoDetails
import com.example.todolist.models.ToDoStatus
import com.example.todolist.models.TodoManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddWordFragment : Fragment(R.layout.add_word_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleInput = view.findViewById<TextInputEditText>(R.id.inputTitle)
        val detailsInput = view.findViewById<TextInputEditText>(R.id.inputDetails)
        val meaningInput = view.findViewById<TextInputEditText>(R.id.inputMeaning)
        val synonymsInput = view.findViewById<TextInputEditText>(R.id.inputSynonyms)

        val btnAdd = view.findViewById<MaterialButton>(R.id.btnAdd)
        val btnCancel = view.findViewById<MaterialButton>(R.id.btnCancel)

        btnAdd.setOnClickListener {

            val newTodo = TodoDetails(
                id = System.currentTimeMillis().toInt(),
                title = titleInput.text.toString(),
                details = detailsInput.text.toString(),
                meaning = meaningInput.text.toString(),
                synonyms = synonymsInput.text.toString(),
                status = ToDoStatus.NEW
            )

            TodoManager.addTodo(newTodo)

            // 🔥 tell list to refresh
            parentFragmentManager.setFragmentResult("refresh", Bundle())

            // go back
            parentFragmentManager.popBackStack()
        }

        btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}