package com.example.adjustablelist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvRes;
     ResAdapter resAdapter;
    private ResModel resModel;
    private FloatingActionButton fbtn;
//    private EditText etText;
//    private EditText etText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRes = findViewById(R.id.rv_res);
        resAdapter = new ResAdapter(this, MainActivity.this);
        rvRes.setAdapter(resAdapter);
        fbtn = findViewById(R.id.fbtn);
//        etText = findViewById(R.id.et_text);
//        etText2 = findViewById(R.id.et_text2);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        rvRes.setLayoutManager(new LinearLayoutManager(this));
        resAdapter = new ResAdapter(this, MainActivity.this);
        rvRes.setAdapter(resAdapter);
        resAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(int position) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
              intent.putExtra("title", resAdapter.list.get(position).getTitle());
                intent.putExtra("description", resAdapter.list.get(position).getDescription());
                intent.putExtra("pos", position);
                startActivityForResult(intent,10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            if (data != null){
                ResModel resModel = new ResModel(data.getStringExtra("title"),
                        data.getStringExtra("description"));
                resAdapter.addData(resModel);
            }
        }
        if (requestCode == 10 && resultCode == RESULT_OK){
            ResModel resModel = new ResModel(data.getStringExtra("title"), data.getStringExtra("description"));
            int pos = data.getIntExtra("pos", 0);
            resAdapter.updateData(resModel, pos);
        }
    }
}