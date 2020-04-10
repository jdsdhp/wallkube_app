package cu.jesusd0897.wallkube.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.util.KNav

private const val SPLASH_DURATION = 1500L

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        val shape = findViewById<View>(R.id.splash_logo)
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = 1200
        val animation = AnimationSet(false)
        animation.addAnimation(fadeIn)
        shape.animation = animation
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            KNav.navToMain(this)
            finish()
        }, SPLASH_DURATION)
    }

}