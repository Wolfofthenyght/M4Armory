package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.m4armory.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemCopperSpear extends WeaponBase_Copper {

    public ItemCopperSpear()
    {
        super();
        this.setUnlocalizedName("CopperSpear");
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
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


    //Spear Specific
    @Override
    public EnumAction getItemUseAction (ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player)
    {
        if (player.onGround)
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        return stack;
    }

    //@Override
    public float chargeAttack ()
    {
        return 1.0f;
    }

    @Override
    public void onPlayerStoppedUsing (ItemStack stack, World world, EntityPlayer player, int useCount)
    {
        /*
         * if (player.onGround) { int time = this.getMaxItemUseDuration(stack) -
         * useCount; if (time > 5) { player.addExhaustion(0.2F);
         * player.setSprinting(true);
         *
         * float speed = 0.05F * time; if (speed > 0.925f) speed = 0.925f;
         *
         * float increase = (float) (0.02 * time + 0.2); if (increase > 0.56f)
         * increase = 0.56f; player.motionY += increase + speed/3;
         *
         * player.motionX = (double) (-MathHelper.sin(player.rotationYaw /
         * 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch /
         * 180.0F * (float) Math.PI) * speed); player.motionZ = (double)
         * (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) *
         * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI) *
         * speed); } }
         */
        int time = this.getMaxItemUseDuration(stack) - useCount;
        if (time > 5)
        {
            player.addExhaustion(0.2F);
            player.setSprinting(true);

            float increase = (float) (0.02 * time + 0.2);
            if (increase > 0.56f)
                increase = 0.56f;
            player.motionY += increase;

            float speed = 0.05F * time;
            if (speed > 0.925f)
                speed = 0.925f;
            player.motionX = (double) (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI) * speed);
            player.motionZ = (double) (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI) * speed);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate (ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        super.onUpdate(stack, world, entity, par4, par5);
        if (entity instanceof EntityPlayerSP)
        {
            EntityPlayerSP player = (EntityPlayerSP) entity;
            ItemStack usingItem = player.getItemInUse();
            if (usingItem != null && usingItem.getItem() == this)
            {
                player.movementInput.moveForward *= 1.0F;
                player.movementInput.moveStrafe *= 1.0F;
            }
        }
    }

    //@SideOnly(Side.CLIENT)
    //public void registerIcons(IIconRegister register){
    //this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1);
    //}
}