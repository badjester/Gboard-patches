package dev.jason.gboardpatches.patches.gboard.features.hapticprimitives

import dev.jason.gboardpatches.patches.gboard.shared.GboardFlagFamilyFeature
import dev.jason.gboardpatches.patches.gboard.shared.gboardFlagFamilyFeaturePatch

internal val gboardHapticPrimitivesFlagValuePatch = gboardFlagFamilyFeaturePatch(
    description = "在 vibration_effect_min_sdk flag 的 stock result 後套用 feature 設定。",
    feature = GboardFlagFamilyFeature.MODERN_KEYPRESS_HAPTICS,
)
