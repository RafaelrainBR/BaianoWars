package net.winternetwork.bedwars.api.config;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Getter
public class YamlConfig extends YamlConfiguration {

    private final File file;

    public YamlConfig(Plugin plugin, File parent, String fileName) {
        if (!parent.exists() && !parent.mkdir()) {
            throw new IllegalStateException("Could not create parent file");
        }

        if (!parent.isDirectory()) {
            throw new IllegalArgumentException("Parent is not a directory");
        }

        file = new File(parent, fileName);
        try {
            /*
            if(!file.exists() && !file.createNewFile()) {
                throw new IllegalStateException("Could not create file");
            }
             */
            if (!file.exists()) {
                plugin.saveResource(fileName, false);
            }

            if (file.isDirectory()) {
                throw new IllegalArgumentException("File is a directory");
            }

            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8
            );

            load(reader);
        } catch (IOException | InvalidConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> T getOrDefault(String path, T def) {
        Object object = get(path);
        if (object == null) {
            set(path, def);
            save();
            return def;
        }

        return ((T) object);
    }

    public boolean save() {
        try {
            save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean reload() throws IllegalStateException {
        try {
            if (!file.exists() && !file.createNewFile()) {
                throw new IllegalStateException("Could not create file");
            }

            if (file.isDirectory()) {
                throw new IllegalArgumentException("File is a directory");
            }

            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8
            );

            load(reader);
        } catch (IOException | InvalidConfigurationException e) {
            throw new IllegalStateException(e);
        }

        return true;
    }

}

