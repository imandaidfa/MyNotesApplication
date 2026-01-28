package com.example.mynotesapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotesapplication.data.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    viewModel: NoteViewModel,
    noteId: Int? = null,
    onBackClick: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var existingNote by remember { mutableStateOf<Note?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(noteId) {
        if (noteId != null && noteId != -1) {
            existingNote = viewModel.getNoteById(noteId)
            existingNote?.let {
                title = it.title
                content = it.content
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId == null || noteId == -1) "Add Note" else "Edit Note") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (title.isNotBlank()) {
                    if (existingNote == null) {
                        viewModel.insertNote(title, content)
                    } else {
                        viewModel.updateNote(existingNote!!.copy(title = title, content = content, timestamp = System.currentTimeMillis()))
                    }
                    onBackClick()
                }
            }) {
                Icon(Icons.Default.Check, contentDescription = "Save Note")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}
