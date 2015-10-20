package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.m4armory.material.M4CopperMaterial;
import com.nyghtwolf.m4armory.reference.Reference;
import com.teammetallurgy.metallurgy.api.MetallurgyApi;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

//Define Generic Item
public class WeaponBase_Copper extends ItemSword
{
    public WeaponBase_Copper()
    {
        super(M4CopperMaterial.M4CopperMaterial);
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
        MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolEncantabilty();
        MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDamage();
        MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDurability();
    }
    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase()+ ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase()+ ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    //Registers the items texture
    @Override
    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}