@file:JvmName("AppView")

package appstudio.appbar

import android.view.ViewGroup
import androidx.annotation.DrawableRes

interface AppView {
    @get:DrawableRes
    val icon: Int

    val name: String

    fun onAppIconClick()

    interface KeyboardAppListener {
        fun onAppOpened(parent: ViewGroup)
        fun onAppClosed()
    }

}