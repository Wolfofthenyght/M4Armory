package com.nyghtwolf.m4armory.library;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ToolHelper {
    public  static Random random = new Random();

    //Scythe Left/Right click Events
    //Right Click - Hoe function

    public static boolean hoeGround (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, Random random)
    {
        if (!player.canPlayerEdit(x, y, z, side, stack))
        {
            return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == Event.Result.ALLOW)
            {
                //damageTool(stack, 1, player, false);
                return true;
            }

            Block block = world.getBlock(x, y, z);

            if (side != 0 && world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt))
            {
                Block block1 = Blocks.farmland;
                world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

                if (world.isRemote)
                {
                    return true;
                }
                else
                {
                    world.setBlock(x, y, z, block1);
                    //damageTool(stack, 1, player, false);
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
