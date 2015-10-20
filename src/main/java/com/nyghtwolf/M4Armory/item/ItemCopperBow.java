package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.teammetallurgy.metallurgy.api.MetallurgyApi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemCopperBow extends WeaponBase_Copper {

    public static final String[] bowPullIconNameArray = new String[]{"Copper_bow_pull_0", "Copper_bow_pull_1", "Copper_bow_pull_2",};
    IIcon[] iconArray;

    public ItemCopperBow(){
        super();
        this.setUnlocalizedName("CopperBow");
        this.maxStackSize = 1;
        MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolEncantabilty();
        MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDamage();
        MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDurability();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister){
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        this.itemIcon = par1IIconRegister.registerIcon("m4armory:CopperBow");
        for (int i = 0; i < this.iconArray.length; ++i){
            this.iconArray[i] = par1IIconRegister.registerIcon("m4armory:" + bowPullIconNameArray[i]);
        }
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining){
        if (usingItem != null){
            int j = this.getMaxItemUseDuration(stack) - useRemaining;
            if (j >= 18){
                return this.getItemIconForUseDuration(2);
            }

            if (j > 13){
                return this.getItemIconForUseDuration(1);
            }

            if (j > 0){
                return this.getItemIconForUseDuration(0);
            }
        }

        return this.getIcon(stack, renderPass);
    }

    //@Override
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int par1){
        return this.iconArray[par1];
    }
}