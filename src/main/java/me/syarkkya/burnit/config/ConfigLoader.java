package me.syarkkya.burnit.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    public static final ConfigClassHandler<ConfigLoader> HANDLER = ConfigClassHandler.createBuilder(ConfigLoader.class)
            .id(ResourceLocation.parse("burnit:config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("burnit-server.json5"))
                    .build())
            .build();

    public static ConfigLoader getConfig() {
        return HANDLER.instance();
    }

    @SerialEntry
    public List<String> burnSources = new ArrayList<>(List.of(
            "minecraft:lava_bucket",
            "minecraft:fire_charge",
            "minecraft:flint_and_steel"
    ));

    @SerialEntry
    public Boolean keepDurability = false;

    @SerialEntry
    public Boolean preventFireResistant = true;

    @SerialEntry
    public Boolean preventBurnSources = true;

    @SerialEntry
    public Boolean preventShalkerBoxes = true;

    @SerialEntry
    public boolean preventEnchantedItems = true;

    @SerialEntry
    public List<String> preventCustomItems = new ArrayList<>(List.of(
    ));

    public static void init() {
        HANDLER.load();
    }
}