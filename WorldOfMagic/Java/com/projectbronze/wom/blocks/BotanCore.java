package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.BotanCoreEntity;


public class BotanCore extends GenericCore {

	

	 public BotanCore(String unlocName, Block portal) {
		super(unlocName, portal);
	}


	@Override
	    public TileEntity createNewTileEntity(World worldIn, int meta) {
	        return new BotanCoreEntity();
	    }
	
	 
}