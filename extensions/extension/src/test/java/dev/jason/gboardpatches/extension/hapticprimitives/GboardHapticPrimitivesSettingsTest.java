package dev.jason.gboardpatches.extension.hapticprimitives;

import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class GboardHapticPrimitivesSettingsTest {
    @Test
    public void ownsExpectedPreferenceKeyAndDefaultsOn() {
        TestSharedPreferences preferences = new TestSharedPreferences();

        Assert.assertEquals(
                "pref_modern_keypress_haptics_enabled",
                GboardHapticPrimitivesSettings.PREF_KEY_ENABLED);
        Assert.assertTrue(GboardHapticPrimitivesSettings.DEFAULT_ENABLED);
        Assert.assertTrue(GboardHapticPrimitivesSettings.readEnabled(preferences));
        Assert.assertFalse(GboardHapticPrimitivesSettings.readEnabled(
                (SharedPreferences) null));
    }

    @Test
    public void ensureDefaultPersistsEnabledForFreshInstalls() {
        TestSharedPreferences preferences = new TestSharedPreferences();

        GboardHapticPrimitivesSettings.ensureDefault(preferences);

        Assert.assertEquals(
                Boolean.TRUE,
                preferences.values.get(GboardHapticPrimitivesSettings.PREF_KEY_ENABLED));
    }

    @Test
    public void writeUsesOnlyOwnedPreferenceKey() {
        TestSharedPreferences preferences = new TestSharedPreferences();

        Assert.assertTrue(GboardHapticPrimitivesSettings.writeEnabled(
                preferences,
                true));

        Assert.assertEquals(
                Collections.singleton(GboardHapticPrimitivesSettings.PREF_KEY_ENABLED),
                preferences.values.keySet());
        Assert.assertEquals(
                Boolean.TRUE,
                preferences.values.get(GboardHapticPrimitivesSettings.PREF_KEY_ENABLED));
    }

    private static final class TestSharedPreferences implements SharedPreferences {
        final Map<String, Object> values = new HashMap<>();

        @Override
        public Map<String, ?> getAll() {
            return Collections.unmodifiableMap(new HashMap<>(values));
        }

        @Override
        public String getString(String key, String defValue) {
            Object value = values.get(key);
            return value instanceof String ? (String) value : defValue;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Set<String> getStringSet(String key, Set<String> defValues) {
            Object value = values.get(key);
            return value instanceof Set<?> ? (Set<String>) value : defValues;
        }

        @Override
        public int getInt(String key, int defValue) {
            Object value = values.get(key);
            return value instanceof Number ? ((Number) value).intValue() : defValue;
        }

        @Override
        public long getLong(String key, long defValue) {
            Object value = values.get(key);
            return value instanceof Number ? ((Number) value).longValue() : defValue;
        }

        @Override
        public float getFloat(String key, float defValue) {
            Object value = values.get(key);
            return value instanceof Number ? ((Number) value).floatValue() : defValue;
        }

        @Override
        public boolean getBoolean(String key, boolean defValue) {
            Object value = values.get(key);
            return value instanceof Boolean ? ((Boolean) value).booleanValue() : defValue;
        }

        @Override
        public boolean contains(String key) {
            return values.containsKey(key);
        }

        @Override
        public Editor edit() {
            return new TestEditor();
        }

        @Override
        public void registerOnSharedPreferenceChangeListener(
                OnSharedPreferenceChangeListener listener) {
        }

        @Override
        public void unregisterOnSharedPreferenceChangeListener(
                OnSharedPreferenceChangeListener listener) {
        }

        private final class TestEditor implements Editor {
            private final Map<String, Object> pending = new HashMap<>();
            private final Set<String> removals = new HashSet<>();
            private boolean clear;

            @Override
            public Editor putString(String key, String value) {
                pending.put(key, value);
                return this;
            }

            @Override
            public Editor putStringSet(String key, Set<String> value) {
                pending.put(key, value == null ? null : new HashSet<>(value));
                return this;
            }

            @Override
            public Editor putInt(String key, int value) {
                pending.put(key, Integer.valueOf(value));
                return this;
            }

            @Override
            public Editor putLong(String key, long value) {
                pending.put(key, Long.valueOf(value));
                return this;
            }

            @Override
            public Editor putFloat(String key, float value) {
                pending.put(key, Float.valueOf(value));
                return this;
            }

            @Override
            public Editor putBoolean(String key, boolean value) {
                pending.put(key, Boolean.valueOf(value));
                return this;
            }

            @Override
            public Editor remove(String key) {
                removals.add(key);
                return this;
            }

            @Override
            public Editor clear() {
                clear = true;
                return this;
            }

            @Override
            public boolean commit() {
                apply();
                return true;
            }

            @Override
            public void apply() {
                if (clear) {
                    values.clear();
                }
                for (String key : removals) {
                    values.remove(key);
                }
                values.putAll(pending);
            }
        }
    }
}
