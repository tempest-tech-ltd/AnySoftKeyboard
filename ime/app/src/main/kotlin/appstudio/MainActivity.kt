package appstudio

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anysoftkeyboard.ui.settings.MainSettingsActivity
import com.anysoftkeyboard.ui.settings.setup.SetupSupport
import com.menny.android.anysoftkeyboard.R


class MainActivity : AppCompatActivity() {
    private lateinit var langButton: TextView
    private lateinit var switchKeyboard: TextView
    private lateinit var enabledText: TextView

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToAsk = findViewById<TextView>(R.id.go_to_ask)
        goToAsk.setOnClickListener {
            val intent = Intent(this, MainSettingsActivity::class.java)
            startActivity(intent)
        }

        enabledText = findViewById<TextView>(R.id.enabled)
        langButton = findViewById<TextView>(R.id.go_to_language_settings)
        switchKeyboard = findViewById<TextView>(R.id.go_to_switch_keyboard)
        langButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
            startActivity(intent)
        }
        switchKeyboard.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodPicker()
        }

        handler.postDelayed(object : Runnable {
            override fun run() {
                handleSettingVisibility()
                handleEnabled()

                // Continue polling
                handler.postDelayed(this, 500)
            }
        }, 500)


//        onEnterActivity()
    }

    override fun onResume() {
        super.onResume()
        handleSettingVisibility()
        handleEnabled()
        onEnterActivity()
    }

    fun handleSettingVisibility() {
        langButton.visibility =
            if (SetupSupport.isThisKeyboardEnabled(application)) View.GONE else View.VISIBLE
        switchKeyboard.visibility =
            if (SetupSupport.isThisKeyboardSetAsDefaultIME(application)) View.GONE else View.VISIBLE
    }

    fun handleEnabled() {
        val enabled = SetupSupport.isThisKeyboardEnabled(application)
        val default = SetupSupport.isThisKeyboardSetAsDefaultIME(application)
        val color = if (enabled && default) Color.GREEN else Color.RED

        enabledText.apply {
            setTextColor(color)
            text = "Enabled = $enabled | Default = $default"
        }
    }

    fun onEnterActivity() {
        val enabled = SetupSupport.isThisKeyboardEnabled(application)
        val default = SetupSupport.isThisKeyboardSetAsDefaultIME(application)
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!enabled) {
                    val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
                    startActivity(intent)
                } else if (!default) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showInputMethodPicker()
                }
            }
        }, 500)


    }

}
