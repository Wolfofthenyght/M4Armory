package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.m4armory.reference.Reference;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemSpearBase extends ItemSword{

    public ItemSpearBase (ToolMaterial M4CopperMaterial)
    {
        super(M4CopperMaterial);
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
        this.maxStackSize = 1;
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
