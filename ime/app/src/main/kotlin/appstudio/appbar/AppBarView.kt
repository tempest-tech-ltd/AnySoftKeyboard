@file:JvmName("AppBarView")

package appstudio.appbar

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import appstudio.appbar.search.SearchBarView
import appstudio.offers.Offer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.menny.android.anysoftkeyboard.R

open class AppBarView(context: Context, attrs: AttributeSet?) :
    RecyclerView(context, attrs) {


    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = AppViewAdapter(getApps(), getOffers())
        background = ResourcesCompat.getDrawable(resources, R.drawable.tempest_background, null)


    }

    private fun getApps(): List<AppView> {
        val searchApp = SearchBarView(context)

        return listOf(searchApp)
    }

    private fun getOffers(): List<Offer> {
        //Mock offers
        val offers = listOf(
            Offer(
                "Youtube",
                "https://cdn-icons-png.flaticon.com/512/1384/1384060.png",
                "https://www.youtube.com"
            ),
            Offer(
                "Giphy",
                "https://cdn.iconscout.com/icon/free/png-256/free-giphy-461796.png",
                "https://giphy.com"
            ),
            Offer(
                "Reddit",
                "https://cdn-icons-png.flaticon.com/512/2504/2504934.png",
                "https://reddit.com"
            ),
            Offer(
                "Facebook",
                "https://i.pinimg.com/originals/ce/d6/6e/ced66ecfc53814d71f8774789b55cc76.png",
                "https://facebook.com"
            ),
            Offer(
                "Candy Crush",
                "https://play-lh.googleusercontent.com/TLUeelx8wcpEzf3hoqeLxPs3ai1tdGtAZTIFkNqy3gbDp1NPpNFTOzSFJDvZ9narFS0",
                "https://play.google.com/store/apps/details?id=com.king.candycrushsaga&hl=en_US&gl=US"
            ),
            Offer(
                "Subway Surfers",
                "https://static.wikia.nocookie.net/subwaysurf/images/4/4b/FirstAvatar.jpg/revision/latest/scale-to-width-down/250?cb=20180314123155",
                "https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf&hl=en_US&gl=US"
            ),
            Offer(
                "Turbo Tax",
                "https://play-lh.googleusercontent.com/QJXOh3117MdSycj1Q2_hSa8ETGLjQ0EKk1CsZjGwh5sL_8xUFJCsT19YOaCaUNI6dw",
                "https://play.google.com/store/apps/details?id=com.intuit.turbotax.mobile&hl=en_US&gl=US"
            ),
        )
        return offers
    }

    inner class AppViewAdapter(
        private val apps: List<AppView>,
        private val offers: List<Offer>
    ) :
        RecyclerView.Adapter<AppViewAdapter.AppViewHolder>() {

        inner class AppViewHolder(val appView: View) : RecyclerView.ViewHolder(appView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.app_item, parent, false)
            return AppViewHolder(view)
        }

        override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
            val icon = holder.appView.findViewById<ImageView>(R.id.icon)
            val name = holder.appView.findViewById<TextView>(R.id.name)

            //TODO there is definitely a better way to make this adapter.
            if (isOffer(position)) {

                val pos = position - apps.size
                val offer = offers[pos]

                name.text = offer.name

                Glide.with(context)
                    .load(offer.iconUrl)
                    .placeholder(R.drawable.ic_media_insertion)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(icon)

                holder.appView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(offer.url))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "Unable to open browser", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                val app = apps[position]
                name.text = app.name

                icon.setImageResource(app.icon)
                holder.appView.setOnClickListener {
                    app.onAppIconClick()
                }
            }
        }

        private fun isOffer(position: Int) = position >= apps.size
        override fun getItemCount() = apps.size + offers.size
    }
}
