package com.example.shenjack.zhihudailyreader.storydetail

import com.example.shenjack.zhihudailyreader.BaseView
import com.example.shenjack.zhihudailyreader.data.Detail

/**
 * Created by ShenJack on 2017/6/17.
 */

interface DetailContract {
    interface View : BaseView<Presenter> {
        fun showLoadingIndicator(visible: Boolean)

        fun showStoryDetail(detail: Detail)

    }

    interface Presenter {
        fun loadStoryDetail(id: String)

    }
}
