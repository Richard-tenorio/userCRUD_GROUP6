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

    String baseUrl = "http://10.0.2.2/usercrud/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnCreate = findViewById(R.id.btnCreate);
        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(v -> createUser());
        btnRead.setOnClickListener(v -> readUsers());
        btnUpdate.setOnClickListener(v -> updateUser());
        btnDelete.setOnClickListener(v -> deleteUser());
    }

    private void createUser() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, baseUrl + "create.php",
                response -> showToast(response),
                error -> showToast("Error: " + error.getMessage())
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

    private void readUsers() {
        StringRequest request = new StringRequest(Request.Method.GET, baseUrl + "read.php",
                response -> showToast(response),
                error -> showToast("Error: " + error.getMessage()));
        Volley.newRequestQueue(this).add(request);
    }

    private void updateUser() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, baseUrl + "update.php",
                response -> showToast(response),
                error -> showToast("Error: " + error.getMessage())
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

    private void deleteUser() {
        String id = editTextId.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, baseUrl + "delete.php",
                response -> showToast(response),
                error -> showToast("Error: " + error.getMessage())
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
