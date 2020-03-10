package cn.vove7.slide_picker.demo

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cn.vove7.slide_picker.demo.view.SlideFlexboxLayout
import cn.vove7.slide_picker.toggleSelected
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchContent(null)
    }

    private fun buildFlexContent(): View {

        val container = SlideFlexboxLayout(this).apply {
            flexWrap = FlexWrap.WRAP
        }

        val rd = Random()
        for (i in 0..100) {
            container.addView(TextView(this).apply {
                layoutParams = FlexboxLayout.LayoutParams(-2, -2).apply {
                    setPadding(20, 10, 20, 10)
                    setMargins(5, 5, 5, 5)
                }
                gravity = Gravity.CENTER
                minHeight = 60
                minWidth = 100
                setBackgroundResource(R.drawable.bg_text_selector)
                setTextColor(ContextCompat.getColorStateList(this@MainActivity, R.color.demo_text_colors))
                text = "*" * rd.nextInt(5)
            })
        }
        return container
    }

    var b = false
    fun switchContent(view: View?) {
        content_container.toggleSelected()
        content_container.apply {
            removeAllViews()
            b = !b
            addView(
                if (b) layoutInflater.inflate(R.layout.demo_frame, null)
                else buildFlexContent()
            )
        }
    }

}

private operator fun String.times(t: Int): CharSequence = buildString {
    for (i in 0..t) this.append(this@times)
}
