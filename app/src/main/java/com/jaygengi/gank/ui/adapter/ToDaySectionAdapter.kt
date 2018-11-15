package com.jaygengi.gank.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jaygengi.gank.R
import com.jaygengi.gank.mvp.model.bean.ToDayEntity

/**
 * @description: 获取最新一天的干货适配器
 * @author JayGengi
 * @date  2018/11/14 0014 下午 5:01
 * @email jaygengiii@gmail.com
 */
class ToDaySectionAdapter(data: List<String>?)
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
        if (item == "Android") {
            if (toDayTypeInfo!!.Android != null && toDayTypeInfo!!.Android!!.isNotEmpty()) {
                for (i in 0 until toDayTypeInfo!!.Android!!.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_today, null)
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
    }
}
