package com.jaygengi.gank.mvp.model

import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.net.RetrofitManager
import com.jaygengi.gank.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
   * @description: 获取最新一天的干货数据模型
   * @author JayGengi
   * @date  2018/11/14 0014 下午 4:33
   * @email jaygengiii@gmail.com
   */
class ToDayModel {


    /**
     * 获取福利数据信息
     */
    fun getToDayInfo(): Observable<ToDayEntity> {
        return RetrofitManager.service.getToDayInfo()
                .compose(SchedulerUtils.ioToMain())
    }
}