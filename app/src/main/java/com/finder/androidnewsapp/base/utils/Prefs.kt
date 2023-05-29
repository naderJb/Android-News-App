package com.finder.androidnewsapp.base.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class Prefs @Inject constructor(context: Context) {
    companion object {
        private var sp: SharedPreferences? = null
        private var masterKey: MasterKey? = null

        private fun getInstance(context: Context): SharedPreferences {
            synchronized(this) {
                masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()
                sp = EncryptedSharedPreferences.create(
                    context,
                    "secret_shared_prefs",
                    masterKey!!,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
                return sp!!
            }
        }

        const val PREF_LAST_SYNC = "PREF_LAST_SYNC"
    }

    init {
        getInstance(context)
    }

    var lastSync: String?
        get() = sp?.getString(PREF_LAST_SYNC, "")
        set(value) {
            sp?.edit()?.putString(PREF_LAST_SYNC, value)?.apply()
        }
}