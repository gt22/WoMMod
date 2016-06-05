package com.projectbronze.wom.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.projectbronze.wom.registry.BlockRegistry;
import com.projectbronze.wom.world.generators.CloudGen;
import com.projectbronze.wom.world.generators.FlowerGen;
import com.projectbronze.wom.world.generators.IslandGen;

import cpw.mods.fml.common.IWorldGenerator;


public class WomWorldGenerator implements IWorldGenerator {
	private WorldGenerator flowergen;
	private IslandGen islandgen;
	private WorldGenMinable irongen, goldgen, diamondgen, redstonegen, emeraldgen, lapisgen;
	private CloudGen smallcloud, mediumcloud, largecloud, hugecloud;
	
	public WomWorldGenerator() {
		flowergen = new FlowerGen(BlockRegistry.desertFlower);
		islandgen = new IslandGen();
		irongen = new WorldGenMinable(Blocks.iron_ore, 20);
		goldgen = new WorldGenMinable(Blocks.gold_ore, 10);
		diamondgen = new WorldGenMinable(Blocks.diamond_ore, 10);
		redstonegen = new WorldGenMinable(Blocks.redstone_ore, 20);
		lapisgen = new WorldGenMinable(Blocks.lapis_ore, 10);
		smallcloud = new CloudGen(BlockRegistry.cloudBlock, 0, 10, false);
        mediumcloud = new CloudGen(BlockRegistry.cloudBlock, 0, 20, false);
        largecloud = new CloudGen(BlockRegistry.cloudBlock, 0, 30, false);
        hugecloud = new CloudGen(BlockRegistry.cloudBlock, 0, 40, false);
	}
	
	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {   
    	if(world.provider.dimensionId == 0)
    	{
			runGenerator(flowergen, world, random, chunkX, chunkZ, 100, 60, 100);
			if (/*random.nextInt(801) + random.nextInt(301)*/ random.nextInt(100) == 0)
			{
                 islandgen.generateIsland(world, random, chunkX * 16, chunkZ * 16);
   			}
			runGenerator(irongen, world, random, chunkX, chunkZ, 20, 100, 256);
            runGenerator(goldgen, world, random, chunkX, chunkZ, 5, 100, 256);
            runGenerator(diamondgen, world, random, chunkX, chunkZ, 3, 100, 256);
            runGenerator(redstonegen, world, random, chunkX, chunkZ, 10, 100, 256);
            runGenerator(lapisgen, world, random, chunkX, chunkZ, 10, 100, 256);
            int xChunk = chunkX * 16 + 8, zChunk = chunkZ * 16 + 8;
            int xCh = xChunk + random.nextInt(16);
            int zCh = zChunk + random.nextInt(16);
            int yCh = random.nextInt(107) + 150;
            int size = random.nextInt(13);
            if (size < 5) {
                smallcloud.generate(world, random, xCh, yCh, zCh);
            } else if (size < 9) {
                mediumcloud.generate(world, random, xCh, yCh, zCh);
            } else if (size < 11) {
                largecloud.generate(world, random, xCh, yCh, zCh);
            } else {
                hugecloud.generate(world, random, xCh, yCh, zCh);
            }
    	}
    	
    }
	

	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
	    if (minHeight < 0 || maxHeight > world.getActualHeight() || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        generator.generate(world, rand, x, y, z);
	    }
	}
}