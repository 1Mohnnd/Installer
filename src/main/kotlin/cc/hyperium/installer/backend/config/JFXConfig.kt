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

import cc.hyperium.installer.shared.entities.version.Version
import cc.hyperium.installer.shared.utils.MinecraftUtils
import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.PropertyDelegate

object JFXConfig : Config {
    val advancedProperty = SimpleBooleanProperty(false)
    val ramProperty = SimpleIntegerProperty(2)
    val pathProperty = SimpleObjectProperty(MinecraftUtils.getMinecraftDir().canonicalPath)
    val optifineProperty = SimpleBooleanProperty(false)
    val versionProperty = SimpleObjectProperty<Version>()

    override var advanced by PropertyDelegate(advancedProperty)
    override var ram by PropertyDelegate(ramProperty)
    override var path by PropertyDelegate(pathProperty)
    override var optifine by PropertyDelegate(optifineProperty)
    override var version by PropertyDelegate(versionProperty)
    override val addons = mutableMapOf<String, BooleanProperty>()
}