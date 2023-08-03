@file:JvmName("AppView")
package appstudio.appbar
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes

abstract class AppView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    @get:DrawableRes
    abstract val icon: Int

    interface KeyboardAppListener {
        fun onAppOpened(parent: ViewGroup)
        fun onAppClosed()
    }

}