package com.restcraft.restcraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.ConfigValue<String> BACKEND_URL = BUILDER
            .comment("The URL of the backend to send player events to")
            .define("backendUrl", "http://localhost:3000/api");
    public static final ForgeConfigSpec SPEC = BUILDER.build();
}