package com.nyghtwolf.m4armory.creativetab;

import com.nyghtwolf.m4armory.init.ModItems;
import com.nyghtwolf.m4armory.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabM4Armory
{
    public static final CreativeTabs M4Armory_Tab = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
          return ModItems.ItemCopperScythe;
        }

        @Override
        public String getTranslatedTabLabel()
        {
            return "Metallurgy4 Armory";
        }
    };
}
