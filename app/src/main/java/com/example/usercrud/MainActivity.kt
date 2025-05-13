package com.example.usercrud

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var idEdit: EditText
    lateinit var nameEdit: EditText
    lateinit var emailEdit: EditText
    lateinit var passwordEdit: EditText
    lateinit var btnCreate: Button
    lateinit var btnRead: Button
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button

    // ⚠️ Change to your real IP if using real device
    private val baseUrl = "http://10.0.2.2/usercrud/"  // emulator = 10.0.2.2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idEdit = findViewById(R.id.editTextId)
        nameEdit = findViewById(R.id.editTextName)
        emailEdit = findViewById(R.id.editTextEmail)
        passwordEdit = findViewById(R.id.editTextPassword)
        btnCreate = findViewById(R.id.btnCreate)
        btnRead = findViewById(R.id.btnRead)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

        btnCreate.setOnClickListener { createUser() }
        btnRead.setOnClickListener { readUsers() }
        btnUpdate.setOnClickListener { updateUser() }
        btnDelete.setOnClickListener { deleteUser() }
    }

    private fun createUser() {
        val name = nameEdit.text.toString()
        val email = emailEdit.text.toString()
        val password = passwordEdit.text.toString()

        val request = object : StringRequest(Request.Method.POST, baseUrl + "create.php",
            { response -> showToast(response) },
            { error -> showToast(error.toString()) }) {
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf("name" to name, "email" to email, "password" to password)
            }
        }

        Volley.newRequestQueue(this).add(request)
    }

    private fun readUsers() {
        val request = StringRequest(Request.Method.GET, baseUrl + "read.php",
            { response -> showToast(response) },
            { error -> showToast(error.toString()) })

        Volley.newRequestQueue(this).add(request)
    }

    private fun updateUser() {
        val id = idEdit.text.toString()
        val name = nameEdit.text.toString()
        val email = emailEdit.text.toString()
        val password = passwordEdit.text.toString()

        val request = object : StringRequest(Request.Method.POST, baseUrl + "update.php",
            { response -> showToast(response) },
            { error -> showToast(error.toString()) }) {
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf("id" to id, "name" to name, "email" to email, "password" to password)
            }
        }

        Volley.newRequestQueue(this).add(request)
    }

    private fun deleteUser() {
        val id = idEdit.text.toString()

        val request = object : StringRequest(Request.Method.POST, baseUrl + "delete.php",
            { response -> showToast(response) },
            { error -> showToast(error.toString()) }) {
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf("id" to id)
            }
        }

        Volley.newRequestQueue(this).add(request)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
