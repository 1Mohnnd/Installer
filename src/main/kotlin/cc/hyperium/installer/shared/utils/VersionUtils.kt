/*
 * Copyright © 2020 by Sk1er LLC
 *
 * All rights reserved.
 *
 * Sk1er LLC
 * 444 S Fulton Ave
 * Mount Vernon, NY
 * sk1er.club
 */

package cc.hyperium.installer.shared.utils

import cc.hyperium.installer.shared.entities.addon.AddonsManifest
import cc.hyperium.installer.shared.entities.version.VersionManifest
import com.google.gson.Gson
import java.net.URL

object VersionUtils {
    private val gson = Gson()
    val addonsManifest by lazy {
        runCatching {
            gson.fromJson(
                get("https://raw.githubusercontent.com/HyperiumClient/Hyperium-Repo/master/installer/addons.json"),
                AddonsManifest::class.java
            )
        }.getOrNull()
    }
    val versionsManifest by lazy {
        runCatching {
            gson.fromJson(
                get("https://api.hyperium.cc/versions"),
                VersionManifest::class.java
            )
        }.getOrNull()
    }

    init {
        System.setProperty("http.agent", "Mozilla/5.0 (Hyperium Installer)")
    }

    fun isLocal() = javaClass.getResource("/assets/client.bin") != null

    fun get(url: String) = URL(url).readText()
}