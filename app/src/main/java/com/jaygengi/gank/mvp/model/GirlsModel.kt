package com.jaygengi.gank.mvp.model

import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.net.BaseResponse
import com.jaygengi.gank.net.RetrofitManager
import com.jaygengi.gank.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
   * @description: 福利数据模型
   * @author JayGengi
   * @date  2018/11/13 0013 下午 3:35
   * @email jaygengiii@gmail.com
   */
class GirlsModel {


    /**
     * 获取福利数据信息
     */
    fun getGirlsInfo(limit: Int,page: Int): Observable<GirlsEntity> {
        return RetrofitManager.service.getGirlsInfo(limit,page)
                .compose(SchedulerUtils.ioToMain())
    }
}