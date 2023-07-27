package appstudio.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.menny.android.anysoftkeyboard.R

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchBar = findViewById<EditText>(R.id.searchBar)
        searchBar.requestFocus()

        //This doesnt work
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(searchBar, InputMethodManager.SHOW_IMPLICIT)

        val goButton = findViewById<Button>(R.id.goButton)

        goButton.setOnClickListener {
            val searchText = searchBar.text.toString()
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bing.com/search?q=$searchText"))
            startActivity(intent)
        }
    }
}
