package com.example.firtsthomework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

public class SignonPage extends Activity {

    String fileName = "member.txt";

    EditText et_Email, et_pass, et_name, et_add, et_tel;
    TextView tv_validEmail, tv_validPass;
    Button b_done, b_back;
    Boolean bool_checkEmail, bool_checkPass, bool_policy;
    RadioGroup rg_policy;

    String [] splitMember;
    String [] memberIDs = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signon_page);

        bool_checkEmail = false;
        bool_checkPass = false;
        bool_policy = false;

        et_Email = findViewById(R.id.etEmail);
        et_pass = findViewById(R.id.etPassword);
        et_name = findViewById(R.id.etName);
        et_tel = findViewById(R.id.etTelephone);
        et_add = findViewById(R.id.etAddress);

        tv_validEmail = findViewById(R.id.validEmail);
        tv_validPass = findViewById(R.id.validPass);

        b_done = findViewById(R.id.btnDone);
        b_back = findViewById(R.id.btnCancel);

        rg_policy = findViewById(R.id.radioPolicy);

        // 아이디 입력이 변경됨에 따라 2자 이상과 중복 체크
        et_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!Pattern.matches("^().{2,20}$", et_Email.getText().toString())){
                    tv_validEmail.setText("2자 이상 입력");
                    bool_checkEmail = false;
                } else {
                    readFile(fileName);
                }
            }
        });

        // 비밀번호 입력이 변경됨에 따라 유효성 체크
        et_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // 비밀번호 유효성
                if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", et_pass.getText().toString()))
                {
                    tv_validPass.setText("부적합 비밀번호");
                    bool_checkPass = false;
                } else {
                    tv_validPass.setText("유효한 비밀번호");
                    bool_checkPass = true;
                }
            }
        });

        // 회원가입 버튼
        b_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // member.txt에 띄어쓰기를 기준으로 회원아이디와 비밀번호, 이름, 전화번호, 주소 저장.
                saveFile("member.txt", et_Email.getText().toString()+ " " + et_pass.getText().toString()
                        + " " + et_name.getText().toString()+ " " + et_tel.getText().toString()+ " " + et_add.getText().toString());
            }
        });

        // 뒤로가기 버튼
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // 파일 읽으며 중복 체크 함수.
    public void readFile(String file) {
        String text = "";

        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
            // 읽은 텍스트 파일에서 map에 key로 id값을, value로 비밀번호를 넣는다.
            splitMember = text.split("\n");
            for(int i = 0; i < splitMember.length; i++) {
                memberIDs[i] = splitMember[i].split(" ")[0];
                if( memberIDs[i].equals(et_Email.getText().toString())){
                    bool_checkEmail = false;
                    tv_validEmail.setText("중복된 아이디");
                    break;
                }
                else{
                    bool_checkEmail = true;
                    tv_validEmail.setText("가능한 아이디");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 파일읽기는 성공하였으나 멤버가 아무도 없을 때를 위해 가입 가능하도록 설정.
            bool_checkEmail = true;
            tv_validEmail.setText("가능한 아이디");
        }
    }

    // 회원가입하며 파일 저장 함수.
    public void saveFile(String file, String text) {
        // 간략 약관 동의 여부 여기서 따져줌.
        int id = rg_policy.getCheckedRadioButtonId();
        RadioButton rb = findViewById(id);
        if (bool_checkEmail == true && bool_checkPass == true && rb.getText().toString().equals("Accept")) {
            try {
                text += "\n";
                FileOutputStream fos = openFileOutput(file, Context.MODE_APPEND);
                fos.write(text.getBytes());
                fos.close();
                Toast.makeText(SignonPage.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(SignonPage.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SignonPage.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
