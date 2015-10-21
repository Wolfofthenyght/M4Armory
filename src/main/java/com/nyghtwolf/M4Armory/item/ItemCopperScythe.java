package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.m4armory.library.ToolHelper;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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

    //Allows right click to hoe the ground into farmland
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ) {
        return ToolHelper.hoeGround(stack, player, world, x, y, z, side, ToolHelper.random);
    }

    //@Override
    //public boolean canHarvestBlock(Block block, ItemStack stack) {
    //return block == Blocks.leaves || block == Blocks.leaves2 || block == Blocks.web || block == Blocks.tallgrass || block == Blocks.vine;
    //}

    //Allows Scythe to break grass and other materials at an efficient rate
    public float onBlockBreak(ItemStack item, Block block)
    {
        if (block == Blocks.web || block == Blocks.vine)
        {
            return 15.0F;
        }
        else
        {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 2.5F;
        }
    }
}