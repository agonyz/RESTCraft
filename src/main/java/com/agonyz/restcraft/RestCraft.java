package com.agonyz.restcraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(RestCraft.MODID)
public class RestCraft {

    public static final String MODID = "restcraft";
    public static final Logger LOGGER = LogManager.getLogger();

    public RestCraft() {
        LOGGER.info("RestCraft mod loaded!");

        // register config
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC);

        // register event handler
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
    }
}