package com.example.shenjack.zhihudailyreader.storydetail;

import com.example.shenjack.zhihudailyreader.BaseView;
import com.example.shenjack.zhihudailyreader.data.Detail;

/**
 * Created by ShenJack on 2017/6/17.
 */

public interface DetailContract {
    interface View extends BaseView<Presenter>{
        void showLoadingIndicator(boolean visible);

        void showStoryDetail(Detail detail);

    }

    interface Presenter {
        void loadStoryDetail(String id);

    }
}
