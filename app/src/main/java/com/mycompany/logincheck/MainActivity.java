package com.mycompany.logincheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    //私有成员用户名，密码，登录按钮，密码是否可见
    private EditText et_account;
    private EditText et_password;
    private Button btn_login;
    private ImageView iv_see_password;
    //判断存用户名的字符串是否为手机号
    public static boolean isMobile(String str) {
        Pattern p ;
        Matcher m ;
        boolean b ;
        p = Pattern.compile("^[1][3,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    //密码是否可见
    public void ifSee(){
        if (iv_see_password.isSelected()) {
            iv_see_password.setSelected(false);
            //密码不可见
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        else {
            iv_see_password.setSelected(true);
            //密码可见
            et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }
    //登录检查
    public boolean loginCheck(){
        String username, password ;
        username = et_account.getText().toString();
        password = et_password.getText().toString();
        if(username.isEmpty()) {
            Toast tot = Toast.makeText(MainActivity.this, "用户名为空", Toast.LENGTH_LONG);
            tot.show();
            return false;
        }
        else if(password.isEmpty()){
            Toast tot = Toast.makeText(MainActivity.this, "密码为空", Toast.LENGTH_LONG);
            tot.show();
            return false;
        }
        else if(username.length()!=11) {
            Toast tot = Toast.makeText(MainActivity.this, "手机号位数不是11位", Toast.LENGTH_LONG);
            tot.show();
            return false;
        }
        else if (!isMobile(username)) {
            Toast tot= Toast.makeText(MainActivity.this,"请输入正确的手机号", Toast.LENGTH_SHORT);
            tot.show();
            return false;
        }
        else if (username.equals("13770595711") && password.equals("Legend0907")) {
            Toast tot = Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG);
            tot.show();
            return true;
        }
        else {
            Toast tot = Toast.makeText(MainActivity.this, "账号或密码不正确", Toast.LENGTH_LONG);
            tot.show();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.btn_login);
        et_account = findViewById(R.id.et_account);
        et_password =  findViewById(R.id.et_password);
        iv_see_password = findViewById(R.id.iv_see_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginCheck();
                if(loginCheck())
                {
                    Intent it = new Intent(MainActivity.this,AfterActivity.class);
                    startActivity(it);
                }
            }
        });
        iv_see_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifSee();
            }
        });
    }
}

