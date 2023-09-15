package appstudio.appbar.search.suggestions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.menny.android.anysoftkeyboard.R

class SearchSuggestionsAdapter(

    private val context: Context,
    private val suggestions: List<Suggestion>,
) :
    RecyclerView.Adapter<SearchSuggestionsAdapter.SuggestionViewHolder>() {

    inner class SuggestionViewHolder(val suggestionView: View) :
        RecyclerView.ViewHolder(suggestionView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_suggest_item, parent, false)
        return SuggestionViewHolder(view)
    }

    override fun getItemCount(): Int = suggestions.size


    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        val textView = holder.suggestionView.findViewById<TextView>(R.id.suggestion_text)

        val suggestion = suggestions[position]

        textView.text = suggestion.text


        holder.suggestionView.setOnClickListener {
            goToLink(suggestion.url)
        }


    }

    fun goToLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Unable to open browser", Toast.LENGTH_SHORT).show()
        }
    }
}

