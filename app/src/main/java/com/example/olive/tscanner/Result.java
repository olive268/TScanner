package com.example.olive.tscanner;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Result extends AppCompatActivity {
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Scanning Result");
        res = getIntent().getStringExtra("Result");
        TextView textView = findViewById(R.id.tv);
        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setText(res);
    }

    public void saveResult(View view) {


        String title="scanned_doc.txt";

        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(path + File.separator + title);
        FileOutputStream fos = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fos=new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Saved to "+path+title, Toast.LENGTH_LONG).show();
        finish();

    }



}
