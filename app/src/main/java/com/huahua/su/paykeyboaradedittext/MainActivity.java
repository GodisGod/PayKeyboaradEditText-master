package com.huahua.su.paykeyboaradedittext;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PasswordDialog passwordDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordDialog = new PasswordDialog();
        passwordDialog.setCancelable(true);
        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPayDialog();

                passwordDialog.show(getFragmentManager(), "password");
            }
        });

        passwordDialog.setPasswordDialogCallback(new PasswordDialog.PasswordDialogCallback() {
            @Override
            public void completed(String password) {
                Toast.makeText(MainActivity.this, "你输入的密码是：" + password, Toast.LENGTH_LONG).show();
                passwordDialog.checkPasswordSuccess();
            }
        });

    }


    public void showPayDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.password_dialog);

        final PasswordEditText editSecurityCode = (PasswordEditText) window
                .findViewById(R.id.password_input);
        editSecurityCode.setSecurityEditCompileListener(new PasswordEditText.SecurityEditCompileListener() {
            @Override
            public void onNumCompleted(String num) {
                Toast.makeText(MainActivity.this, "你输入的密码是：" + num, Toast.LENGTH_LONG).show();
            }
        });
    }

}
