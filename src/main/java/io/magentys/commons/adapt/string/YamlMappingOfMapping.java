package io.magentys.commons.adapt.string;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;
import java.util.Set;

public class YamlMappingOfMapping {

    private Map<String, Map<String, String>> mappings;

    @SuppressWarnings("unchecked")
    public YamlMappingOfMapping(String string) {
        mappings = (Map<String, Map<String, String>>) new Yaml().load(string);

    }

    public Map<String, String> getMappingsFor(String key) {
        return mappings.get(key);
    }

    public Set<String> getKeys() {
        return mappings.keySet();
    }
}
