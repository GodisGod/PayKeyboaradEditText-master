package com.huahua.su.paykeyboaradedittext;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by PLUSH81029 on 2017/8/10.
 */

public class PasswordDialog extends DialogFragment {

    private LinearLayout lineSuccess;
    private LinearLayout lineInput;
    private ProgressBar progressBar;
    public PasswordEditText passwordEditText;
    private TextView tvCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.password_dialog, container);
        lineSuccess = (LinearLayout) view.findViewById(R.id.line_success);
        lineInput = (LinearLayout) view.findViewById(R.id.line_input);
        passwordEditText = (PasswordEditText) view.findViewById(R.id.password_input);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        passwordEditText.setSecurityEditCompileListener(new PasswordEditText.SecurityEditCompileListener() {
            @Override
            public void onNumCompleted(final String password) {
                passwordEditText.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                passwordDialogCallback.completed(password);
                            }
                        });
                    }
                }).start();
            }
        });
        return view;
    }

    public void checkPasswordSuccess() {
        tvCancel.setVisibility(View.GONE);
        lineInput.setVisibility(View.GONE);
        lineSuccess.setVisibility(View.VISIBLE);
    }

    private PasswordDialogCallback passwordDialogCallback;

    public interface PasswordDialogCallback {
        void completed(String password);
    }

    public void setPasswordDialogCallback(PasswordDialogCallback passwordDialogCallback) {
        this.passwordDialogCallback = passwordDialogCallback;
    }
}
