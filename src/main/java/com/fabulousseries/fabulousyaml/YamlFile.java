package com.fabulousseries.fabulousyaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public record YamlFile(String fileName)  {

    private File getFile() {
        URL url = this.getClass().getClassLoader().getResource(fileName);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        } finally {
            return file;
        }
    }
    public YamlConfig getConfig() {
        InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return new YamlConfig(new Yaml().load(inputStream));
    }

    public void saveConfig(YamlConfig yamlConfig) {

        DumperOptions options = new DumperOptions();
        options.setIndent(4);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);


        Yaml yaml = new Yaml(options);

        try (FileWriter writer = new FileWriter(getFile())) {
            yaml.dump(yamlConfig.config, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}