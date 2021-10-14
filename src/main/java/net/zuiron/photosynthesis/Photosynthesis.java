package net.zuiron.photosynthesis;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.zuiron.photosynthesis.block.CustomCropBlock;
import net.zuiron.photosynthesis.block.ModBlocks;
import net.zuiron.photosynthesis.item.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Photosynthesis implements ModInitializer {
	public static final String MOD_ID = "photosynthesis";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems(); //Items first otherwise crash.
		ModBlocks.registerModBlocks();

		LOGGER.info("Hello Fabric world!");
	}
}
