package com.example.firtsthomework;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.TreeMap;

public class FirstPage extends AppCompatActivity {

    String fileName = "member.txt";
    EditText et_text, et_text2;
    Button b_write, b_read, b_viewDB;
    TextView tv_text;

//    TreeMap<String, String> map = new TreeMap<>();
//    for(int i = 0; i < ~~~.lenght(); i++) {
//        map.put((~~~~))
//    }

    //    JSONObject resultObject = json.getJSONObject(0);
    //    for (int j = 0; j < resultObject.length(); j++) {
    //        map.put(resultObject.names().getString(j), resultObject.getString(resultObject.names().getString(j)));
    //    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);

        et_text = (EditText) findViewById(R.id.etEmail);
        et_text2 = (EditText) findViewById(R.id.etPassword);
        b_write = (Button) findViewById(R.id.btnRegist);
        b_read = (Button) findViewById(R.id.btnLogin);
        b_viewDB = (Button) findViewById(R.id.btnViewDB);
        tv_text = (TextView) findViewById(R.id.tv_text);

        // 로그인 버튼
        b_read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 로그인 버튼 누를 시 파일 읽어와서 변수에 저장시키고 있나 일치하는 것 있으면 저장.
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });

        // 회원가입 버튼
        b_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignonPage.class);
                startActivity(intent);
            }
        });

        // DB 보기 버튼
        b_viewDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // DB 보기 버튼 클릭시 현재 member.txt 파일 저장된 것 보여줌.
                tv_text.setText(readFile(fileName));
            }
        });
    }

    public String readFile(String file) {
        String text = "";

        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(FirstPage.this, "Error reading file!", Toast.LENGTH_SHORT).show();
        }

        return text;
    }
}