package com.jaygengi.gank.ui.adapter

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jaygengi.gank.R
import com.jaygengi.gank.mvp.model.bean.CategoryEntity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @description: 获取最新一天的干货适配器
 * @author JayGengi
 * @date  2018/11/14 0014 下午 5:01
 * @email jaygengiii@gmail.com
 */
class ToDayAndroidAdapter(data: List<CategoryEntity.ResultsBean>?)
    : BaseQuickAdapter<CategoryEntity.ResultsBean, BaseViewHolder>
        (R.layout.item_category_info, data) {

    @SuppressLint("SimpleDateFormat")
    override fun convert(helper: BaseViewHolder, item: CategoryEntity.ResultsBean) {

        val sf = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = sf.parse(item.publishedAt)
            val calendar = Calendar.getInstance()
            calendar.time = date
            val time = calendar.get(Calendar.YEAR).toString()+"-"+(calendar.get(Calendar.MONTH) + 1)+"-"+Calendar.DAY_OF_MONTH
            helper.setText(R.id.content, item.desc)
                    .setText(R.id.auther, "auther: "+item.who)
                    .setText(R.id.time,time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}
//判断是否有图片
//if (null != item.images) {
//    if (item.images!!.isNotEmpty()) {
//        wrapLinear.visibility = View.VISIBLE
//        if (wrapLinear.childCount > 0) {
//            wrapLinear.removeAllViews()
//        }
//        for (y in 0 until item.images!!.size) {
//            val mview = LayoutInflater.from(mContext).inflate(R.layout.item_home_today_img, null)
//            //图片
//            val img: ImageView = mview.findViewById(R.id.img)
//            Glide.with(mContext)
//                    .load(item.images!![y])
//                    .into(img)
//            wrapLinear.addView(mview)
//            if(y ==3){
//                break
//            }
//        }
//    }
//
//} else {
//    wrapLinear.visibility = View.GONE
//}