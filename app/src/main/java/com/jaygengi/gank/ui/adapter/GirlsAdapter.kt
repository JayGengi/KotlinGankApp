package com.jaygengi.gank.ui.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jaygengi.gank.R
import com.jaygengi.gank.mvp.model.bean.GirlsEntity

/**
 * @description: 美女福利适配器
 * @author JayGengi
 * @date  2018/11/14 0014 下午 5:01
 * @email jaygengiii@gmail.com
 */
class GirlsAdapter(data: List<GirlsEntity.ResultsBean>?)
    : BaseQuickAdapter<GirlsEntity.ResultsBean, BaseViewHolder>
(R.layout.item_grils_info, data) {

    override fun convert(helper: BaseViewHolder, item: GirlsEntity.ResultsBean) {
        //Glide 加载图片简单用法
        Glide.with(mContext).load(item.url).into(helper.getView(R.id.rental_image))
    }
}