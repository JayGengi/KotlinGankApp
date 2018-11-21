package com.jaygengi.gank.ui.fragment.home

import android.support.v7.widget.GridLayoutManager
import cc.shinichi.library.ImagePreview
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.GirlsContract
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.presenter.GirlsPresenter
import com.jaygengi.gank.net.exception.ErrorStatus
import com.jaygengi.gank.ui.adapter.GirlsAdapter
import kotlinx.android.synthetic.main.fragment_home_common_type.*
import cc.shinichi.library.bean.ImageInfo





/**
   * @description: 福利
   * @author JayGengi
   * @date  2018/11/15 0015 下午 4:59
   * @email jaygengiii@gmail.com
   */

class WelfareFragment : BaseFragment(), GirlsContract.View {
    private var isRefresh = false
    private val imageInfoList: ArrayList<ImageInfo> = ArrayList()

    private var imageInfo = ImageInfo()
    private val mPresenter by lazy { GirlsPresenter() }
    private var grilsList = ArrayList<GirlsEntity.ResultsBean>()
    private val mAdapter by lazy { activity?.let { GirlsAdapter( grilsList) } }
    override fun getLayoutId(): Int = R.layout.fragment_welfare


    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView
        mRefreshLayout.setOnRefreshListener {
            CURRENT_PAGE =1
            loadData()
        }
        mRefreshLayout.setOnLoadMoreListener {
            CURRENT_PAGE++
            loadData()
        }
        recycler.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(context,2)
            adapter = mAdapter
        }
        mAdapter!!.setOnItemClickListener { adapter, _, position ->
            ImagePreview
                    .getInstance()
                    .setContext(context!!)
                    .setIndex(position)// 从第一张图片开始，索引从0开始哦
                    .setFolderName("Gank")// 保存的文件夹名称，SD卡根目录
                    .setImageInfoList(imageInfoList)
                    .start()
        }
    }

    override fun lazyLoad() {
        isRefresh = true
        loadData()
    }
    private fun loadData(){

        mPresenter.requestGirlInfo(PAGE_CAPACITY,CURRENT_PAGE)
    }
    override fun showGirlInfo(dataInfo: GirlsEntity) {
        multipleStatusView?.showContent()
        mAdapter?.run {
            if (dataInfo.results != null && dataInfo.results!!.isNotEmpty()) {
                if (CURRENT_PAGE == 1) {
                    grilsList.clear()
                    imageInfoList.clear()
                }
                grilsList.addAll(dataInfo.results!!)
                for(img : GirlsEntity.ResultsBean in grilsList){
                    imageInfo = ImageInfo()
                    imageInfo.originUrl = img.url
                    imageInfo.thumbnailUrl = img.url
                    imageInfoList.add(imageInfo)
                }
                setNewData(grilsList)
            } else {
                multipleStatusView?.showEmpty()
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()

        } else {
            multipleStatusView?.showError()
        }
    }

    /**
     * 显示 Loading （下拉刷新的时候不需要显示 Loading）
     */
    override fun showLoading() {
        if(isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }
    /**
     * 隐藏 Loading
     */
    override fun dismissLoading() {
        if(mRefreshLayout!=null && mRefreshLayout.isRefreshing){
            mRefreshLayout.finishRefresh()
        }
        if(mRefreshLayout!=null && mRefreshLayout.isLoading){
            mRefreshLayout.finishLoadMore()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}
