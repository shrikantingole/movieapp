package com.network.shared.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import timber.log.Timber

fun Context.shareOnMail(code: String) {
    val emailIntent = Intent(
        Intent.ACTION_SENDTO, Uri.fromParts(
            "mailto", "", null
        )
    )
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contest Code Share")
    emailIntent.putExtra(Intent.EXTRA_TEXT, code)
    startActivity(Intent.createChooser(emailIntent, "Send email..."))
}

fun Context.shareTextOn(code: String, shareBy: String): String {
    val whatsappIntent = Intent(Intent.ACTION_SEND)
    whatsappIntent.type = "text/plain"
    whatsappIntent.setPackage(shareBy)
    whatsappIntent.putExtra(Intent.EXTRA_TEXT, code);
    return try {
        startActivity(whatsappIntent)
        "Share to"
    } catch (ex: ActivityNotFoundException) {
        Timber.e(ex)
        Toast.makeText(this, "Not Installed", Toast.LENGTH_SHORT).show()
        "Not Installed"
    }
}

