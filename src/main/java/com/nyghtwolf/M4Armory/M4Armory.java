package com.nyghtwolf.m4armory;

import com.nyghtwolf.m4armory.init.ModRecipes;
import com.nyghtwolf.m4armory.reference.Reference;
import com.nyghtwolf.m4armory.init.ModItems;
import com.teammetallurgy.metallurgy.Metallurgy;
import com.teammetallurgy.metallurgy.networking.CommonProxy;
import com.teammetallurgy.metallurgycore.CreativeTab;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid= Reference.MOD_ID, name="m4armory", version="0.0.1a", dependencies = Metallurgy.DEPS)

public class M4Armory {

    @Mod.Instance(Reference.MOD_ID)
    public static M4Armory instance;

    public static final String modid = "m4armory";

    @SidedProxy(clientSide="com.nyghtwolf.m4armory.proxy.ClientProxy", serverSide="com.nyghtwolf.m4armory.proxy.CommonProxy")

    public static CommonProxy Wolfproxy;

    public CreativeTab CreativeTabM4Armory = new CreativeTab(Reference.MOD_ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        //Items Init
        ModItems.init();

        //Recipes Init
        //ModRecipes.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}