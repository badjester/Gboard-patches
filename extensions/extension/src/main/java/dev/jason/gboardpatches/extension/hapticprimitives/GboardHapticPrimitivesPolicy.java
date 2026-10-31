package dev.jason.gboardpatches.extension.hapticprimitives;

public final class GboardHapticPrimitivesPolicy {
    public static final String VIBRATION_EFFECT_MIN_SDK_FLAG =
            "vibration_effect_min_sdk";
    public static final long COMPOSITION_MIN_SDK = 30L;

    private GboardHapticPrimitivesPolicy() {
    }

    public static boolean isTargetFlagName(String flagName) {
        return VIBRATION_EFFECT_MIN_SDK_FLAG.equals(flagName);
    }

    public static Object maybeForce(String flagName, Object stockResult, boolean enabled) {
        if (!enabled || !isTargetFlagName(flagName) || !(stockResult instanceof Long)) {
            return stockResult;
        }
        return Long.valueOf(COMPOSITION_MIN_SDK);
    }
}
