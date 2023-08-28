package appstudio.search

import android.app.UiModeManager
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import appstudio.appbar.SearchBarView
import com.menny.android.anysoftkeyboard.R


class SearchBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)
        overridePendingTransition(R.anim.slide_up, 0)

        // Day/Night set background. For some reason the activity isnt handing dark mode
        val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        val isNightMode = uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
        val color = if (isNightMode) R.color.keyboard_bg_root_dark else R.color.keyboard_bg_root
        window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, color)))

        window?.attributes?.apply {
            gravity = Gravity.BOTTOM
            width = getWidth()
        }


        val searchBar = findViewById<SearchBarView>(R.id.search_bar)
        val searchText = findViewById<EditText>(R.id.search_text)
        val searchButton = findViewById<ImageButton>(R.id.search_button)
        val cancelButton = findViewById<TextView>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }


        searchText.postDelayed({
            searchText.requestFocus()
            val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            keyboard?.showSoftInput(searchText, InputMethodManager.SHOW_IMPLICIT)
        }, 100L)

    }

    override fun onBackPressed() {
//        TODO close entire activity if back is ever pressed
        super.onBackPressed()
    }

    private fun getWidth(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}
