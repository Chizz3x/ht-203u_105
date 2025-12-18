package com.hti.xtherm.hti160203u.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import com.hti.xtherm.hti160203u.helper.Config;
import com.hti.xtherm.hti160203u.helper.ShareHelper;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.TipDialog;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.util.TextInfo;

import java.io.IOException;
import java.util.Locale;

/* loaded from: classes.dex */
public abstract class BaseActivity extends AppCompatActivity {
    protected boolean FULLSCREEN = true;
    protected Handler mHandler;

    protected abstract boolean onFullScreen();

    protected void onHandlerMessage(Message message) {
    }

    protected abstract void onInitData() throws IOException;

    protected abstract void onInitView();

    protected abstract boolean onKeepScreen();

    protected abstract int onLayout();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initWindow();
        initHandler();
        setContentView(onLayout());
        onInitView();
        try {
            onInitData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        final Context languageContext = getLanguageContext(context, ShareHelper.getLanguage());
        super.attachBaseContext(new ContextThemeWrapper(languageContext, null) { // from class: com.hti.xtherm.hti160203u.base.BaseActivity.1
            @Override // androidx.appcompat.view.ContextThemeWrapper
            public void applyOverrideConfiguration(Configuration configuration) {
                if (configuration != null) {
                    configuration.setTo(languageContext.getResources().getConfiguration());
                }
                super.applyOverrideConfiguration(configuration);
            }
        });
    }

    private void initWindow() {
        supportRequestWindowFeature(1);
        if (onFullScreen()) {
            getWindow().setFlags(1024, 1024);
        }
        if (onKeepScreen()) {
            getWindow().addFlags(128);
        }
    }

    private void initHandler() {
        this.mHandler = new Handler(Looper.myLooper()) { // from class: com.hti.xtherm.hti160203u.base.BaseActivity.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                BaseActivity.this.onHandlerMessage(message);
            }
        };
    }

    protected Context getLanguageContext(Context context, Config.Language language) {
        Resources resources = context.getResources();
        resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Locale.ENGLISH;
        int i = AnonymousClass3.$SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Language[language.ordinal()];
        if (i == 1) {
            locale = Locale.SIMPLIFIED_CHINESE;
        } else if (i == 2) {
            locale = Locale.ENGLISH;
        } else if (i == 3) {
            locale = new Locale("ru", "RU");
        }
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    /* renamed from: com.hti.xtherm.hti160203u.base.BaseActivity$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Language;

        static {
            int[] iArr = new int[Config.Language.values().length];
            $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Language = iArr;
            try {
                iArr[Config.Language.CHINESE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Language[Config.Language.ENGLISH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Language[Config.Language.RUSSIAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected String getStringByid(int i) {
        return getLanguageContext(this, ShareHelper.getLanguage()).getResources().getString(i);
    }

    protected String getStringValue(int i) {
        return getLanguageContext(this, ShareHelper.getLanguage()).getResources().getString(i);
    }

    protected String getStringValue(int i, Object... objArr) {
        return getLanguageContext(this, ShareHelper.getLanguage()).getResources().getString(i, objArr);
    }

    protected String getStringByid(int i, Object... objArr) {
        return getLanguageContext(this, ShareHelper.getLanguage()).getResources().getString(i, objArr);
    }

    private void tipMessage(final int i, final WaitDialog.TYPE type) {
        runOnUiThread(new Runnable() { // from class: com.hti.xtherm.hti160203u.base.BaseActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                m94lambda$tipMessage$0$comhtixthermhti160203ubaseBaseActivity(i, type);
            }
        });
    }

    /* renamed from: lambda$tipMessage$0$com-hti-xtherm-hti160203u-base-BaseActivity, reason: not valid java name */
    /* synthetic */ void m94lambda$tipMessage$0$comhtixthermhti160203ubaseBaseActivity(int i, WaitDialog.TYPE type) {
        TipDialog.show(getLanguageContext(this, ShareHelper.getLanguage()).getResources().getString(i), type);
    }

    protected void tipNone(int i) {
        tipMessage(i, WaitDialog.TYPE.NONE);
    }

    protected void tipWarning(int i) {
        tipMessage(i, WaitDialog.TYPE.WARNING);
    }

    protected void tipSuccess(int i) {
        tipMessage(i, WaitDialog.TYPE.SUCCESS);
    }

    protected void tipFailed(int i) {
        tipMessage(i, WaitDialog.TYPE.ERROR);
    }

    protected void show_message_dialog(int i, int i2, int i3, OnDialogButtonClickListener<MessageDialog> onDialogButtonClickListener, int i4, OnDialogButtonClickListener<MessageDialog> onDialogButtonClickListener2, boolean z) {
        Resources resources = getLanguageContext(this, ShareHelper.getLanguage()).getResources();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog okTextInfo = MessageDialog.build().setTitle(resources.getString(i)).setMessage(resources.getString(i2)).setOkButton(resources.getString(i3), onDialogButtonClickListener).setOkTextInfo(textInfo);
        if (i4 != -1) {
            okTextInfo.setCancelButton(resources.getString(i4), onDialogButtonClickListener2).setCancelTextInfo(textInfo);
        }
        okTextInfo.setCancelable(z);
        okTextInfo.show();
    }
}
