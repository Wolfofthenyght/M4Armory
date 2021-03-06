package com.nyghtwolf.m4armory.init;

import com.nyghtwolf.m4armory.item.ItemCopperBow;
import com.nyghtwolf.m4armory.item.ItemCopperScythe;
import com.nyghtwolf.m4armory.item.ItemCopperSpear;
import com.nyghtwolf.m4armory.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

//@GameRegistry.ObjectHolder(Reference.MOD_ID)

public class ModItems
{
    //Define Spears
    public static final ItemCopperSpear ItemCopperSpear = new ItemCopperSpear();

    //Define Bows
    public static final ItemCopperBow ItemCopperBow = new ItemCopperBow();

    //Define Scythes
    public static final ItemCopperScythe ItemCopperScythe = new ItemCopperScythe();

    public static void init()
    {
    GameRegistry.registerItem(ItemCopperSpear,"CopperSpear");
    GameRegistry.registerItem(ItemCopperBow, "CopperBow");
    GameRegistry.registerItem(ItemCopperScythe, "CopperScythe");
    }
}