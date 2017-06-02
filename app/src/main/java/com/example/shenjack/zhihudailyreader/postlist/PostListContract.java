package com.example.shenjack.zhihudailyreader.postlist;

import com.example.shenjack.zhihudailyreader.BasePresenter;
import com.example.shenjack.zhihudailyreader.BaseView;

/**
 * Created by ShenJack on 2017/5/29.
 */

public interface PostListContract {

    interface View extends BaseView<Presenter>{
        void showLoadingIndicator(boolean visible);

    }

    interface Presenter extends BasePresenter{

    }
}
