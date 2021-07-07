package com.barros.listrepo.base

import androidx.appcompat.app.AppCompatActivity
import com.barros.listrepo.customView.LoadingDialog

open class BaseActivity: AppCompatActivity(), BaseView {
    private var loadingDialog: LoadingDialog? = null

    override fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.newInstance(this)
        }
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }
}