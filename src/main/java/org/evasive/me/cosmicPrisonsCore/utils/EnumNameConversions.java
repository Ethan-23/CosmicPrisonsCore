package org.evasive.me.cosmicPrisonsCore.utils;

import org.bukkit.Material;

import java.util.List;

public class EnumNameConversions {

    public String getOreName(Material material){
        List<String> nameParts = List.of(material.name().split("_"));
        StringBuilder oreName = new StringBuilder();
        for (String part : nameParts){
            oreName.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
            if(!part.equals(nameParts.getLast()))
                oreName.append(" ");
        }
        return oreName.toString();
    }

}
