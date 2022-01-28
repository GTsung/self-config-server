package com.gt.self.config.core;

import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @create: 2022-01-24 14:10
 **/
public class VariablePool {

    public static Map<String, Map<Class, String>> pool = new HashMap<>();
    private static final String regex = "^(\\$\\{)(.)+(\\})$";
    private static Pattern pattern;

    static {
        pattern = Pattern.compile(regex);
    }

    public static void add(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                Value annotation = field.getAnnotation(Value.class);
                String annoValue = annotation.value();
                if (!pattern.matcher(annoValue).matches())
                    continue;

                annoValue = annoValue.replace("${", "");
                annoValue = annoValue.substring(0, annoValue.length() - 1);

                Map<Class, String> clazzMap = Optional.ofNullable(pool.get(annoValue))
                        .orElse(new HashMap<>());
                // 當前類與屬性名
                clazzMap.put(clazz, field.getName());
                // key為配置文件中的屬性，value為鍵:當前類，值:屬性名
                pool.put(annoValue, clazzMap);
            }
        }
    }

    public static Map<String, Map<Class, String>> getPool() {
        return pool;
    }
}
