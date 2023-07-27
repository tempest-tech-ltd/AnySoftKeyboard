package com.anysoftkeyboard.ui.dev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.anysoftkeyboard.AnySoftKeyboard;
import com.anysoftkeyboard.keyboards.views.KeyboardViewContainerView;
import com.menny.android.anysoftkeyboard.R;

import net.evendanan.pixel.GeneralDialogController;

import appstudio.appbar.SearchAppView;


public class SearchAppActionProvider implements KeyboardViewContainerView.StripActionProvider {
    @NonNull
    private final Context mContext;
    private final AnySoftKeyboard mAnySoftKeyboard;

    public SearchAppActionProvider(@NonNull Context context, AnySoftKeyboard anySoftKeyboard) {
        mContext = context.getApplicationContext();
        mAnySoftKeyboard = anySoftKeyboard;
    }

    @Override
    public @NonNull View inflateActionView(@NonNull ViewGroup parent) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.app_item, parent, false);
        root.setOnClickListener(v -> startSearchApp(parent));//
        return root;
    }

    private void startSearchApp(@NonNull ViewGroup parent) {

        SearchAppView searchApp = new SearchAppView(mContext, null);
        parent.addView(searchApp, 0);

        // WIP
//        GeneralDialogController dialogController = mAnySoftKeyboard.mGeneralDialogController;
//        dialogController.showSearchDialog();
    }

    @Override
    public void onRemoved() {
    }
}
