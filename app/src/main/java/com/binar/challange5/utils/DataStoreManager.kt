package com.binar.challange5.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context : Context) {

    suspend fun saveUser(id : Int){
        context.dataStoreUser.edit { pref->
            pref[ID_KEY] = id
        }
    }

    fun getIdUser ():Flow<Int>{
        return context.dataStoreUser.data.map { pref->
            pref[ID_KEY] ?:0
        }
    }

    suspend fun logoutUser() {
        context.dataStoreUser.edit { pref ->
            pref.remove(ID_KEY)
        }
    }

    companion object{
        private const val DATASTORE_NAME = "user"
        private val Context.dataStoreUser by preferencesDataStore(name = DATASTORE_NAME)

        private val ID_KEY = intPreferencesKey("idKey")
    }
}