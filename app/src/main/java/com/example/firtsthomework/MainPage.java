package com.example.firtsthomework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends Activity {
    EditText edtNum1, edtNum2;
    Button[] buttonNum = new Button[10];
    Button btnAdd, btnMinus, btnMul, btnDiv, btnClear;
    TextView txtResult;
    Integer[] btnID = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    String num1, num2;
    Integer result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        setTitle("간단 계산기");

        txtResult = findViewById(R.id.txtV);
        edtNum1 = findViewById(R.id.edt1);
        edtNum2 = findViewById(R.id.edt2);
        btnAdd = findViewById(R.id.btnplus);
        btnMinus = findViewById(R.id.btnminus);
        btnDiv = findViewById(R.id.btndivide);
        btnMul = findViewById(R.id.btngobhagi);
        btnClear = findViewById(R.id.btnclear);

        // 숫자 버튼 초기화
        for (int i = 0; i < buttonNum.length; i++) {
            buttonNum[i] = findViewById(btnID[i]);
        }

        // 클릭 시 이벤트
        for (int i = 0; i < buttonNum.length; i++) {
            final int index = i;
            buttonNum[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edtNum1.isFocused()) {
                        num1 = edtNum1.getText().toString() + buttonNum[index].getText().toString();
                        edtNum1.setText(num1);
                    } else if (edtNum2.isFocused()) {
                        num2 = edtNum2.getText().toString() + buttonNum[index].getText().toString();
                        edtNum2.setText(num2);
                    } else {
                        Toast.makeText(MainPage.this, "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        // 더하기 버튼
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNum1.getText().toString().length() > 0 && edtNum2.getText().toString().length() > 0) {
                    num1 = edtNum1.getText().toString();
                    num2 = edtNum2.getText().toString();
                    result = Integer.parseInt(num1) + Integer.parseInt(num2);
                    txtResult.setText("계산 결과 : " + result.toString());
                } else {
                    Toast.makeText(MainPage.this, "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 빼기 버튼
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNum1.getText().toString().length() > 0 && edtNum2.getText().toString().length() > 0) {
                    num1 = edtNum1.getText().toString();
                    num2 = edtNum2.getText().toString();
                    result = Integer.parseInt(num1) - Integer.parseInt(num2);
                    txtResult.setText("계산 결과 : " + result.toString());
                } else {
                    Toast.makeText(MainPage.this, "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 나누기 버튼
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNum1.getText().toString().length() > 0 && edtNum2.getText().toString().length() > 0) {
                    num1 = edtNum1.getText().toString();
                    num2 = edtNum2.getText().toString();
                    result = Integer.parseInt(num1) / Integer.parseInt(num2);
                    txtResult.setText("계산 결과 : " + result.toString());
                } else {
                    Toast.makeText(MainPage.this, "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 곱하기 버튼
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNum1.getText().toString().length() > 0 && edtNum2.getText().toString().length() > 0) {
                    num1 = edtNum1.getText().toString();
                    num2 = edtNum2.getText().toString();
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                    txtResult.setText("계산 결과 : " + result.toString());
                } else {
                    Toast.makeText(MainPage.this, "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 초기화 버튼
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNum1.setText("");
                edtNum2.setText("");
                txtResult.setText("계산 결과 : ");
            }
        });
    }
}