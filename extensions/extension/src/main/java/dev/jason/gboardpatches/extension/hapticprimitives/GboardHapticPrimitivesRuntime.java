package dev.jason.gboardpatches.extension.hapticprimitives;

import android.content.SharedPreferences;

import dev.jason.gboardpatches.extension.flagsettings.GboardFlagRuntimeContext;

public final class GboardHapticPrimitivesRuntime {
    private GboardHapticPrimitivesRuntime() {
    }

    public static Object applyFlagValue(String flagName, Object stockResult) {
        try {
            return applyFlagValue(
                    flagName,
                    stockResult,
                    GboardFlagRuntimeContext.preferencesOrNull());
        } catch (Throwable ignored) {
            return stockResult;
        }
    }

    static Object applyFlagValue(
            String flagName,
            Object stockResult,
            SharedPreferences preferences) {
        try {
            if (!GboardHapticPrimitivesPolicy.isTargetFlagName(flagName)
                    || !(stockResult instanceof Long)) {
                return stockResult;
            }
            if (preferences == null) {
                return stockResult;
            }
            return GboardHapticPrimitivesPolicy.maybeForce(
                    flagName,
                    stockResult,
                    GboardHapticPrimitivesSettings.readEnabled(preferences));
        } catch (Throwable ignored) {
            return stockResult;
        }
    }
}
