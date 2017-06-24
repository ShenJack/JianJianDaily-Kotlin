package com.example.shenjack.zhihudailyreader.topstories

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by ShenJack on 2017/6/21.
 */

class NewFragment : Fragment() {
    private var localView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return localView
    }

    fun setView(view: View) {
        this.localView = view
    }

    companion object {


        fun newInstance(view: View): NewFragment {

            val args = Bundle()

            val fragment = NewFragment()
            fragment.arguments = args

            fragment.setView(view)

            return fragment
        }
    }
}
