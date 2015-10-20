package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.m4armory.material.M4CopperMaterial;
import com.nyghtwolf.m4armory.reference.Reference;
import com.teammetallurgy.metallurgy.api.MetallurgyApi;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class ItemCopperScythe extends WeaponBase_Copper {

    public ItemCopperScythe() {
        super();
        this.setUnlocalizedName("CopperScythe");
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
    }

    protected Material[] geteffectiveMaterials() {
        return materials;
    }

    static Material[] materials = new Material[]{Material.web, Material.cactus, Material.plants, Material.leaves, Material.vine, Material.gourd};

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    //Registers the items' texture
    @Override
    @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}

    //Scythe Specific