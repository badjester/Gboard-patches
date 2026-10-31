package dev.jason.gboardpatches.extension.hapticprimitives;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import dev.jason.gboardpatches.extension.settings.GboardPatchesSettingsContract;

public final class GboardHapticPrimitivesSettingsFeatureTest {
    @Test
    public void flatScreenShowsOneDefaultOnToggleWithExactCopy() {
        GboardHapticPrimitivesSettingsFeature feature =
                new GboardHapticPrimitivesSettingsFeature(null);

        GboardPatchesSettingsContract.Screen screen = feature.buildScreenForState(
                true,
                value -> {
                });

        Assert.assertEquals("Modern keypress haptics", feature.getEntryTitle());
        Assert.assertEquals(
                "Uses Android's haptic primitives for keypresses instead of a plain buzz.",
                feature.getEntrySummary());
        Assert.assertEquals("Modern keypress haptics", screen.getToolbarTitle());
        Assert.assertEquals("Modern keypress haptics", screen.getHeaderTitle());
        Assert.assertEquals("", screen.getHeaderSummary());
        Assert.assertEquals(
                GboardPatchesSettingsContract.PanelStyle.FLAT,
                screen.getPanelStyle());
        Assert.assertEquals(1, screen.getSections().size());

        List<GboardPatchesSettingsContract.Row> rows = screen.getRows();
        Assert.assertEquals(1, rows.size());
        Assert.assertTrue(rows.get(0) instanceof GboardPatchesSettingsContract.ToggleRow);
        GboardPatchesSettingsContract.ToggleRow toggleRow =
                (GboardPatchesSettingsContract.ToggleRow) rows.get(0);
        Assert.assertEquals("Modern keypress haptics", toggleRow.getTitle());
        Assert.assertEquals(
                "Uses Android's haptic primitives for keypresses instead of a plain buzz.",
                toggleRow.getSummary());
        Assert.assertTrue(toggleRow.isChecked());
        Assert.assertTrue(toggleRow.isEnabled());
    }
}
