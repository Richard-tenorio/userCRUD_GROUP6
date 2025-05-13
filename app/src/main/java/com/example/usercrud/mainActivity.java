package com.example.usercrud;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import java.util.HashMap;
import java.util.Map;

public class mainActivity extends AppCompatActivity {

    EditText editTextId, editTextName, editTextEmail, editTextPassword;
    Button btnCreate, btnRead, btnUpdate, btnDelete;

    String baseUrl = "http://10.0.2.2/usercrud/"; // ← CHANGE THIS TO YOUR IP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnCreate = findViewById(R.id.btnCreate);
        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Button listeners
        btnCreate.setOnClickListener(v -> createUser());
        btnRead.setOnClickListener(v -> readUsers());
        btnUpdate.setOnClickListener(v -> updateUser());
        btnDelete.setOnClickListener(v -> deleteUser());
    }

    // Create
    private void createUser() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, baseUrl + "create.php",
                response -> Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    // Read
    private void readUsers() {
        StringRequest request = new StringRequest(Request.Method.GET, baseUrl + "read.php",
                response -> {
                    // Just show raw response (you can customize this)
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                },
                error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(this).add(request);
    }

    // Update
    private void updateUser() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, baseUrl + "update.php",
                response -> Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    // Delete
    private void deleteUser() {
        String id = editTextId.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, baseUrl + "delete.php",
                response -> Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}
