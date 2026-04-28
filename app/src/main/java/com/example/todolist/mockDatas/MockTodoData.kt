package com.example.todolist.mockDatas

import com.example.todolist.models.ToDoStatus
import com.example.todolist.models.TodoDetails

object MockTodoData {

    fun populateData(): List<TodoDetails> = listOf(

        TodoDetails(
            id = 2,
            title = "Task 2",
            meaning = "Complete assigned homework or work task",
            synonyms = "assignment, duty, job",
            details = "Details for task 2",
            status = ToDoStatus.NEW
        ),

        TodoDetails(
            id = 3,
            title = "Task 3",
            meaning = "Fix bugs and improve code quality",
            synonyms = "debugging, troubleshooting, fixing",
            details = "Details for task 3",
            status = ToDoStatus.NEW
        ),

        TodoDetails(
            id = 4,
            title = "Task 4",
            meaning = "Study and revise important topics",
            synonyms = "revision, learning, review",
            details = "Details for task 4",
            status = ToDoStatus.NEW
        ),

        TodoDetails(
            id = 5,
            title = "Task 5",
            meaning = "Clean and organize workspace",
            synonyms = "tidy, arrange, organize",
            details = "Details for task 5",
            status = ToDoStatus.NEW
        ),

        TodoDetails(
            id = 6,
            title = "Task 6",
            meaning = "Prepare documents and reports",
            synonyms = "paperwork, documentation, files",
            details = "Details for task 6",
            status = ToDoStatus.NEW
        ),

        TodoDetails(
            id = 7,
            title = "Task 7",
            meaning = "Attend meetings and discussions",
            synonyms = "conference, session, meeting",
            details = "Details for task 7",
            status = ToDoStatus.NEW
        ),

        TodoDetails(
            id = 8,
            title = "Task 8",
            meaning = "Finish and submit completed work",
            synonyms = "submit, deliver, hand in",
            details = "Details for task 8",
            status = ToDoStatus.DONE
        ),

        TodoDetails(
            id = 9,
            title = "Task 9",
            meaning = "Plan upcoming activities",
            synonyms = "schedule, organize, plan",
            details = "Details for task 9",
            status = ToDoStatus.DONE
        ),

        TodoDetails(
            id = 10,
            title = "Task 10",
            meaning = "Review past work and improve",
            synonyms = "analyze, evaluate, improve",
            details = "Details for task 10",
            status = ToDoStatus.DONE
        )
    )
}