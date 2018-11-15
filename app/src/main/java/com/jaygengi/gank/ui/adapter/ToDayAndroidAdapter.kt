package com.jaygengi.gank.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
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
class ToDayAndroidAdapter(data: List<ToDayEntity.ResultsBean.AndroidBean>?)
    : BaseQuickAdapter<ToDayEntity.ResultsBean.AndroidBean, BaseViewHolder>
        (R.layout.item_home_today, data) {

    override fun convert(helper: BaseViewHolder, item: ToDayEntity.ResultsBean.AndroidBean) {
        helper.setText(R.id.content, item.desc)
                .setText(R.id.time, item.publishedAt)
        val wrapLinear: LinearLayout = helper.getView(R.id.wrap_linear)
        //判断是否有图片
        if (null != item.images) {
            if (item.images!!.isNotEmpty()) {
                wrapLinear.visibility = View.VISIBLE
                if (wrapLinear.childCount > 0) {
                    wrapLinear.removeAllViews()
                }
                for (y in 0 until item.images!!.size) {
                    val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
                    //图片
                    val img: ImageView = mview.findViewById(R.id.img)
                    Glide.with(mContext)
                            .load(item.images!![y])
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
    }
}
