package com.kotlinsg.kworkshopapp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kotlinsg.kworkshopapp.base.mvp.BasePresenter
import com.kotlinsg.kworkshopapp.base.mvp.BaseView

abstract class BaseFragment<Presenter : BasePresenter> : Fragment(), BaseView {

    abstract val presenter: Presenter
    abstract val layoutResourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutResourceId, container, false).also {
            presenter.attachView(this)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.viewShown()
    }

    override fun onPause() {
        super.onPause()
        presenter.viewHidden()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    final override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    final override fun hideKeyboard() {
        (activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
    }

}