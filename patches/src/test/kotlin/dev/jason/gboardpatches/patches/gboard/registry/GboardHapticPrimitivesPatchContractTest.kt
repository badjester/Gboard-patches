package dev.jason.gboardpatches.patches.gboard.registry

import dev.jason.gboardpatches.patches.gboard.features.hapticprimitives.HAPTIC_PRIMITIVES_FEATURE_MARKER
import dev.jason.gboardpatches.patches.gboard.features.hapticprimitives.gboardHapticPrimitivesFeatureMarkerPatch
import dev.jason.gboardpatches.patches.gboard.features.hapticprimitives.gboardHapticPrimitivesFlagValuePatch
import dev.jason.gboardpatches.patches.gboard.shared.gboardPatchesSettingsPatch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GboardHapticPrimitivesPatchContractTest {
    @Test
    fun publicPatchIsIndependentAndExact1803Only() {
        val patch = gboardHapticPrimitivesPatch
        assertEquals("Modern keypress haptics", patch.name)
        assertEquals(HAPTIC_PRIMITIVES_DESCRIPTION, patch.description)
        assertTrue(patch.default)
        assertEquals(
            listOf(
                gboardPatchesSettingsPatch,
                gboardHapticPrimitivesFeatureMarkerPatch,
                gboardHapticPrimitivesFlagValuePatch,
            ),
            patch.dependencies.toList(),
        )
        assertFalse(patch.dependencies.any { dependency ->
            dependency.toString().contains("AdvancedVoice", ignoreCase = true) ||
                dependency.toString().contains("LongPressQuickActions", ignoreCase = true)
        })
        assertEquals(1, gboardHapticPrimitivesFlagValuePatch.dependencies.size)
        assertEquals(
            "18.0.3.954559732-release-arm64-v8a",
            patch.compatibility!!.single().targets.single().version,
        )
        assertEquals(
            "dev.jason.gboardpatches.feature.haptic_primitives",
            HAPTIC_PRIMITIVES_FEATURE_MARKER,
        )
    }

    @Test
    fun generatedInventoryContainsExactlyOneHapticPrimitivesRowWithoutOptions() {
        val rows = generatedPublishedPatches()
            .filter { it.get("name").asString == "Modern keypress haptics" }

        assertEquals(1, rows.size)
        val row = rows.single()
        assertEquals(HAPTIC_PRIMITIVES_DESCRIPTION, row.get("description").asString)
        assertTrue(row.get("use").asBoolean)
        assertEquals(0, row.getAsJsonArray("options").size())
        assertEquals(
            listOf("18.0.3.954559732-release-arm64-v8a"),
            row.getAsJsonObject("compatiblePackages")
                .getAsJsonArray("com.google.android.inputmethod.latin")
                .map { it.asString },
        )
    }

    private companion object {
        const val HAPTIC_PRIMITIVES_DESCRIPTION =
            "使用 Android 的觸覺圖元（haptic primitives）進行按鍵反饋，取代傳統震動。\n" +
                "Uses Android's haptic primitives for keypresses — the crisp tick the rest of the system uses — instead of a plain buzz."
    }
}
