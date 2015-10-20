package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.m4armory.library.ToolHelper;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCopperScythe extends WeaponBase_Copper {

    public ItemCopperScythe() {
        super();
        this.setUnlocalizedName("CopperScythe");
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
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

    //Scythe Specific
    protected Material[] geteffectiveMaterials() {
        return materials;
    }

    static Material[] materials = new Material[]{Material.web, Material.cactus, Material.plants, Material.leaves, Material.vine, Material.gourd};

    public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ){
        return ToolHelper.hoeGround(stack, player, world, x, y, z, side, ToolHelper.random);
    }
}