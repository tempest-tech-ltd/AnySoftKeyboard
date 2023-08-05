@file:JvmName("AppView")
package appstudio.appbar
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout

abstract class AppView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    interface KeyboardAppListener {
        fun onAppOpened(parent: ViewGroup)
        fun onAppClosed()
    }

}