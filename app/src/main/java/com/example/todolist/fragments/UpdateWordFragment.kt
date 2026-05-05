package com.example.todolist.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.models.ToDoStatus
import com.example.todolist.models.TodoDetails
import com.example.todolist.models.TodoManager
import com.google.android.material.button.MaterialButton

class UpdateWordFragment : Fragment(R.layout.update_word_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnUpdate = view.findViewById<MaterialButton>(R.id.btnUpdate)
        val btnCancel = view.findViewById<MaterialButton>(R.id.btnCancel)

        val id = arguments?.getInt("id")
        val title = arguments?.getString("title")
        val details = arguments?.getString("details")
        val meaning = arguments?.getString("meaning")
        val synonyms = arguments?.getString("synonyms")

        val titleInput = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.inputTitle)
        val detailsInput = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.inputDetails)
        val meaningInput = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.inputMeaning)
        val synonymsInput = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.inputSynonyms)

        titleInput.setText(title)
        detailsInput.setText(details)
        meaningInput.setText(meaning)
        synonymsInput.setText(synonyms)

        btnUpdate.setOnClickListener {

            if (id != null) {

                val updatedTodo = TodoDetails(
                    id = id,
                    title = titleInput.text.toString(),
                    details = detailsInput.text.toString(),
                    meaning = meaningInput.text.toString(),
                    synonyms = synonymsInput.text.toString(),
                    status = ToDoStatus.NEW
                )

                TodoManager.updateTodo(updatedTodo)

                // 🔥 tell list to refresh
                parentFragmentManager.setFragmentResult("refresh", Bundle())
            }

            // 🔥 go back to MAIN screen (not just previous fragment)
            parentFragmentManager.popBackStack()
        }

        btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}