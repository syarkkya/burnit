package me.syarkkya.burnit;

import me.syarkkya.burnit.config.ConfigScreen;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.fabricmc.loader.api.FabricLoader;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
            return ConfigScreen::createScreen;
        } else {
            return parent -> new ConfirmScreen(
                    (confirmed) -> {
                        if (confirmed) {
                            Util.getPlatform().openUri("https://modrinth.com/mod/yacl/");
                        }
                        Minecraft.getInstance().setScreen(parent);
                    },
                    Component.translatable("burnit.config.yaclmissing"),
                    Component.translatable("burnit.config.yaclmissing.desc"),
                    Component.translatable("chat.link.open"),
                    Component.translatable("gui.back")
            );
        }
    }
}