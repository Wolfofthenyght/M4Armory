package com.nyghtwolf.M4Armory.material;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class M4CopperMaterial {
    //First int: harvestLevel, Second int: maxUses, Third float: efficiencyOnProperMaterial, Fourth float: damageVsEntity, Fifth int: enchantibility
    public static Item.ToolMaterial M4CopperMaterial =EnumHelper.addToolMaterial("M4CopperMaterial",0, 300, 1.5F, 3.0F, 10);
}
