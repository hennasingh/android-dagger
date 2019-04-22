package com.coder.dagger_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.coder.dagger_kotlin.data.DataManager
import com.coder.dagger_kotlin.di.component.ActivityComponent
import com.coder.dagger_kotlin.di.component.DaggerActivityComponent
import com.coder.dagger_kotlin.di.module.ActivityModule
import javax.inject.Inject
import com.coder.dagger_kotlin.data.model.User
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent(DaggerApplication.applicationComponent)
            .build()

        activityComponent.inject(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        createUser()
        getUser()
        dataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543")

        val token = dataManager.getAccessToken()
        if (token != null) {
            tv_access_token.text = token
        }
    }

    private fun createUser() {
        try {
            dataManager.createUser(User(name = "Henna", address = "Castle Avenue, Dublin, Ireland"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun getUser() {
        try {
            val user = dataManager.getUser(1)
            tv_user_info.text = user.toString()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
