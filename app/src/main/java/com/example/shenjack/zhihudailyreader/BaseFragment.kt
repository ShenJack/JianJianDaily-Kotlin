package com.example.shenjack.zhihudailyreader

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
* sjjkk on 2017/10/13 in Beijing.

*/

abstract class BaseFragment : Fragment() , BaseView<BasePresenter>{
    protected var mContext : Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(mContext,getLayoutId(),null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(getView()!!)
        initData()
    }

    abstract fun initViews(rootView: View)

    abstract fun initData()

    abstract fun getLayoutId() : Int

}
