package com.huahua.su.paykeyboaradedittext;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PasswordDialog passwordDialog;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        passwordDialog = PasswordDialog.newInstance("8765");
        passwordDialog.setCancelable(true);
        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordDialog.show(getFragmentManager(), "password");
            }
        });

        passwordDialog.setPasswordDialogCallback(new PasswordDialog.PasswordDialogCallback() {
            @Override
            public void completed(final String password) {
                passwordDialog.showProgress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "你输入的密码是：" + password, Toast.LENGTH_LONG).show();
//                                passwordDialog.showFail();
//                                passwordDialog.showSuccess();
//                                passwordDialog.showWrongPassword();
                                passwordDialog.showInput();
                                passwordDialog.showWrongTip();
                                passwordDialog.clearInput();
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void failClose() {

            }

            @Override
            public void failRetry() {
                Toast.makeText(ctx, "failRetry", Toast.LENGTH_SHORT).show();
                passwordDialog.dismiss();
            }

            @Override
            public void successClose() {

            }
        });

    }


}
