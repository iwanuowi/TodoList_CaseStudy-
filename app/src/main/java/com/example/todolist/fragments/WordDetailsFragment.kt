package com.example.todolist.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.models.TodoManager
import com.google.android.material.button.MaterialButton

class WordDetailsFragment : Fragment(R.layout.word_details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id")
        val todo = TodoManager.getAllTodos().find{it.id == id}

        val btnUpdate = view.findViewById<MaterialButton>(R.id.btnUpdate)
        val btnDone = view.findViewById<MaterialButton>(R.id.btnDone)
        val btnDelete = view.findViewById<MaterialButton>(R.id.btnDelete)

        val titleText = view.findViewById<TextView>(R.id.titleText)
        val detailsText = view.findViewById<TextView>(R.id.detailsText)
        val meaningText = view.findViewById<TextView>(R.id.meaningText)
        val synonymsText = view.findViewById<TextView>(R.id.synonymText)

        // UI bind
        titleText.text = todo?.title ?: "No title"
        detailsText.text = todo?.details ?: "No details"
        meaningText.text = todo?.meaning ?: "No meaning available"
        synonymsText.text = todo?.synonyms ?: "No synonyms available"

        // 🔥 DONE
        btnDone.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_done, null)

            val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            val yesBtn = dialogView.findViewById<MaterialButton>(R.id.btnYes)
            val noBtn = dialogView.findViewById<MaterialButton>(R.id.btnNo)

            yesBtn.setOnClickListener {
                id?.let {
                    TodoManager.markAsDone(it)
                }

                parentFragmentManager.setFragmentResult("refresh", Bundle())
                dialog.dismiss()
                parentFragmentManager.popBackStack()
            }

            noBtn.setOnClickListener {
                dialog.dismiss()
                parentFragmentManager.popBackStack()
            }

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        // 🔥 DELETE
        btnDelete.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_delete, null)

            val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            val btnCancel = dialogView.findViewById<MaterialButton>(R.id.btnCancel)
            val btnDeleteConfirm = dialogView.findViewById<MaterialButton>(R.id.btnDeletes)

            btnCancel.setOnClickListener {
                dialog.dismiss()
            }

            btnDeleteConfirm.setOnClickListener {
                id?.let {
                    TodoManager.deleteTodo(it)
                }

                parentFragmentManager.setFragmentResult("refresh", Bundle())
                dialog.dismiss()
                parentFragmentManager.popBackStack()
            }

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        btnUpdate.setOnClickListener {

            todo?.let {

                val bundle = Bundle().apply {
                    putInt("id", it.id)
                    putString("title", it.title)
                    putString("details", it.details)
                    putString("meaning", it.meaning)
                    putString("synonyms", it.synonyms)
                }

                val fragment = UpdateWordFragment().apply {
                    arguments = bundle
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }


//        println("DEBUG ID = $id")
    }

}