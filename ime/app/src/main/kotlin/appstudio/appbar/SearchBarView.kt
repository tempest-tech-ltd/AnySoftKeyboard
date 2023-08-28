package appstudio.appbar

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import appstudio.search.SearchBarActivity
import com.menny.android.anysoftkeyboard.R

class SearchBarView(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs), AppView {

    override val icon: Int
        get() = R.drawable.ic_search_app


    val searchText: EditText
    private val searchIcon: ImageButton

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.search_bar_view, this, true)
        searchText = view.findViewById(R.id.search_text)
        searchIcon = view.findViewById(R.id.search_button)

        setupViews()
    }

    private fun setupViews() {
        searchIcon.setOnClickListener { performSearch() }
        searchText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        val query = searchText.text.toString()
        if(query.isNotEmpty()){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bing.com/search?q=$query"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Unable to open browser", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onAppIconClick() {
        val intent = Intent(context, SearchBarActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Unable to open Search Activity", Toast.LENGTH_SHORT).show()
        }
    }
}
