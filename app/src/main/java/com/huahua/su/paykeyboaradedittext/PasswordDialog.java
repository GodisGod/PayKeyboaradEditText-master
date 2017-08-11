package com.huahua.su.paykeyboaradedittext;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by PLUSH81029 on 2017/8/10.
 */

public class PasswordDialog extends DialogFragment {

    private LinearLayout lineSuccess;
    private RelativeLayout rlInput;
    private ProgressBar progressBar;
    public PasswordEditText passwordEditText;
    private ImageView imgClose;
    //兑换成功
    private TextView tvSuccess;
    //兑换失败
    private LinearLayout lineFail;
    private TextView tvFailClose;
    private TextView tvFailRetry;
    //密码错误
    private ImageView imgFail;
    private TextView tvFailTip;
    //错误次数过多
    private TextView tvWrongTip;
    private TextView tvBeansNum1;
    private TextView tvBeansNum2;

    public static PasswordDialog newInstance(String num) {

        Bundle args = new Bundle();
        args.putString("exchangeNum", num);
        PasswordDialog fragment = new PasswordDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.password_dialog, container);

        initView(view);
        reset();
        initEvent();
        return view;
    }

    private void initView(View view) {
        lineSuccess = (LinearLayout) view.findViewById(R.id.line_success);
        rlInput = (RelativeLayout) view.findViewById(R.id.rl_input);
        passwordEditText = (PasswordEditText) view.findViewById(R.id.password_input);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        imgClose = (ImageView) view.findViewById(R.id.img_close);
        //成功
        tvSuccess = (TextView) view.findViewById(R.id.tv_success);
        //失败
        lineFail = (LinearLayout) view.findViewById(R.id.line_fail);
        tvFailClose = (TextView) view.findViewById(R.id.tv_fail_close);
        tvFailRetry = (TextView) view.findViewById(R.id.tv_fail_retry);
        imgFail = (ImageView) view.findViewById(R.id.img_fail);
        tvFailTip = (TextView) view.findViewById(R.id.tv_fail_tip);
        //错误次数过多
        tvWrongTip = (TextView) view.findViewById(R.id.tv_wrong_tip);
        tvBeansNum1 = (TextView) view.findViewById(R.id.tv_beans_num);
        tvBeansNum2 = (TextView) view.findViewById(R.id.tv_beans_num2);
        String num = getArguments().getString("exchangeNum");
        tvBeansNum1.setText(num);
        tvBeansNum2.setText(num);
    }

    private void initEvent() {
        passwordEditText.setSecurityEditCompileListener(new PasswordEditText.SecurityEditCompileListener() {
            @Override
            public void onNumCompleted(final String password) {
                passwordDialogCallback.completed(password);
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvFailClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvFailRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordDialogCallback.failRetry();
            }
        });
    }

    public void reset() {
        rlInput.setVisibility(View.VISIBLE);
        lineSuccess.setVisibility(View.GONE);
        lineFail.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.VISIBLE);
    }

    public void showInput() {
        rlInput.setVisibility(View.VISIBLE);
        lineSuccess.setVisibility(View.GONE);
        lineFail.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvWrongTip.setVisibility(View.GONE);
    }

    public void showProgress() {
        rlInput.setVisibility(View.VISIBLE);
        lineSuccess.setVisibility(View.GONE);
        lineFail.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void showFail() {
        lineFail.setVisibility(View.VISIBLE);
        imgClose.setVisibility(View.GONE);
        rlInput.setVisibility(View.GONE);
        lineSuccess.setVisibility(View.GONE);
        imgFail.setImageResource(R.drawable.icon_fail);
        tvFailTip.setText("兑换失败，请稍后重试");
    }

    public void showWrongPassword() {
        showFail();
        imgFail.setImageResource(R.drawable.icon_error);
        tvFailTip.setText("密码错误");
    }

    public void showSuccess() {
        lineFail.setVisibility(View.GONE);
        rlInput.setVisibility(View.GONE);
        lineSuccess.setVisibility(View.VISIBLE);
    }

    public void showWrongTip() {
        reset();
        showInput();
        tvWrongTip.setVisibility(View.VISIBLE);
    }

    public void clearInput() {
        passwordEditText.clearInput();
    }

    private PasswordDialogCallback passwordDialogCallback;

    public interface PasswordDialogCallback {
        void completed(String password);

        void failClose();

        void failRetry();

        void successClose();

    }

    public void setPasswordDialogCallback(PasswordDialogCallback passwordDialogCallback) {
        this.passwordDialogCallback = passwordDialogCallback;
    }
}
