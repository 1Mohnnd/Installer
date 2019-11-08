/*
 * Copyright © 2019 by Sk1er LLC
 *
 * All rights reserved.
 *
 * Sk1er LLC
 * 444 S Fulton Ave
 * Mount Vernon, NY
 * sk1er.club
 */

package cc.hyperium.installer.backend.config

import cc.hyperium.installer.shared.utils.MinecraftUtils
import cc.hyperium.installer.shared.utils.VersionUtils
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class CLIConfig(parser: ArgParser) : Config {
    override val ram by parser.storing("-r", "--ram", help = "Amount of ram (GB) [default: 2]") { toInt() }.default(2)
    override val path by parser.storing("-o", "--path", help = "Minecraft path [default: autodetect]")
        .default { MinecraftUtils.getMinecraftDir().canonicalPath }
    override val optifine by parser.flagging("--optifine", help = "Install Optifine")
    override val version by parser.storing("-v", "--version", help = "Version build number [default: latest beta]") {
        (VersionUtils.versionsManifest ?: throw IllegalStateException("Failed to fetch version manifest"))
            .versions.find { it.build == this } ?: throw IllegalArgumentException("Version not found")
    }.default {
        VersionUtils.versionsManifest?.latestBeta ?: throw IllegalStateException("Failed to fetch version manifest")
    }
    override val addons = emptyMap<String, Boolean>()
    override val forceGui by parser.flagging("-g", "--gui", "--forceGui", help = "Force GUI mode, regardless of support. All CLI options will be ignored.")
}