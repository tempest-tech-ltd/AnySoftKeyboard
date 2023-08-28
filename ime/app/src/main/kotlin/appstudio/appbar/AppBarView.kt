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
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import appstudio.appbar.search.SearchBarView
import appstudio.offers.Offer
import com.bumptech.glide.Glide
import com.menny.android.anysoftkeyboard.R

open class AppBarView(context: Context, attrs: AttributeSet?) :
    RecyclerView(context, attrs) {


    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = AppViewAdapter(getApps(), getOffers())
        background = ResourcesCompat.getDrawable(resources, R.drawable.tempest_background,null)


    }

    private fun getApps(): List<AppView> {
        val searchApp = SearchBarView(context)

        return listOf(searchApp)
    }

    private fun getOffers(): List<Offer> {
        //Mock offers
        val offers = listOf(
            Offer(
                "https://cdn-icons-png.flaticon.com/512/1384/1384060.png",
                "https://www.youtube.com"
            ),
            Offer(
                "https://cdn.iconscout.com/icon/free/png-256/free-giphy-461796.png",
                "https://giphy.com"
            ),
            Offer("https://cdn-icons-png.flaticon.com/512/2504/2504934.png", "https://reddit.com"),
            Offer(
                "https://i.pinimg.com/originals/ce/d6/6e/ced66ecfc53814d71f8774789b55cc76.png",
                "https://facebook.com"
            ),
            Offer(
                "https://play-lh.googleusercontent.com/TLUeelx8wcpEzf3hoqeLxPs3ai1tdGtAZTIFkNqy3gbDp1NPpNFTOzSFJDvZ9narFS0",
                "https://play.google.com/store/apps/details?id=com.king.candycrushsaga&hl=en_US&gl=US"
            ),
            Offer(
                "https://static.wikia.nocookie.net/subwaysurf/images/4/4b/FirstAvatar.jpg/revision/latest/scale-to-width-down/250?cb=20180314123155",
                "https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf&hl=en_US&gl=US"
            ),
            Offer(
                "https://play-lh.googleusercontent.com/QJXOh3117MdSycj1Q2_hSa8ETGLjQ0EKk1CsZjGwh5sL_8xUFJCsT19YOaCaUNI6dw",
                "https://play.google.com/store/apps/details?id=com.intuit.turbotax.mobile&hl=en_US&gl=US"
            ),
        )
        return offers
    }

    inner class AppViewAdapter(
        private val appViews: List<AppView>,
        private val offers: List<Offer>
    ) :
        RecyclerView.Adapter<AppViewAdapter.AppViewHolder>() {

        inner class AppViewHolder(val iconView: View) : RecyclerView.ViewHolder(iconView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.app_item, parent, false)
            return AppViewHolder(view)
        }

        override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
            val icon = holder.iconView.findViewById<ImageView>(R.id.appIcon)

            //TODO there is definitely a better way to make this adapter.
            if (isOffer(position)) {

                val pos = position - appViews.size
                val offer = offers[pos]

                Glide.with(context)
                    .load(offer.iconUrl)
                    .placeholder(R.drawable.ic_media_insertion)
                    .into(icon)

                holder.iconView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(offer.url))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "Unable to open browser", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                val appView = appViews[position]
                icon.setImageResource(appView.icon)
                holder.iconView.setOnClickListener {
                    appView.onAppIconClick()
                }
            }
        }

        private fun isOffer(position: Int) = position >= appViews.size
        override fun getItemCount() = appViews.size + offers.size
    }
}
