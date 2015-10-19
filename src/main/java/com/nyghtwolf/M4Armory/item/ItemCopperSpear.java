package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.material.M4CopperMaterial;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemCopperSpear extends ItemSpearBase{

    public ItemCopperSpear()
    {
        super(M4CopperMaterial.M4CopperMaterial);
        this.setUnlocalizedName("CopperSpear");
        //MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolEncantabilty();
        //MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDamage();
        //MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDurability();
    }

    //@SideOnly(Side.CLIENT)
    //public void registerIcons(IIconRegister register){
    //this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1);
    //}
}

