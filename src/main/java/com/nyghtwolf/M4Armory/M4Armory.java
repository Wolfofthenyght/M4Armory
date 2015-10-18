package com.nyghtwolf.M4Armory;

import com.nyghtwolf.M4Armory.creativetab.CreativeTabM4Armory;
import com.nyghtwolf.M4Armory.init.ModRecipes;
import com.nyghtwolf.M4Armory.reference.Reference;
import com.nyghtwolf.M4Armory.init.ModItems;
import com.teammetallurgy.metallurgy.networking.CommonProxy;
import com.teammetallurgy.metallurgycore.CreativeTab;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid= Reference.MOD_ID, name="M4Armory", version="0.0.1a")
public class M4Armory {

    @Mod.Instance(Reference.MOD_ID)
    public static M4Armory instance;

    public static final String modid = "M4Armory";

    @SidedProxy(clientSide="com.nyghtwolf.M4Armory.proxy.ClientProxy", serverSide="com.nyghtwolf.M4Armory.proxy.CommonProxy")

    public static CommonProxy Wolfproxy;

    public CreativeTab CreativeTabM4Armory = new CreativeTab(Reference.MOD_ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        //Items Init
        ModItems.init();

        //Recipes Init
        ModRecipes.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}