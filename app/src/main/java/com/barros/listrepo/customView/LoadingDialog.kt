package com.barros.listrepo.customView

import android.app.Activity
import android.os.Bundle
import com.barros.listrepo.R
import com.barros.listrepo.base.BaseDialog

class LoadingDialog(activity: Activity) : BaseDialog(activity, R.style.full_screen_dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.progress_bar_indeterminate_dialog)
        setCancelable(false)
    }

    companion object {
        fun newInstance(activity: Activity): LoadingDialog {
            return LoadingDialog(activity)
        }
    }

}