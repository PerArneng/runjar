package com.runjar.model;

import java.util.*;

public class ManifestInfo {


    private static final String MAIN_CLASS_KEY = "Main-Class";
    private final Map<String, String> properties = new HashMap<String, String>();
    private final List<String> propertyKeys;

    public ManifestInfo(Map<?, ?> props) {

        for (Map.Entry<?, ?> entry: props.entrySet()) {
            properties.put("" + entry.getKey(), "" + entry.getValue());
        }

        this.propertyKeys = new ArrayList<String>(this.properties.keySet());
    }

    public boolean hasMainClass() {
        return this.properties.containsKey("Main-Class");
    }

    public String getMainClass() {
        return properties.get(MAIN_CLASS_KEY);
    }

    public String getValue(String property) {
        return this.properties.get(property);
    }

    public List<String> getPropertyKeys() {
        return new ArrayList<String>(this.properties.keySet());
    }

}
