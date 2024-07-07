package com.example.weather_application.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.weather_application.utils.MyApplication;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void attachBaseContext(Context base) {
    Context newContext = MyApplication.localeManager.setLocale(base);
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newContext));
  }

  @Override
  public void applyOverrideConfiguration(@Nullable Configuration overrideConfiguration) {
    if (overrideConfiguration != null) {
      int uiMode = overrideConfiguration.uiMode;
      overrideConfiguration.setTo(getBaseContext().getResources().getConfiguration());
      overrideConfiguration.uiMode = uiMode;
    }
    super.applyOverrideConfiguration(getResources().getConfiguration());
  }

    public abstract void onRequestPermisisonsResult(int requestCode, @NonNull String[] permisisons, @NonNull int[] grantResults);

  @SuppressLint("MissingSuperCall")
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    onRequestPermisisonsResult(requestCode, permissions, grantResults);
  }
}