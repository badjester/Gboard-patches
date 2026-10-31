package dev.jason.gboardpatches.extension.hapticprimitives;

import android.content.Context;
import android.content.SharedPreferences;

import dev.jason.gboardpatches.extension.R;
import dev.jason.gboardpatches.extension.flagsettings.GboardBooleanFlagSettingsFeature;
import dev.jason.gboardpatches.extension.settings.GboardPatchesFeatureAvailability;
import dev.jason.gboardpatches.extension.settings.GboardPatchesSettingsContract;
import dev.jason.gboardpatches.extension.settings.GboardSettingsText;

public final class GboardHapticPrimitivesSettingsFeature
        extends GboardBooleanFlagSettingsFeature {

    public GboardHapticPrimitivesSettingsFeature(Context context) {
        super(
                GboardPatchesFeatureAvailability.FEATURE_HAPTIC_PRIMITIVES,
                text(context, R.string.gboard_patches_modern_keypress_haptics_title),
                text(context, R.string.gboard_patches_modern_keypress_haptics_summary),
                text(context, R.string.gboard_patches_modern_keypress_haptics_title),
                text(context, R.string.gboard_patches_header_badge),
                text(context,
                        R.string.gboard_patches_modern_keypress_haptics_error_title),
                text(context,
                        R.string.gboard_patches_modern_keypress_haptics_error_summary),
                text(context,
                        R.string.gboard_patches_modern_keypress_haptics_section_feature),
                new GboardPatchesSettingsContract.PreviewSpec(
                        text(context,
                                R.string.gboard_patches_modern_keypress_haptics_title),
                        text(context,
                                R.string.gboard_patches_modern_keypress_haptics_summary),
                        GboardPatchesSettingsContract.PreviewLayout.STACKED,
                        java.util.Collections.emptyList()),
                new SettingsStore() {
                    @Override
                    public void ensureDefault(SharedPreferences preferences) {
                        GboardHapticPrimitivesSettings.ensureDefault(preferences);
                    }

                    @Override
                    public boolean readEnabled(SharedPreferences preferences) {
                        return GboardHapticPrimitivesSettings.readEnabled(preferences);
                    }

                    @Override
                    public boolean writeEnabled(
                            SharedPreferences preferences,
                            boolean enabled) {
                        return GboardHapticPrimitivesSettings
                                .writeEnabled(preferences, enabled);
                    }
                });
    }

    private static String text(Context context, int resourceId) {
        return GboardSettingsText.get(context, resourceId);
    }
}
