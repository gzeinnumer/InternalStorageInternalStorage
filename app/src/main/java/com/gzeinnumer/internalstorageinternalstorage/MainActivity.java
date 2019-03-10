package com.gzeinnumer.internalstorageinternalstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.read)
    TextView read;
    @BindView(R.id.editNama)
    EditText editNama;

    String fileName = "myFile";
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                saveData();
                break;
            case R.id.btn2:
                readData();
                break;
        }
    }

    private void saveData() {
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            name = editNama.getText().toString();
            fos.write(name.getBytes());
            fos.close();
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() {
        try{
            FileInputStream fin = openFileInput(fileName);
            InputStreamReader inputStream = new InputStreamReader(fin);
            BufferedReader buffered = new BufferedReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line=buffered.readLine())!=null){
                stringBuilder.append(line);
            }
            fin.close();
            inputStream.close();
            read.setText("Name= "+stringBuilder.toString());
            Toast.makeText(this, "Readed", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
