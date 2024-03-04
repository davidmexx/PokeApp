package com.evaluation.dp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evaluation.dp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etName, etLastName;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();

                if (!name.isEmpty() && !lastName.isEmpty()) {
                    loginAndOpenMainActivity();
                } else {
                    etName.setError("Ingrese su nombre");
                    etLastName.setError("Ingrese su apellido");
                }
            }
        });
    }

    private void loginAndOpenMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("fullName", etName.getText() + " " + etLastName.getText());
        startActivity(intent);
        finish();
    }
}
