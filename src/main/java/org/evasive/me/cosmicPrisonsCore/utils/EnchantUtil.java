package org.evasive.me.cosmicPrisonsCore.utils;

import java.util.HashMap;
import java.util.Map;

public class EnchantUtil {

    public <E extends Enum<E>> String EnchantMapToString(Map<E, Integer> enchantMap){
        StringBuilder enchantList = new StringBuilder();
        for (Map.Entry<E, Integer> entry : enchantMap.entrySet()) {
            enchantList.append(entry.getKey().name()) // save enum name
                    .append(":")
                    .append(entry.getValue())
                    .append(",");
        }
        if (!enchantList.isEmpty()) enchantList.setLength(enchantList.length() - 1);
        return enchantList.toString();
    }

    public <E extends Enum<E>> Map<E, Integer> stringToEnumMap(String str, Class<E> enumClass) {
        Map<E, Integer> map = new HashMap<>();
        if (str == null || str.isEmpty()) return map;

        String[] entries = str.split(",");
        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            if (keyValue.length == 2) {
                E key = Enum.valueOf(enumClass, keyValue[0]); // reconstruct enum
                Integer value = Integer.parseInt(keyValue[1]);
                map.put(key, value);
            }
        }
        return map;
    }

    public String intToRoman(int num) {
        int[] values =    {1000, 900, 500, 400, 100, 90,  50,  40,  10, 9,   5,  4,   1};
        String[] symbols = {"M",  "CM","D", "CD","C", "XC","L", "XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
        }

        return roman.toString();
    }

}
