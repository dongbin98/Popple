package com.dongbin.popple

import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import com.kakao.sdk.common.KakaoSdk
import com.naver.maps.map.NaverMapSdk
import com.navercorp.nid.NaverIdLoginSDK

class GlobalApplication : Application() {
    init {
        instance = this
    }

    var id: Int? = null
    var account: String? = null
    var name: String? = null
    var nickname: String? = null
    var loginType: String? = null
    var accessToken: String? = null

    override fun onCreate() {
        super.onCreate()

        /* get metadata for api key */
        val metadata = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getApplicationInfo(
                packageName,
                PackageManager.ApplicationInfoFlags.of(PackageManager.GET_META_DATA.toLong())
            ).metaData
        } else {
            packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA).metaData
        }

        /* Naver Login Module Init */
        NaverIdLoginSDK.initialize(
            this,
            metadata.getString("com.dongbin.popple.naverSdkId").toString(),
            metadata.getString("com.dongbin.popple.naverSdkSecret").toString(),
            metadata.getString("com.dongbin.popple.naverSdkName").toString()
        )

        /* Naver Map Module Init */
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient(
            metadata.getString("com.naver.maps.map.CLIENT_ID").toString()
        )

        /* Kakao Login Module Init */
        KakaoSdk.init(this, metadata.getString("com.dongbin.popple.kakaoSdkKey").toString())
    }

    fun clearUserInfo() {
        id = null
        account = null
        name = null
        nickname = null
        loginType = null
        accessToken = null
    }

    companion object {
        lateinit var instance: GlobalApplication
    }
}