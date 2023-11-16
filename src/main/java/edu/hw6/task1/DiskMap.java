package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final Path path;
    private final Map<String, String> data;
    private static final char SEPARATION_SYMBOL = ':';
    private static final Logger LOGGER = LogManager.getLogger();

    public DiskMap(Map<String, String> data, Path path) {
        this.path = path;
        this.data = data;
        loadFromDisk();
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
        flushToDisk();
        return prev;
    }

    @Override
    public String remove(Object key) {
        String removed = data.remove(key);
        flushToDisk();
        return removed;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        data.putAll(m);
        flushToDisk();
    }

    @Override
    public void clear() {
        data.clear();
        flushToDisk();
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

    public void loadFromDisk() {
        LOGGER.info("Loading data from the \"{}\"", path);
        data.clear();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] entryData = line.split(String.valueOf(SEPARATION_SYMBOL));
                data.put(entryData[0], entryData[1]);
            }

        } catch (IOException e) {
            LOGGER.error("Caught an exception during loading DiskBackedMap from the disk", e);
            throw new IllegalStateException(e);
        }
    }

    public void flushToDisk() {
        LOGGER.info("Flushing data to the \"{}\"", path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            StringBuilder sb = new StringBuilder();
            forEach((key, value) -> sb.append(key).append(SEPARATION_SYMBOL).append(value).append("\n"));
            writer.write(sb.toString());
        } catch (IOException e) {
            LOGGER.error("Caught an exception during flushing DiskBackedMap to the disk", e);
            throw new IllegalStateException(e);
        }
    }
}
