package cn.vove7.slide_picker.demo.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable

/**
 * # DemoView
 * Created on 2019/12/12
 *
 * @author Vove
 */
class DemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Checkable {
    private var isChecked = false

    override fun isChecked(): Boolean = isChecked
    override fun toggle() = setChecked(!isChecked)

    override fun setChecked(checked: Boolean) {
        isChecked = checked

        //处理样式
        if (checked) {
            setBackgroundColor(Color.RED)
        } else {
            setBackgroundColor(Color.BLUE)
        }
    }
}