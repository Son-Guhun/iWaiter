package com.example.songuhun.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.songuhun.test.TestStuff;
import com.example.songuhun.objects.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TestStuff.currentUser != null) {
            startActivity(new Intent(LoginActivity.this, SelectTableActivity.class));
        }

        setContentView(R.layout.activity_login);
        createEditorActionListener();
        configureRememberMe();
    }

    private void createEditorActionListener() {
        final EditText userInput = findViewById(R.id.editText_user);
        final EditText passwordInput = findViewById(R.id.editText_password);
        passwordInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_DONE){
                    TestStuff.currentUser = new User(userInput.getText().toString());
                }

                startActivity(new Intent(LoginActivity.this, SelectTableActivity.class));
                return true;
            }
        });
    }

    private void configureRememberMe(){
        android.widget.Switch mySwitch = findViewById(R.id.switch_rememberMe);

        //TODO: Load whether user wants to remember username or not.

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked)
                ;//TODO: Remember me
            else
                ;//TODO: Don't remember me
            }
        });

    }


}
