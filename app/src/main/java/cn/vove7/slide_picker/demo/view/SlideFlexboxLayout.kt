package cn.vove7.slide_picker.demo.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import cn.vove7.slide_picker.SlideDelegate
import cn.vove7.slide_picker.toggleSelected
import com.google.android.flexbox.FlexboxLayout

/**
 * # SlideFlexboxLayout
 * Created on 2019/12/9
 *
 * @author Vove
 */
class SlideFlexboxLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FlexboxLayout(context, attrs, defStyleAttr) {

    //保存之前状态
    private lateinit var backupStatus: Array<Boolean>
    private var downIndex = -1

    //逻辑可自由定制
    private val delegate by lazy {
        SlideDelegate { target, p, action ->
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    downIndex = p
                    saveStatus()
                    target?.toggleSelected()
                }
                MotionEvent.ACTION_UP -> {
                    downIndex = -1
                }
                else -> {
                    checkTo(p)
                }
            }
        }
    }

    private fun checkTo(p: Int) {
        //空白处Move到p
        if (downIndex == -1) {
            saveStatus()
            downIndex = p
        }

        val min = p.coerceAtMost(downIndex)
        val max = p.coerceAtLeast(downIndex)

        fun checkStatus(i: Int, s: Boolean) {
            getChildAt(i).isSelected = s
        }

        for (i in 0 until min) {
            checkStatus(i, backupStatus[i])
        }
        for (i in min..max) {
            checkStatus(i, !backupStatus[i])
        }
        for (i in max + 1 until childCount) {
            checkStatus(i, backupStatus[i])
        }
    }

    private fun saveStatus() {
        backupStatus = Array(childCount) {
            getChildAt(it).isSelected
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return delegate.onProcessTouchEvent(this, ev)
    }
}