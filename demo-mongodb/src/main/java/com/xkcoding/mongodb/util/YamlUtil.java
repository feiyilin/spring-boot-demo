package com.xkcoding.mongodb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class YamlUtil {
    private static Logger logger = LoggerFactory.getLogger(YamlUtil.class);
    private static List<Map<String, Object>> propertyList = new ArrayList();

    private YamlUtil() {
    }

    public static void loadApplication(String profile) {
        load("application");
        load("application", profile);
    }

    public static void load(String fileName, String profile) {
        String file = fileName + "-" + profile;
        load(file);
    }

    public static void load(String fileName) {
        try {
            String file = fileName + ".yaml";
            InputStream resource = YamlUtil.class.getClassLoader().getResourceAsStream(file);
            if (Objects.nonNull(resource)) {
                Yaml yaml = new Yaml();
                Map m = (Map)yaml.loadAs(resource, Map.class);
                propertyList.add(m);
            }
        } catch (Exception var5) {
            logger.error("YamlUtil load error", var5);
        }

    }

    public static void loadContent(String content) {
        try {
            if (Objects.nonNull(content)) {
                Yaml yaml = new Yaml();
                Map m = (Map)yaml.loadAs(content, Map.class);
                propertyList.add(m);
            }
        } catch (Exception var3) {
            logger.error("YamlUtil load error", var3);
        }

    }

    public static <T> T get(String key, T defaultValue) {
        return getValue(key, defaultValue);
    }

    public static String get(String key) {
        return (String)getValue(key, (Object)((String)null));
    }

    public static String getValue(String key) {
        return (String)getValue(key, (Object)((String)null));
    }

    public static <T> T getValue(String key, T defaultValue) {
        Object value = null;

        for(int i = propertyList.size() - 1; i >= 0; --i) {
            Map<String, Object> properties = (Map)propertyList.get(i);
            value = getValue(key, properties);
            if (value != null) {
                return value;
            }
        }

        return defaultValue;
    }

    private static Object getValue(String key, Map<String, Object> properties) {
        if (properties == null) {
            return null;
        } else {
            String separator = ".";
            String[] separatorKeys = null;
            if (key.contains(separator)) {
                separatorKeys = key.split("\\.");
                Object var4 = null;
                Object tempObject = properties;

                for(int i = 0; i < separatorKeys.length; ++i) {
                    String innerKey = separatorKeys[i];
                    Integer index = null;
                    Map<String, Object> mapTempObj = (Map)tempObject;
                    Object object = mapTempObj.get(innerKey);
                    if (object == null) {
                        return null;
                    }

                    Object targetObj = object;
                    if (index != null) {
                        targetObj = ((ArrayList)object).get(index);
                    }

                    tempObject = targetObj;
                    if (i == separatorKeys.length - 1) {
                        return targetObj;
                    }
                }

                return null;
            } else {
                return properties.get(key);
            }
        }
    }
}