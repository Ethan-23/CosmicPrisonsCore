package org.evasive.me.cosmicPrisonsCore.utils;

import io.github.retrooper.packetevents.adventure.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ComponentUtils {

    public static Component legacy(String input) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(input).decoration(TextDecoration.ITALIC, false);
    }
}
