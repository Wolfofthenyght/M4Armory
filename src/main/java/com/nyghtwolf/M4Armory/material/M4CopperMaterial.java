package com.nyghtwolf.m4armory.material;

import com.teammetallurgy.metallurgy.api.MetallurgyApi;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class M4CopperMaterial {
    //First int: harvestLevel, Second int: maxUses, Third float: efficiencyOnProperMaterial, Fourth float: damageVsEntity, Fifth int: enchantibility
    //Float of 1 on the 4th variable = 5 Damage
    public static Item.ToolMaterial M4CopperMaterial = EnumHelper.addToolMaterial("M4CopperMaterial",0, 5, 1.5F, 1.0F, 10);
}
