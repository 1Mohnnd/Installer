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

package cc.hyperium.installer.ui

import cc.hyperium.installer.ui.stages.*
import javafx.scene.control.TabPane
import javafx.scene.input.KeyCode
import kfoenix.jfxtabpane
import tornadofx.View
import tornadofx.tab

class InstallerView : View("Hyperium Installer") {
    val tabPane = jfxtabpane {
        tab(find<WelcomeStage>())
        tab(find<TargetSelectionStage>())
        tab(find<RamSelectionStage>())
        tab(find<VersionSelectionStage>())
        tab(find<AddonsSelectionStage>())
        add(find<AgreementStage>())
        tab(find<ProgressStage>())
        tab(find<FinishedStage>())

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        setOnKeyPressed {
            if ((it.code == KeyCode.TAB && it.isControlDown) || it.code == KeyCode.LEFT || it.code == KeyCode.RIGHT || it.code == KeyCode.UP || it.code == KeyCode.DOWN)
                it.consume()
        }

        prefWidth = 854.0
        prefHeight = 480.0
    }
    override val root = tabPane

    init {
        icon = resources.imageview("/assets/logo_solid.png", true)
    }
}