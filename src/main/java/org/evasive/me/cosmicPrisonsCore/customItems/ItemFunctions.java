package org.evasive.me.cosmicPrisonsCore.customItems;

import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;

public class ItemFunctions {

    public static PickaxeBuilder getPickaxeBuilderFromId(String itemId){
        return (PickaxeBuilder) ItemList.valueOf(itemId).getItemBuilder();
    }

}
