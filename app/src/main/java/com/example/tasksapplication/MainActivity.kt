package com.example.tasksapplication
import android.view.Menu
import android.view.MenuItem
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tasksapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            showTitleDialog()
        }
    }

    private fun showTitleDialog() {
        val popupTitulo = AlertDialog.Builder(this)
        val textTitulo = EditText(this)

        popupTitulo.setTitle("Título")
        popupTitulo.setView(textTitulo)
        popupTitulo.setPositiveButton("Ok") { dialog, which ->
            val titulo = textTitulo.text.toString()
            if (titulo.isNotEmpty()) {
                showDescriptionDialog(titulo)
            } else {
                Toast.makeText(this, "Por favor, insira um título", Toast.LENGTH_SHORT).show()
            }
        }
        popupTitulo.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }
        popupTitulo.show()
    }

    private fun showDescriptionDialog(titulo: String) {
        val popupDescricao = AlertDialog.Builder(this)
        val textDescricao = EditText(this)

        popupDescricao.setTitle("Descrição")
        popupDescricao.setView(textDescricao)
        popupDescricao.setPositiveButton("Ok") { dialog, which ->
            val descricao = textDescricao.text.toString()
            if (descricao.isNotEmpty()) {
                createTask(titulo, descricao)
            } else {
                Toast.makeText(this, "Por favor, insira uma descrição", Toast.LENGTH_SHORT).show()
            }
        }
        popupDescricao.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }
        popupDescricao.show()
    }

    private fun createTask(titulo: String, descricao: String) {
        val task = Task(titulo, descricao)
        tasks.add(task)

        Snackbar.make(binding.fab, task.toString(), Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
