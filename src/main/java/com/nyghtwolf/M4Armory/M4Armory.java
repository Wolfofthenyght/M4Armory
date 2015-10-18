package com.nyghtwolf.M4Armory;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = M4Armory.MODID, version = M4Armory.VERSION)
public class M4Armory
{
    public static final String MODID = "M4Armory";
    public static final String VERSION = "0.0.1a";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
