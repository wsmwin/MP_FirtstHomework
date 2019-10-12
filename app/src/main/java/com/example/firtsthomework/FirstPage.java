package com.example.firtsthomework;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.util.HashMap;

public class FirstPage extends AppCompatActivity {

    String fileName = "member.txt";
    EditText et_text, et_text2;
    Button b_write, b_read, b_viewDB;
    TextView tv_text;

    String tempMember = "";
    String [] splitMember;
    HashMap<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);

        et_text = findViewById(R.id.etEmail);
        et_text2 = findViewById(R.id.etPassword);
        b_write = findViewById(R.id.btnRegist);
        b_read = findViewById(R.id.btnLogin);
        b_viewDB = findViewById(R.id.btnViewDB);
        tv_text = findViewById(R.id.tv_text);

        // 로그인 버튼
        b_read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 파일 읽어온 것에서 변수로 담아서 처리해줄 부분
                tempMember = readFile(fileName);
                // 읽은 텍스트 파일에서 map에 key로 id값을, value로 비밀번호를 넣는다.
                splitMember = tempMember.split("\n");
                for(int i = 0; i < splitMember.length; i++) {
                    map.put(splitMember[i].split(" ")[0], splitMember[i].split(" ")[1]);
                }
                // 아이디와 비밀번호 일치하는지 확인 후 메인 페이지로 넘어가도록 한다. 아니라면 실패 Toast
                try {
                    if ( map.get(et_text.getText().toString()).equals(et_text2.getText().toString()) ) {
                        Toast.makeText(FirstPage.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainPage.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(FirstPage.this,"로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    Toast.makeText(FirstPage.this,"로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
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
                Toast.makeText(FirstPage.this, "member.txt 파일을 불러왔습니다.", Toast.LENGTH_SHORT).show();
                splitMember = tempMember.split("\n");
//                 DB 보기 버튼 클릭시 현재 member.txt 파일 저장된 것 보여줌.
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
            Toast.makeText(FirstPage.this, "MEMBER가 아직 없거나 읽을 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

        return text;
    }
}