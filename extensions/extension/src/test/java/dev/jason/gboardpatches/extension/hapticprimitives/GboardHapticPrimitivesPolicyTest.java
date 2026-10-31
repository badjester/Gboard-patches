package dev.jason.gboardpatches.extension.hapticprimitives;

import org.junit.Assert;
import org.junit.Test;

public final class GboardHapticPrimitivesPolicyTest {
    @Test
    public void forcesCompositionMinSdkWhenEnabled() {
        Assert.assertEquals(
                "vibration_effect_min_sdk",
                GboardHapticPrimitivesPolicy.VIBRATION_EFFECT_MIN_SDK_FLAG);
        Assert.assertEquals(
                30L,
                GboardHapticPrimitivesPolicy.COMPOSITION_MIN_SDK);

        Assert.assertEquals(
                Long.valueOf(30L),
                GboardHapticPrimitivesPolicy.maybeForce(
                        "vibration_effect_min_sdk",
                        Long.valueOf(1024L),
                        true));
        Assert.assertEquals(
                Long.valueOf(1024L),
                GboardHapticPrimitivesPolicy.maybeForce(
                        "vibration_effect_min_sdk",
                        Long.valueOf(1024L),
                        false));
        Assert.assertEquals(
                "1024",
                GboardHapticPrimitivesPolicy.maybeForce(
                        "vibration_effect_min_sdk",
                        "1024",
                        true));
        Assert.assertEquals(
                Long.valueOf(1024L),
                GboardHapticPrimitivesPolicy.maybeForce(
                        "unrelated_flag",
                        Long.valueOf(1024L),
                        true));
        Assert.assertNull(
                GboardHapticPrimitivesPolicy.maybeForce(
                        "vibration_effect_min_sdk",
                        null,
                        true));
    }
}
