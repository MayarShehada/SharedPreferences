package com.birzeit.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText username_edt;
    private EditText password_edt;
    private CheckBox remember_chk;

    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    private boolean flag = false;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        setUpSharedPrefs();
        checkPrefs();
    }

    private void setUpViews() {
        username_edt = findViewById(R.id.username_edt);
        password_edt = findViewById(R.id.password_edt);
        remember_chk = findViewById(R.id.remember_chk);
    }

    private void setUpSharedPrefs() {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if (flag){
            String name = prefs.getString(NAME, "");
            String password = prefs.getString(PASS, "");
            username_edt.setText(name);
            password_edt.setText(password);
            remember_chk.setChecked(true);
        }
    }

    public void loginButtonAction(View view) {

        String name = username_edt.getText().toString();
        String password = password_edt.getText().toString();

        if(remember_chk.isChecked()){
            if(!flag){
                editor.putString(NAME, name);
                editor.putString(PASS, password);
                editor.putBoolean(FLAG, true);
                editor.commit();
            }
        }else if(!remember_chk.isChecked()) {
            if(flag){
                editor.putString(NAME, "");
                editor.putString(PASS, "");
                editor.putBoolean(FLAG, false);
                editor.commit();
                }
            }
        }
    }
