package com.jaygengi.gank

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.jaygengi.gank.BuildConfig
import com.jaygengi.gank.showToast
import com.jaygengi.gank.utils.DisplayManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates
import io.rong.imkit.RongIM
import io.rong.imkit.utils.SystemUtils.getCurProcessName
import io.rong.imlib.RongIMClient

/**
 * Created by xuhao on 2017/11/16.
 *
 */

class MyApplication : MultiDexApplication(){
    val token ="sw5H+Y2WXaf9OaPxU7clB9AhAEzPNP4ZKGx97JmQuqwiIHKp/QJUn0O5t+oh27owu0iA54ph22pWC/TrLmtszw=="
    val token1 ="3zrnAruSvbcD1fnyi/z+WYPDSM9Iv1R0J8N4kD1XKGfJtoP+Kv1mX8XdAGFQfxPYBwbhE4SdLuePKohKPIeSFw=="
    private var refWatcher: RefWatcher? = null

    companion object {

        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
//        refWatcher = setupLeakCanary()
        /**
         * OnCreate 会被多个进程重入，这段保护代码，
         * 确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (applicationInfo.packageName == getCurProcessName(applicationContext) || "io.rong.push" == getCurProcessName(applicationContext)) {
            //IMKit SDK调用第一步 初始化
            RongIM.init(this)
            initRongIM(token)
        }
        initConfig()
        DisplayManager.init(this)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)


    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }
    /**
     * 连接服务器，在整个应用程序全局，只需要调用一次，需在 [.init] 之后调用。
     *
     * 如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @param callback 连接回调。
     * @return RongIM  客户端核心类的实例。
     */
    private fun initRongIM(token: String) {
        RongIM.connect(token, object : RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            override fun onTokenIncorrect() {
                Log.d("LoginActivity", "onTokenIncorrect")
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            override fun onSuccess(userid: String) {
                showToast(userid)
                Log.d("LoginActivity", "--onSuccess$userid")
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            override fun onError(errorCode: RongIMClient.ErrorCode) {
                Log.d("LoginActivity", "onError")
            }
        })
    }

    /**
     * 初始化配置
     */
    private fun initConfig() {

        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 隐藏线程信息 默认：显示
                .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("hao_zz")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }


}
