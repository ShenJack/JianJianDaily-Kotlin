package com.example.shenjack.zhihudailyreader.extention

import android.content.Context
import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.example.shenjack.zhihudailyreader.R

/**
 * Created by shenjack on 17-9-11.
 */

class HeaderAndFooterWrapper : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private val mContext: Context
    private val mInnerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    constructor(mContext: Context, innerAdapter :  RecyclerView.Adapter<in RecyclerView.ViewHolder>) : super() {
        this.mContext = mContext
        this.mInnerAdapter = innerAdapter
        this.mHeaderViews = SparseArrayCompat<View>()
        this.mFootViews = SparseArrayCompat<View>()
    }

    internal var loadingView: View? = null


    var mHeaderViews: SparseArrayCompat<View>
    var mFootViews: SparseArrayCompat<View>

    val headersCount: Int
        get() = mHeaderViews.size()

    val footersCount: Int
        get() = mFootViews.size()

    private val realItemCount: Int
        get() = mInnerAdapter.itemCount

    private fun isHeaderViewPos(position: Int): Boolean {
        return position < headersCount
    }

    private fun isFooterViewPos(position: Int): Boolean {
        return position >= headersCount + realItemCount
    }


    fun addHeaderView(view: View) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view)
    }

    fun addFootView(view: View?) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (mHeaderViews.get(viewType) != null) {
            return BannerHolder(mHeaderViews.get(viewType))

        } else if (mFootViews.get(viewType) != null) {
            return BannerHolder(mFootViews.get(viewType))
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position)
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - headersCount - realItemCount)
        }
        return mInnerAdapter.getItemViewType(position - headersCount)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isHeaderViewPos(position)) {
            return
        }
        if (isFooterViewPos(position)) {
            return
        }
        mInnerAdapter.onBindViewHolder(holder, position - headersCount)
    }

    override fun getItemCount(): Int {
        return headersCount + footersCount + realItemCount
    }

    //    @Override
    //    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder)
    //    {
    ////        mInnerAdapter.onViewAttachedToWindow(holder);
    //        int position = holder.getLayoutPosition();
    //        if (isHeaderViewPos(position) || isFooterViewPos(position))
    //        {
    //            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
    //
    //            if (lp != null
    //                    && lp instanceof StaggeredGridLayoutManager.LayoutParams)
    //            {
    //
    //                StaggeredGridLayoutManager.LayoutParams p =
    //                        (StaggeredGridLayoutManager.LayoutParams) lp;
    //
    //                p.setFullSpan(true);
    //            }
    //        }
    //    }

    //    @Override
    //    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    //    {
    //        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    //
    //        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    //        if (layoutManager instanceof GridLayoutManager)
    //        {
    //            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
    //            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
    //
    //            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
    //            {
    //                @Override
    //                public int getSpanSize(int position)
    //                {
    //                    int viewType = getItemViewType(position);
    //                    if (mHeaderViews.get(viewType) != null)
    //                    {
    //                        return gridLayoutManager.getSpanCount();
    //                    } else if (mFootViews.get(viewType) != null)
    //                    {
    //                        return gridLayoutManager.getSpanCount();
    //                    }
    //                    if (spanSizeLookup != null)
    //                        return spanSizeLookup.getSpanSize(position);
    //                    return 1;
    //                }
    //            });
    //            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
    //        }
    //    }

    private inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun removeHeaders() {
        mHeaderViews.clear()
        //        notifyDataSetChanged();
    }

    fun removeFooters() {
        mFootViews.clear()
        //        notifyDataSetChanged();
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            if (loadingView == null) {
                loadingView = View.inflate(mContext, R.layout.recyclerview_loading_layout, null)
//                addFootView(loadingView)
                notifyItemInserted(itemCount - 1)
            } else {
                loadingView!!.visibility = View.VISIBLE
            }
        } else {
            if (loadingView != null) {
                loadingView!!.visibility = View.GONE
            }
        }
    }

    companion object {
        private val BASE_ITEM_TYPE_HEADER = 100000
        private val BASE_ITEM_TYPE_FOOTER = 200000
    }


}