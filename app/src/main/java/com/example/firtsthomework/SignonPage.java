package com.example.firtsthomework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SignonPage extends Activity {

    EditText et_Email, et_pass, et_name, et_add, et_tel;
    Button b_checkEmail, b_checkPass, b_done, b_back;
    Boolean bool_checkEmail, bool_checkPass, bool_policy;
    RadioButton rb_policyDec, rb_policyAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signon_page);

        bool_checkEmail = Boolean.FALSE;
        bool_checkPass = Boolean.FALSE;
        bool_policy = Boolean.FALSE;

        et_Email = (EditText) findViewById(R.id.etEmail);
        et_pass = (EditText) findViewById(R.id.etPassword);
        et_name = (EditText) findViewById(R.id.etName);
        et_tel = (EditText) findViewById(R.id.etTelephone);
        et_add = (EditText) findViewById(R.id.etAddress);

        b_checkEmail = (Button) findViewById(R.id.btn_CheckEmail);
        b_checkPass = (Button) findViewById(R.id.btn_checkPass);
        b_done = (Button) findViewById(R.id.btnDone);
        b_back = (Button) findViewById(R.id.btnCancel);

        // ischecked ???
        rb_policyDec = (RadioButton) findViewById(R.id.radioPolicy_Dec);
        rb_policyAcc = (RadioButton) findViewById(R.id.radioPolicy_Acc);

        // 회원가입 버튼
        b_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // member.txt에 띄어쓰기를 기준으로 회원아이디와 비밀번호 저장.
                saveFile("member.txt", et_Email.getText().toString()+ " " + et_pass.getText().toString()
                        + " " + et_name.getText().toString()+ " " + et_tel.getText().toString()+ " " + et_add.getText().toString());
            }
        });

        // 뒤로가기 버튼
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FirstPage.class);
                startActivity(intent);
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
            Toast.makeText(SignonPage.this, "Error reading file!", Toast.LENGTH_SHORT).show();
        }

        return text;
    }

    public void saveFile(String file, String text) {
        try {
            text += "\n";
            FileOutputStream fos = openFileOutput(file, Context.MODE_APPEND);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(SignonPage.this, "Saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(SignonPage.this, "Error saving file!", Toast.LENGTH_SHORT).show();
        }
    }
}
