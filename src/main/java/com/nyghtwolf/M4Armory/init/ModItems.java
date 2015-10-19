package com.nyghtwolf.m4armory.init;

import com.nyghtwolf.m4armory.item.ItemCopperSpear;
import cpw.mods.fml.common.registry.GameRegistry;


//@GameRegistry.ObjectHolder(Reference.MOD_ID)

public class ModItems
{
    //Define Spears
    public static final ItemCopperSpear ItemCopperSpear = new ItemCopperSpear();

    //Define Bows

    public static void init()
    {
    GameRegistry.registerItem(ItemCopperSpear,"CopperSpear");
    }
}