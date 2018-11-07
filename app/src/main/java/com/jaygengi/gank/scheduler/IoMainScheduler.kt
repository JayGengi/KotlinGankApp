package com.jaygengi.gank.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by xuhao on 2017/11/17.
 * desc:
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())
