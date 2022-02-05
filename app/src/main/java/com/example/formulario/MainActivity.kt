package com.example.formulario

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.formulario.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_send && validFields()) {

            val name: String = findViewById<TextInputEditText>(R.id.etName).text.toString().trim()
            val surname = binding.etLastName.text.toString().trim()
            Toast.makeText(this, "$name $surname", Toast.LENGTH_SHORT).show()

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.data_collect))
            builder.setMessage("$name $surname")
            builder.setPositiveButton(getString(R.string.clean_data), { dialogInterface, i ->
                Toast.makeText(this, getString(R.string.positive_button), Toast.LENGTH_SHORT).show()
            })
            builder.setNegativeButton(getString(R.string.cancel), null)

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validFields(): Boolean {
        var isValid = true

        if(binding.etHeight.text.isNullOrEmpty()) {
            binding.tilHeight.run {
                error = getString(R.string.mandatory)
                requestFocus()
            }
            isValid = false
        } else if(binding.etHeight.text.toString().toInt() < 50){
            binding.tilHeight.run {
                error = getString(R.string.height_error)
                requestFocus()
            }
            isValid = false
        }else {
            binding.tilHeight.error = null
        }
        if(binding.etName.text.isNullOrEmpty()) {
            binding.tilName.run {
                error = getString(R.string.mandatory)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilName.error = null
        }
        if(binding.etLastName.text.isNullOrEmpty()) {
            binding.tilLastName.run {
                error = getString(R.string.mandatory)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilLastName.error = null
        }

        return isValid
    }
}