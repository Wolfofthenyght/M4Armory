package com.nyghtwolf.M4Armory.creativetab;

import com.nyghtwolf.M4Armory.init.ModItems;
import com.nyghtwolf.M4Armory.reference.Reference;
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
          return Items.gold_ingot;
        }

        @Override
        public String getTranslatedTabLabel()
        {
            return "Metallurgy4 Armory";
        }
    };
}
