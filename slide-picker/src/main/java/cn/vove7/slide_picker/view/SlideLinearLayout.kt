package cn.vove7.slide_picker.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import cn.vove7.slide_picker.SlideDelegate

/**
 * # SlideLinearLayout
 * Created on 2019/12/9
 *
 * @author Vove
 */
class SlideLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val delegate by lazy { SlideDelegate() }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return delegate.onProcessTouchEvent(this, ev)
    }

}