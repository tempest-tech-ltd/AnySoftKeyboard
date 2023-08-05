package com.anysoftkeyboard.ui.dev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.anysoftkeyboard.keyboards.views.KeyboardViewContainerView;
import com.menny.android.anysoftkeyboard.R;


public class SearchAppActionProvider implements KeyboardViewContainerView.StripActionProvider {
  @NonNull
  private final Context mContext;

  public SearchAppActionProvider(@NonNull Context context) {
    mContext = context.getApplicationContext();
  }

  @Override
  public @NonNull View inflateActionView(@NonNull ViewGroup parent) {
    View root = LayoutInflater.from(mContext).inflate(R.layout.app_item, parent, false);
    return root;
  }

  @Override
  public void onRemoved() {
  }
}
