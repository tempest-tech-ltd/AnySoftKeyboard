package appstudio.search

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import appstudio.appbar.SearchBarView
import com.menny.android.anysoftkeyboard.R


class SearchBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)
        overridePendingTransition(R.anim.slide_up, 0);
        window?.setBackgroundDrawable(null)
        window?.attributes?.apply {
            gravity= Gravity.BOTTOM
            width = getWidth()
        }


        val searchBar = findViewById<SearchBarView>(R.id.search_bar)
        val searchText = findViewById<EditText>(R.id.search_text)
        val searchButton = findViewById<AppCompatImageButton>(R.id.search_button)
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
        closeKeyboard()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 1000)
        super.onBackPressed()
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        view?.let {
            it.clearFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private fun getWidth():Int{
        val displayMetrics = DisplayMetrics()
        windowManager. defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}
