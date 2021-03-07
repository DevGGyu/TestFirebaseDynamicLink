package com.project.testinstallreferrer

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }

                val utmSource = deepLink?.getQueryParameter("utm_source")
                val utmCampaign = deepLink?.getQueryParameter("utm_campaign")

                println("utmSource : $utmSource, utmCampaign : $utmCampaign")
            }
            .addOnFailureListener(this) { e -> Log.w("", "getDynamicLink:onFailure", e) }
    }
}