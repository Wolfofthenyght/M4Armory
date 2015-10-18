package com.nyghtwolf.M4Armory.item;

import com.nyghtwolf.M4Armory.creativetab.CreativeTabM4Armory;
import net.minecraft.item.ItemSword;

public class ItemSpearBase extends ItemSword{

    public ItemSpearBase (ToolMaterial M4CopperMaterial){

        super(M4CopperMaterial);
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
        this.maxStackSize = 1;
    }
}
