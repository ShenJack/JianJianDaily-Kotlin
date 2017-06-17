package com.example.shenjack.zhihudailyreader.storydetail;

        import android.util.Log;

        import com.example.shenjack.zhihudailyreader.BasePresenter;
        import com.example.shenjack.zhihudailyreader.data.Detail;
        import com.example.shenjack.zhihudailyreader.data.source.StoryRepository;
        import com.example.shenjack.zhihudailyreader.stoylist.StoryListContract;

        import java.io.Serializable;
        import java.security.KeyStore;

        import io.reactivex.Observable;
        import io.reactivex.Observer;
        import io.reactivex.android.schedulers.AndroidSchedulers;
        import io.reactivex.annotations.NonNull;
        import io.reactivex.disposables.Disposable;

/**
 * Created by ShenJack on 2017/6/17.
 */

public class DetailPresenter  extends BasePresenter implements DetailContract.Presenter{

    private StoryRepository mStoryRepositiry;
    private DetailContract.View mStoryDetailView;
    private Detail mDetail;


    public DetailPresenter(StoryRepository newStoryRepository,DetailContract.View view) {
        mStoryRepositiry = newStoryRepository;
        mStoryDetailView = view;
    }

    @Override
    public void loadStoryDetail(String id) {
        mStoryDetailView.showLoadingIndicator(true);


        Observable<Detail> detailObservable = mStoryRepositiry.getStoryDetail(id);
        Log.d("id",detailObservable.toString());

        detailObservable
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Detail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Detail detail) {
                        mDetail = detail;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mStoryDetailView.showLoadingIndicator(false);
                        mStoryDetailView.showStoryDetail(mDetail);
                    }
                });

        // TODO: 2017/6/17 not processed
    }

    @Override
    protected void onNext(Serializable serializable) {

    }
}
