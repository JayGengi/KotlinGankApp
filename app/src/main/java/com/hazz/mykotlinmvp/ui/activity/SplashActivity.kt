package com.hazz.mykotlinmvp.ui.activity

import android.Manifest
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import com.hazz.mykotlinmvp.R
import com.hazz.mykotlinmvp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions


/**
   * @description: 启动页
   * @author JayGengi
   * @date  2018/10/29 0029 上午 11:56
   * @email jaygengiii@gmail.com
   */

class SplashActivity : BaseActivity() {



    private var alphaAnimation:AlphaAnimation?=null


    override fun layoutId(): Int = R.layout.activity_splash

    override fun initData() {

    }

    override fun initView() {

        //渐变展示启动屏
        alphaAnimation= AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }
            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })

        checkPermission()
    }
    override fun start() {

    }

    fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission(){
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "KotlinMvp应用需要以下权限，请允许", 0, *perms)

    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                        && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }


}