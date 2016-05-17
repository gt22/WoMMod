package com.projectbronze.wom.proxy;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.registry.BlockRegistry;
import com.projectbronze.wom.registry.ItemRegistry;
import com.projectbronze.wom.registry.RecepieRegistry;
import com.projectbronze.wom.registry.TileEntityRegistery;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;


public class CommonProxy{

	public void preInit(FMLPreInitializationEvent e)
	{
		BlockRegistry.init();
		ItemRegistry.init();
		RecepieRegistry.init();
		TileEntityRegistery.init();
	}
	
	public void init(FMLInitializationEvent e)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(WomCore.instance, new GuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		
	}
}
