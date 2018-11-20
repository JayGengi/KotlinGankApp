package com.jaygengi.gank.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jaygengi.gank.R
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.ui.activity.WebViewActivity

/**
 * @description: 获取最新一天的干货适配器
 * @author JayGengi
 * @date  2018/11/14 0014 下午 5:01
 * @email jaygengiii@gmail.com
 */
class ToDaySectionAdapter(private val context:Context,data: List<String>?)
    : BaseQuickAdapter<String, BaseViewHolder>
(R.layout.item_today_header, data) {
    private var toDayTypeInfo: ToDayEntity.ResultsBean? = null
    fun setToDayTypeInfo(toDayTypeInfo: ToDayEntity.ResultsBean?) {
        this.toDayTypeInfo = toDayTypeInfo
    }
    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tvHeader, item)
        val todayInfo: LinearLayout = helper.getView(R.id.today_info)

        if (todayInfo.childCount > 0) {
            todayInfo.removeAllViews()
        }
        //TODO 这里先这样，后期改为二元数组或者动态加载【都这么说了，估计auther懒得改了，手动滑稽】
        if (item == "Android") {
            if (toDayTypeInfo!!.Android != null && toDayTypeInfo!!.Android!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.Android!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.Android!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.Android!![i].url)
                        mContext.startActivity(intent)
                    }
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.Android!![i].desc
                    time.text = toDayTypeInfo!!.Android!![i].publishedAt

                    //判断是否有图片
                    if (null != toDayTypeInfo!!.Android!![i].images) {
                        if (toDayTypeInfo!!.Android!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.Android!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.Android!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }

        if (item == "App") {
            if (toDayTypeInfo!!.App != null && toDayTypeInfo!!.App!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.App!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.App!![i].desc
                    time.text = toDayTypeInfo!!.App!![i].publishedAt
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.App!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.App!![i].url)
                        mContext.startActivity(intent)
                    }
                    //判断是否有图片
                    if (null != toDayTypeInfo!!.App!![i].images) {
                        if (toDayTypeInfo!!.App!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.App!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.App!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }

        if (item == "iOS") {
            if (toDayTypeInfo!!.iOS != null && toDayTypeInfo!!.iOS!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.iOS!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.iOS!![i].desc
                    time.text = toDayTypeInfo!!.iOS!![i].publishedAt
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.iOS!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.iOS!![i].url)
                        mContext.startActivity(intent)
                    }
                    //判断是否有图片
                    if (null != toDayTypeInfo!!.iOS!![i].images) {
                        if (toDayTypeInfo!!.iOS!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.iOS!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.iOS!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }

        if (item == "休息视频") {
            if (toDayTypeInfo!!.休息视频 != null && toDayTypeInfo!!.休息视频!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.休息视频!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.休息视频!![i].desc
                    time.text = toDayTypeInfo!!.休息视频!![i].publishedAt
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.休息视频!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.休息视频!![i].url)
                        mContext.startActivity(intent)
                    }
                    //判断是否有图片
                    if (null != toDayTypeInfo!!.休息视频!![i].images) {
                        if (toDayTypeInfo!!.休息视频!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.休息视频!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.休息视频!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }

        if (item == "前端") {
            if (toDayTypeInfo!!.前端 != null && toDayTypeInfo!!.前端!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.前端!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.前端!![i].desc
                    time.text = toDayTypeInfo!!.前端!![i].publishedAt
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.前端!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.前端!![i].url)
                        mContext.startActivity(intent)
                    }
                    //判断是否有图片
                    if (null != toDayTypeInfo!!.前端!![i].images) {
                        if (toDayTypeInfo!!.前端!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.前端!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.前端!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }

        if (item == "拓展资源") {
            if (toDayTypeInfo!!.拓展资源 != null && toDayTypeInfo!!.拓展资源!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.拓展资源!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.拓展资源!![i].desc
                    time.text = toDayTypeInfo!!.拓展资源!![i].publishedAt
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.拓展资源!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.拓展资源!![i].url)
                        mContext.startActivity(intent)
                    }
                    //判断是否有图片
                    if (null != toDayTypeInfo!!.拓展资源!![i].images) {
                        if (toDayTypeInfo!!.拓展资源!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.拓展资源!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.拓展资源!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }

        if (item == "瞎推荐") {
            if (toDayTypeInfo!!.瞎推荐 != null && toDayTypeInfo!!.瞎推荐!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.瞎推荐!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.瞎推荐!![i].desc
                    time.text = toDayTypeInfo!!.瞎推荐!![i].publishedAt
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.瞎推荐!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.瞎推荐!![i].url)
                        mContext.startActivity(intent)
                    }
                    //判断是否有图片
                    if (null != toDayTypeInfo!!.瞎推荐!![i].images) {
                        if (toDayTypeInfo!!.瞎推荐!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.瞎推荐!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.瞎推荐!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }

        if (item == "福利") {
            if (toDayTypeInfo!!.福利 != null && toDayTypeInfo!!.福利!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.福利!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
                    val title: TextView = view.findViewById(R.id.content)
                    val time: TextView = view.findViewById(R.id.time)
                    val wrapLinear: LinearLayout = view.findViewById(R.id.wrap_linear)
                    title.text = toDayTypeInfo!!.福利!![i].desc
                    time.text = toDayTypeInfo!!.福利!![i].publishedAt
                    val itemLayout: RelativeLayout = view.findViewById(R.id.list_item)
                    itemLayout.setOnClickListener {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("title", toDayTypeInfo!!.福利!![i].desc)
                        intent.putExtra("url", toDayTypeInfo!!.福利!![i].url)
                        mContext.startActivity(intent)
                    }
                    //判断是否有图片
                    if (null != toDayTypeInfo!!.福利!![i].images) {
                        if (toDayTypeInfo!!.福利!![i].images!!.isNotEmpty()) {
                            wrapLinear.visibility = View.VISIBLE
                            if (wrapLinear.childCount > 0) {
                                wrapLinear.removeAllViews()
                            }
                            for (y in 0 until toDayTypeInfo!!.福利!![i].images!!.size) {
                                val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                                //图片
                                val img: ImageView = mview.findViewById(R.id.img)
                                Glide.with(mContext)
                                        .load(toDayTypeInfo!!.福利!![i].images!![y])
                                        .into(img)
                                wrapLinear.addView(mview)
                                if(y ==3){
                                    break
                                }
                            }
                        }

                    } else {
                        wrapLinear.visibility = View.GONE
                    }
                    todayInfo.addView(view)
                }
            }
        }
    }

}

