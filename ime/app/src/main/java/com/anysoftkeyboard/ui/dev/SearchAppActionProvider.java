package com.anysoftkeyboard.ui.dev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.anysoftkeyboard.AnySoftKeyboard;
import com.anysoftkeyboard.keyboards.views.KeyboardViewContainerView;
import com.menny.android.anysoftkeyboard.R;

import appstudio.appbar.SearchKeyboardAppView;


public class SearchAppActionProvider implements KeyboardViewContainerView.StripActionProvider {
    @NonNull
    private final Context mContext;
    private SearchKeyboardAppView mSearchAppView;

    public SearchAppActionProvider(@NonNull Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public @NonNull View inflateActionView(@NonNull ViewGroup parent) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.app_item, parent, false);
        root.setOnClickListener(v -> startSearchApp(parent));
        return root;
    }

    private void startSearchApp(@NonNull ViewGroup parent) {

        mSearchAppView = new SearchKeyboardAppView(mContext, null,null);
        mSearchAppView.onAppOpened(parent);
    }

    @Override
    public void onRemoved() {
        mSearchAppView.onAppClosed();
    }
}
