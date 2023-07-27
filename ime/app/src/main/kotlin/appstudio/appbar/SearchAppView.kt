package appstudio.appbar

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.menny.android.anysoftkeyboard.R

class SearchAppView(
    context: Context,
    attrs: AttributeSet? = null
) : AppView(context, attrs) {

    override val icon = R.drawable.ic_action_search
    val searchBar: EditText
    private val goButton: ImageButton

    override var onShow: (() -> Unit)? = {
        this.visibility = View.VISIBLE

    }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.app_view, this, true)
        searchBar = view.findViewById(R.id.searchBar)

//        searchBar.setOnFocusChangeListener { v, hasFocus -> }

        goButton = view.findViewById(R.id.goButton)
        setupViews()

    }

    private fun setupViews() {
        goButton.setOnClickListener { performSearch() }
        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        val query = searchBar.text.toString()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bing.com/search?q=$query"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Unable to open browser", Toast.LENGTH_SHORT).show()
        }
    }
}
