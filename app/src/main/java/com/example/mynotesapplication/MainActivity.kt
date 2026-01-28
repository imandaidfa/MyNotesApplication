package com.example.mynotesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynotesapplication.data.NoteDatabase
import com.example.mynotesapplication.data.NoteRepository
import com.example.mynotesapplication.ui.*
import com.example.mynotesapplication.ui.theme.MyNotesApplicationTheme

class MainActivity : ComponentActivity() {
    
    private val database by lazy { NoteDatabase.getDatabase(this) }
    private val repository by lazy { NoteRepository(database.noteDao) }
    private val viewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotesApplicationTheme {
                NotesApp(viewModel)
            }
        }
    }
}

@Composable
fun NotesApp(viewModel: NoteViewModel) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "note_list") {
        composable("note_list") {
            NoteListScreen(
                viewModel = viewModel,
                onAddNoteClick = { navController.navigate("add_edit_note/-1") },
                onNoteClick = { note ->
                    navController.navigate("add_edit_note/${note.id}")
                }
            )
        }
        composable(
            route = "add_edit_note/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            AddEditNoteScreen(
                viewModel = viewModel,
                noteId = noteId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}