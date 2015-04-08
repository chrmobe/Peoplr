package com.example.ahaag.peoplr;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.FacebookCallback;

import com.facebook.FacebookSdk;

//http://www.tutorialspoint.com/android/android_facebook_integration.htm

public class LoginActivity extends Activity {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
