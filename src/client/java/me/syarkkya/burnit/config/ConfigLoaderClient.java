package me.syarkkya.burnit.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

public class ConfigLoaderClient {
    public static final ConfigClassHandler<ConfigLoaderClient> HANDLER = ConfigClassHandler.createBuilder(ConfigLoaderClient.class)
            .id(ResourceLocation.parse("burnit:config-client"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("burnit-client.json5"))
                    .build())
            .build();

    public static ConfigLoaderClient getConfig() {
        return HANDLER.instance();
    }

    @SerialEntry
    public Boolean burnToggle = true;
    @SerialEntry
    public String burnString = "minecraft:entity.generic.burn";
    @SerialEntry
    public Float burnPitch = 2f;
    @SerialEntry
    public Float burnVolume = 0.25f;

    @SerialEntry
    public Boolean fireToggle = true;
    @SerialEntry
    public String fireString = "minecraft:block.fire.ambient";
    @SerialEntry
    public Float firePitch = 0.5f;
    @SerialEntry
    public Float fireVolume = 0.5f;

    @SerialEntry
    public Boolean extinguishToggle = true;
    @SerialEntry
    public String extinguishString = "minecraft:block.lava.extinguish";
    @SerialEntry
    public Float extinguishPitch = 0.8f;
    @SerialEntry
    public Float extinguishVolume = 0.25f;

    public static void init() {
        HANDLER.load();
    }
}