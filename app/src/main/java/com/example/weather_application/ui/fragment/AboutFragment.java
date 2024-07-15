package com.example.weather_application.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_application.R;
import com.example.weather_application.adapters.FavoriteCitiesAdapter;
import com.example.weather_application.databinding.FragmentAboutBinding;
import com.example.weather_application.helper.DBHelper;
import com.example.weather_application.ui.activity.MainActivity;
import com.example.weather_application.utils.AppUtil;
import com.example.weather_application.utils.LocaleManager;
import com.example.weather_application.utils.MyApplication;
import com.example.weather_application.utils.SharedPreferencesUtil;
import com.example.weather_application.utils.ViewAnimation;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends DialogFragment {

  private Activity activity;
  private String currentLanguage;
  private FragmentAboutBinding binding;
  private DBHelper dbHelper;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = FragmentAboutBinding.inflate(inflater, container, false);
    View view = binding.getRoot();
    dbHelper = new DBHelper(requireContext());

    initVariables(view);

    return view;
  }

  private void initVariables(View view) {
    currentLanguage = MyApplication.localeManager.getLanguage();
    activity = getActivity();
    if (activity != null) {
      Drawable drawable = activity.getResources().getDrawable(R.drawable.ic_done_black_24dp);
      String versionName = "";
      try {
        versionName = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
      } catch (PackageManager.NameNotFoundException e) {
        // do nothing
      }
      setTextWithLinks(view.findViewById(R.id.text_application_info), getString(R.string.application_info_text, versionName));
      setTextWithLinks(view.findViewById(R.id.text_developer_info), getString(R.string.developer_info_text));
      setTextWithLinks(view.findViewById(R.id.text_design_api), getString(R.string.design_api_text));
      setTextWithLinks(view.findViewById(R.id.text_libraries), getString(R.string.libraries_text));
      setTextWithLinks(view.findViewById(R.id.text_license), getString(R.string.license_text));
      if (currentLanguage.equals(LocaleManager.LANGUAGE_ENGLISH)) {
        binding.englishButton.setIcon(drawable);
      } else {
        binding.persianButton.setIcon(drawable);
      }
    }
    binding.nightModeSwitch.setChecked(SharedPreferencesUtil.getInstance(activity).isDarkThemeEnabled());
    binding.nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferencesUtil.getInstance(activity).setDarkThemeEnabled(isChecked);
        if (isChecked) {
          AppCompatDelegate.setDefaultNightMode(
              AppCompatDelegate.MODE_NIGHT_YES);
        } else {
          AppCompatDelegate.setDefaultNightMode(
              AppCompatDelegate.MODE_NIGHT_NO);
        }
        activity.recreate();
      }
    });
    binding.closeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dismiss();
        if (getFragmentManager() != null) {
          getFragmentManager().popBackStack();
        }
      }
    });
    binding.englishButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentLanguage.equals(LocaleManager.LANGUAGE_PERSIAN)) {
          MyApplication.localeManager.setNewLocale(activity, LocaleManager.LANGUAGE_ENGLISH);
          restartActivity();
        }
      }
    });
    binding.persianButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentLanguage.equals(LocaleManager.LANGUAGE_ENGLISH)) {
          MyApplication.localeManager.setNewLocale(activity, LocaleManager.LANGUAGE_PERSIAN);
          restartActivity();
        }
      }
    });
    binding.toggleInfoButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        toggleView();
      }
    });
    binding.toggleInfoLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        toggleView();
      }
    });
    displayAllFavoriteCities(view);
  }

  private void displayAllFavoriteCities(View view) {
    // Ensure dbHelper is initialized
    if (dbHelper == null) {
      dbHelper = new DBHelper(requireContext());
    }

    Cursor cursor = dbHelper.getAllFavoriteCities();
    if (cursor != null) {
      try {
        List<String> favoriteCities = new ArrayList<>();
        int cityNameIndex = cursor.getColumnIndex("cityName");
        while (cursor.moveToNext()) {
          if (cityNameIndex != -1) {
            String cityName = cursor.getString(cityNameIndex);
            favoriteCities.add(cityName);
          } else {
            Log.e("AboutFragment", "'cityName' column not found in cursor");
          }
        }

        if (!favoriteCities.isEmpty()) {
          // Update RecyclerView adapter
          RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favorite_cities);
          recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
          FavoriteCitiesAdapter adapter = new FavoriteCitiesAdapter(favoriteCities, activity);
          recyclerView.setAdapter(adapter);
        } else {
          Toast.makeText(activity, "No favorite cities saved yet.", Toast.LENGTH_SHORT).show();
        }
      } finally {
        cursor.close();
      }
    } else {
      Toast.makeText(activity, "Cursor is null.", Toast.LENGTH_SHORT).show();
    }
  }

  private void toggleView() {
    boolean show = toggleArrow(binding.toggleInfoButton);
    if (show) {
      ViewAnimation.expand(binding.expandLayout, new ViewAnimation.AnimListener() {
        @Override
        public void onFinish() {
        }
      });
    } else {
      ViewAnimation.collapse(binding.expandLayout);
    }
  }

  private void setTextWithLinks(TextView textView, String htmlText) {
    AppUtil.setTextWithLinks(textView, AppUtil.fromHtml(htmlText));
  }


  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setCancelable(true);
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.copyFrom(dialog.getWindow().getAttributes());
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
    dialog.getWindow().setAttributes(lp);
    return dialog;
  }

  private void restartActivity() {
    Intent intent = new Intent(activity, MainActivity.class);
    activity.startActivity(intent);
    activity.finish();
  }

  private boolean toggleArrow(View view) {
    if (view.getRotation() == 0) {
      view.animate().setDuration(200).rotation(180);
      return true;
    } else {
      view.animate().setDuration(200).rotation(0);
      return false;
    }
  }
}
