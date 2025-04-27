package org.evasive.me.cosmicPrisonsCore.customItems.shards;

import net.kyori.adventure.text.Component;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.ArrayList;
import java.util.List;

public interface ShardBuilder extends ItemBuilder {
    @Override
    default List<Component> getLore(){
        List<Component> lore = new ArrayList<>();
        lore.add(ComponentUtils.legacy("&7A powerful relic that"));
        lore.add(ComponentUtils.legacy("&7may contain treasure"));
        lore.add(ComponentUtils.legacy(""));
        lore.add(ComponentUtils.legacy("&7Contains "+getRatiry().getRarityBuilder().getTextColor()+"&n" + getRatiry().toString().substring(0,1) + getRatiry().toString().substring(1).toLowerCase() + getRatiry().getRarityBuilder().getTextColor()+ "&n Rarity&r &7items..."));
        return lore;
    }

    @Override
    default Component getName(){
        return ComponentUtils.legacy(getRatiry().getRarityBuilder().getTextColor()+"&n" + getRatiry().toString().substring(0,1) + getRatiry().toString().substring(1).toLowerCase() + "Shard");
    };

    Rarity getRatiry();
}
