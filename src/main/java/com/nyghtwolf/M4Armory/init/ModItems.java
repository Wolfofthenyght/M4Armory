package com.nyghtwolf.M4Armory.init;

import com.nyghtwolf.M4Armory.item.ItemCopperSpear;
import com.nyghtwolf.M4Armory.item.ItemM4Armory;
import com.nyghtwolf.M4Armory.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;


@GameRegistry.ObjectHolder(Reference.MOD_ID)

//Initialize Items
public class ModItems
{
    //Define Spears
    public static final ItemCopperSpear ItemCopperSpear = new ItemCopperSpear();

    public static void init()
    {
       GameRegistry.registerItem(ItemCopperSpear,"Copper Spear");
    }
}