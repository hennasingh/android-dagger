package com.coder.dagger_kotlin.data

import android.content.Context
import android.content.res.Resources
import com.coder.dagger_kotlin.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import android.content.res.Resources.NotFoundException
import com.coder.dagger_kotlin.data.model.User


@Singleton
class DataManager @Inject constructor(
    @ApplicationContext var context: Context,
    var dbHelper: DbHelper,
    var sharedPrefHelper: SharedPrefsHelper
) {

    fun saveAccessToken(accessToken: String) =
        sharedPrefHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken)


    fun getAccessToken() = sharedPrefHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null)


    @Throws(Exception::class)
    fun createUser(user: User) = dbHelper.insertUser(user)


    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long): User = dbHelper.getUser(userId)


}