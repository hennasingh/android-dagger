package com.coder.dagger_kotlin.data

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import android.R.id.edit


@Singleton
class SharedPrefsHelper @Inject constructor(var sharedPrefs: SharedPreferences) {

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "access_token"
    }

    fun put(key: String, value: String) = sharedPrefs.edit().putString(key, value).apply()


    fun get(key: String, defaultValue: String?): String? = sharedPrefs.getString(key, defaultValue)


    fun deleteSavedData(key: String) = sharedPrefs.edit().remove(key).apply()

}