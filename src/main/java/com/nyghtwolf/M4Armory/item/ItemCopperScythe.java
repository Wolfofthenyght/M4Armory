package com.nyghtwolf.m4armory.item;

import com.nyghtwolf.m4armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.m4armory.material.M4CopperMaterial;
import com.nyghtwolf.m4armory.reference.Reference;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ItemCopperScythe extends ItemHoe {

    public ItemCopperScythe()
    {
        super(M4CopperMaterial.M4CopperMaterial);
        this.setUnlocalizedName("CopperScythe");
        this.setCreativeTab(CreativeTabM4Armory.M4Armory_Tab);
        this.maxStackSize = 1;
        //MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolEncantabilty();
        //MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDamage();
        //MetallurgyApi.getMetalSet("base").getMetal("Copper").getToolDurability();

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

    //Registers the items' texture
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
    public boolean onBlockStartBreak (ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        if (!stack.hasTagCompound())
            return false;

        World world = player.worldObj;
        final Block blockB = world.getBlock(x, y, z);
        final int meta = world.getBlockMetadata(x, y, z);
        if (!stack.hasTagCompound())
            return false;
        NBTTagCompound tags = stack.getTagCompound().getCompoundTag("InfiTool");
        boolean butter = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) > 0;
        int fortune = EnchantmentHelper.getFortuneModifier(player);
        for (int xPos = x - 1; xPos <= x + 1; xPos++)
        {
            for (int yPos = y - 1; yPos <= y + 1; yPos++)
            {
                for (int zPos = z - 1; zPos <= z + 1; zPos++)
                {
                    if (!(tags.getBoolean("Broken")))
                    {
                        boolean cancelHarvest = false;
                        for (ActiveToolMod mod : TConstructRegistry.activeModifiers)
                        {
                            if (mod.beforeBlockBreak(this, stack, xPos, yPos, zPos, player))
                                cancelHarvest = true;
                        }

                        if (!cancelHarvest)
                        {
                            Block localBlock = world.getBlock(xPos, yPos, zPos);
                            int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
                            float localHardness = localBlock == null ? Float.MAX_VALUE : localBlock.getBlockHardness(world, xPos, yPos, zPos);
                            if (localBlock != null)// && (block.blockMaterial == Material.leaves || block.isLeaves(world, xPos, yPos, zPos)))
                            {
                                for (int iter = 0; iter < materials.length; iter++)
                                {
                                    if (materials[iter] == localBlock.getMaterial())
                                    {
                                        if (!player.capabilities.isCreativeMode)
                                        {
                                            if (butter && localBlock instanceof IShearable && ((IShearable) localBlock).isShearable(stack, player.worldObj, xPos, yPos, zPos))
                                            {
                                                ArrayList<ItemStack> drops = ((IShearable) localBlock).onSheared(stack, player.worldObj, xPos, yPos, zPos, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
                                                Random rand = new Random();

                                                if (!world.isRemote)
                                                    for (ItemStack dropStack : drops)
                                                    {
                                                        float f = 0.7F;
                                                        double d = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                                        double d1 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                                        double d2 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                                        EntityItem entityitem = new EntityItem(player.worldObj, (double) xPos + d, (double) yPos + d1, (double) zPos + d2, dropStack);
                                                        entityitem.delayBeforeCanPickup = 10;
                                                        player.worldObj.spawnEntityInWorld(entityitem);
                                                    }

                                                if (localHardness > 0f)
                                                    onBlockDestroyed(stack, world, localBlock, xPos, yPos, zPos, player);
                                                player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(localBlock)], 1);
                                                world.setBlockToAir(xPos, yPos, zPos);
                                            }
                                            else
                                            {

                                                // Workaround for dropping experience
                                                int exp = localBlock.getExpDrop(world, localMeta, fortune);

                                                localBlock.onBlockHarvested(world, xPos, yPos, zPos, localMeta, player);
                                                if (localBlock.removedByPlayer(world, player, xPos, yPos, zPos, true))
                                                {
                                                    localBlock.onBlockDestroyedByPlayer(world, xPos, yPos, zPos, localMeta);
                                                    localBlock.harvestBlock(world, player, xPos, yPos, zPos, localMeta);
                                                    // Workaround for dropping experience
                                                    if (!butter)
                                                        localBlock.dropXpOnBlockBreak(world, xPos, yPos, zPos, exp);
                                                }

                                                if (world.isRemote)
                                                {
                                                    INetHandler handler = FMLClientHandler.instance().getClientPlayHandler();
                                                    if (handler != null && handler instanceof NetHandlerPlayClient)
                                                    {
                                                        NetHandlerPlayClient handlerClient = (NetHandlerPlayClient) handler;
                                                        handlerClient.addToSendQueue(new C07PacketPlayerDigging(0, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
                                                        handlerClient.addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
                                                    }
                                                }

                                                if (localHardness > 0f)
                                                    onBlockDestroyed(stack, world, localBlock, xPos, yPos, zPos, player);
                                            }
                                        }
                                        else
                                        {
                                            world.setBlockToAir(xPos, yPos, zPos);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!world.isRemote)
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(blockB) + (meta << 12));
        return super.onBlockStartBreak(stack, x, y, z, player);
    }

    @Override
    public boolean onLeftClickEntity (ItemStack stack, EntityPlayer player, Entity entity)
    {
        AxisAlignedBB box = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.posX + 1.0D, entity.posY + 1.0D, entity.posZ + 1.0D).expand(1.0D, 1.0D, 1.0D);
        List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, box);
        for (Object o : list)
        {
            AbilityHelper.onLeftClickEntity(stack, player, (Entity) o, this);
        }
        return true;
    }
}
