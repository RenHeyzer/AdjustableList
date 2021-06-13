package com.example.adjustablelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    EditText etText,etText2;
    Button btnAdd;
    ResModel resModel;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        etText = findViewById(R.id.et_text);
        etText2 = findViewById(R.id.et_text2);
        btnAdd = findViewById(R.id.btn_add);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String title = etText.getText().toString().trim();
                    String description = etText2.getText().toString().trim();
                    if (title.isEmpty()){
                        etText.setError("Input title");
                        return;
                    }
                    if (description.isEmpty()){
                        etText2.setError("Input description");
                        return;
                    }
                        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("description", description);
                        intent.putExtra("position", position);
                        setResult(RESULT_OK, intent);
                        finish();
                if (resModel != null){
                    etText.setText(title);
                    etText2.setText(description);
                }finish();

            }
        });
        Intent intent = getIntent();
        String Title = intent.getStringExtra("title");
        String Description = intent.getStringExtra("description");
        etText.setText(Title);
        etText2.setText(Description);
    }
}