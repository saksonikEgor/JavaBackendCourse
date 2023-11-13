package edu.hw6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final File file;
    private final Map<String, String> data;
    private static final char SEPARATION_SYMBOL = ':';
    private static final Logger LOGGER = LogManager.getLogger();

    public DiskMap(File file) {
        this.file = file;
        data = new HashMap<>();
    }

    public DiskMap(String path) {
        this(new File(path));
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return data.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String prev = data.put(key, value);

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(key + SEPARATION_SYMBOL + value);
            writer.flush();
        } catch (IOException ex) {
            LOGGER.error(ex);
        }

        return prev;
    }

    @Override
    public String remove(Object key) {
        String removed = data.remove(key);



        return removed;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {
        data.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return data.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return data.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return data.entrySet();
    }
}
