package dev.jason.gboardpatches.patches.gboard.features.hapticprimitives

import app.morphe.patcher.patch.resourcePatch
import dev.jason.gboardpatches.patches.gboard.features.featureflags.applyFeatureMarker
import dev.jason.gboardpatches.patches.shared.Constants.COMPATIBILITY_GBOARD

internal val gboardHapticPrimitivesFeatureMarkerPatch = resourcePatch(
    description = "標記 Modern keypress haptics feature 已被打入 target APK。",
) {
    compatibleWith(COMPATIBILITY_GBOARD)

    finalize {
        applyFeatureMarker(HAPTIC_PRIMITIVES_FEATURE_MARKER)
    }
}

internal const val HAPTIC_PRIMITIVES_FEATURE_MARKER =
    "dev.jason.gboardpatches.feature.haptic_primitives"
