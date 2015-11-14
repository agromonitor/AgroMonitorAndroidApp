package com.example.kostas.agromonitor2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public Button logInButton;
    private String username[] = new String[]{"user0", "user1", "user2", "user3", "user4"};
    private String password[] = new String[]{"pass0", "pass1", "pass2", "pass3", "pass4"};
    private EditText loginPassword = null;
    private EditText loginUsername = null;
    private TextView errmsg = null;
    private CharSequence errorloginmsg = "Invalid input. Try again.";
    int userPairFlag = -1, passPairFlag = -1;
    boolean userFlag = false;
    boolean passFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        listenerOfLoginButton();
    }


public void listenerOfLoginButton()
{
    loginUsername = (EditText) findViewById(R.id.TFusername);
    loginPassword = (EditText) findViewById(R.id.TFpassword);
    logInButton = (Button) findViewById(R.id.Blogin);


    errmsg = (TextView) findViewById(R.id.TVErrorMessage);

    logInButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (validateLoginData())   //if user & pass are valid and maching, go to main application
            {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
                initializeLoginData();
            } else //if user is invalid, give error message in red
            {
                initializeLoginData();
                presentErrorMsg();
            }
        }
    });
}
    private boolean validateLoginData()
    {
        if (validUsername() && validPassword() && matchingPassUser())
            return true;
        else
            return false;
    }

    private boolean validUsername()
    {
        for (int i=0; i<5; i++)
            if (Objects.equals(username[i], loginUsername.getText().toString())) {
                userFlag = true;
                userPairFlag = i;
            }
        return userFlag;
    }

    private boolean validPassword()
    {

        for (int i=0; i<5; i++)
            if (Objects.equals(password[i], loginPassword.getText().toString())) {
                passFlag = true;
                passPairFlag = i;
            }
        return passFlag;
    }

    private boolean matchingPassUser()
    {
        if (Objects.equals(userPairFlag, passPairFlag)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void initializeLoginData()
    {
        passFlag = userFlag = false;
        loginPassword.setText("");
        loginUsername.setText("");
    }

    private void presentErrorMsg()
    {
        long startTime = System.nanoTime();
        errmsg.setText(errorloginmsg);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
    }

    //closure of activity class
}