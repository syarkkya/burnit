package me.syarkkya.burnit.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen {
    public static Screen createScreen(Screen parent) {
        return YetAnotherConfigLib.create(ConfigLoader.HANDLER, (defaults, config, builder) -> {
            ConfigLoaderClient clientConfig = ConfigLoaderClient.getConfig();
            ConfigLoaderClient clientDefaults = ConfigLoaderClient.HANDLER.defaults();
            return builder
                    .title(Component.translatable("burnit.config.title"))
                    .category(ConfigCategory.createBuilder()
                            .name(Component.translatable("burnit.config.general"))
                            .tooltip(Component.translatable("burnit.config.general.tooltip"))
                            .group(ListOption.<String>createBuilder()
                                    .name(Component.translatable("burnit.config.burnsources"))
                                    .description(OptionDescription.of(Component.translatable("burnit.config.burnsources.desc")))
                                    .binding(defaults.burnSources,
                                            () -> config.burnSources,
                                            val -> config.burnSources = val
                                    )
                                    .controller(StringControllerBuilder::create)
                                    .initial("")
                                    .build())
                            .option(Option.<Boolean>createBuilder()
                                    .name(Component.translatable("burnit.config.keepdurability"))
                                    .description(OptionDescription.of(Component.translatable("burnit.config.keepdurability.desc")))
                                    .binding(
                                            defaults.keepDurability,
                                            () -> config.keepDurability,
                                            val -> config.keepDurability = val
                                    )
                                    .controller(opt -> BooleanControllerBuilder.create(opt)
                                            .formatValue(val -> val ? Component.translatable("burnit.config.keepdurability.on") : Component.translatable("burnit.config.keepdurability.off"))
                                            .coloured(true))
                                    .build())
                            .group(OptionGroup.createBuilder()
                                    .name(Component.translatable("burnit.config.prevent"))
                                    .description(OptionDescription.of(Component.translatable("burnit.config.prevent.desc")))
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Component.translatable("burnit.config.preventburnsources"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.preventburnsources.desc")))
                                            .binding(
                                                    defaults.preventBurnSources,
                                                    () -> config.preventBurnSources,
                                                    val -> config.preventBurnSources = val
                                            )
                                            .controller(opt -> BooleanControllerBuilder.create(opt)
                                                    .formatValue(val -> val ? Component.translatable("burnit.config.preventburnsources.on") : Component.translatable("burnit.config.preventburnsources.off"))
                                                    .coloured(true))
                                            .build())
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Component.translatable("burnit.config.preventfireresistant"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.preventfireresistant.desc")))
                                            .binding(
                                                    defaults.preventFireResistant,
                                                    () -> config.preventFireResistant,
                                                    val -> config.preventFireResistant = val
                                            )
                                            .controller(opt -> BooleanControllerBuilder.create(opt)
                                                    .formatValue(val -> val ? Component.translatable("burnit.config.preventfireresistant.on") : Component.translatable("burnit.config.preventfireresistant.off"))
                                                    .coloured(true))
                                            .build())
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Component.translatable("burnit.config.preventenchanteditems"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.preventenchanteditems.desc")))
                                            .binding(
                                                    defaults.preventEnchantedItems,
                                                    () -> config.preventEnchantedItems,
                                                    val -> config.preventEnchantedItems = val
                                            )
                                            .controller(opt -> BooleanControllerBuilder.create(opt)
                                                    .formatValue(val -> val ? Component.translatable("burnit.config.preventenchanteditems.on") : Component.translatable("burnit.config.preventenchanteditems.off"))
                                                    .coloured(true))
                                            .build())
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Component.translatable("burnit.config.preventshalkerboxes"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.preventshalkerboxes.desc")))
                                            .binding(
                                                    defaults.preventShalkerBoxes,
                                                    () -> config.preventShalkerBoxes,
                                                    val -> config.preventShalkerBoxes = val
                                            )
                                            .controller(opt -> BooleanControllerBuilder.create(opt)
                                                    .formatValue(val -> val ? Component.translatable("burnit.config.preventshalkerboxes.on") : Component.translatable("burnit.config.preventshalkerboxes.off"))
                                                    .coloured(true))
                                            .build())
                                    .build())
                            .group(ListOption.<String>createBuilder()
                                    .name(Component.translatable("burnit.config.preventcustomitems"))
                                    .description(OptionDescription.of(Component.translatable("burnit.config.preventcustomitems.desc")))
                                    .binding(defaults.preventCustomItems,
                                            () -> config.preventCustomItems,
                                            val -> config.preventCustomItems = val
                                    )
                                    .controller(StringControllerBuilder::create)
                                    .initial("")
                                    .build())
                            .build())
                    .category(ConfigCategory.createBuilder()
                            .name(Component.translatable("burnit.config.sounds"))
                            .tooltip(Component.translatable("burnit.config.sounds.tooltip"))
                            .group(OptionGroup.createBuilder()
                                    .name(Component.translatable("burnit.config.sounds.burn"))
                                    .description(OptionDescription.of(Component.translatable("burnit.config.sounds.burn.desc")))
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.burn.toggle"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.burn.toggle.desc")))
                                            .binding(
                                                    clientDefaults.burnToggle,
                                                    () -> clientConfig.burnToggle,
                                                    val -> clientConfig.burnToggle = val
                                            )
                                            .controller(opt -> BooleanControllerBuilder.create(opt)
                                                .formatValue(val -> val ? Component.translatable("burnit.config.sounds.burn.toggle.on") : Component.translatable("burnit.config.sounds.burn.toggle.off"))
                                                .coloured(true))
                                            .build())
                                    .option(Option.<String>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.burn.string"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.burn.string.desc")))
                                            .binding(
                                                    clientDefaults.burnString,
                                                    () -> clientConfig.burnString,
                                                    val -> clientConfig.burnString = val
                                            )
                                            .controller(StringControllerBuilder::create)
                                            .build())
                                    .option(Option.<Float>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.burn.pitch"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.burn.pitch.desc")))
                                            .binding(
                                                    clientDefaults.burnPitch,
                                                    () -> clientConfig.burnPitch,
                                                    val -> clientConfig.burnPitch = val
                                            )
                                            .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                    .range(0f, 2f)
                                                    .step(0.1f)
                                                    .formatValue(val -> Component.literal(String.format("%.1f", val))))
                                            .build())
                                    .option(Option.<Float>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.burn.volume"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.burn.volume.desc")))
                                            .binding(
                                                    clientDefaults.burnVolume,
                                                    () -> clientConfig.burnVolume,
                                                    val -> clientConfig.burnVolume = val
                                            )
                                            .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                    .range(0f, 1f)
                                                    .step(0.05f)
                                                    .formatValue(val -> Component.literal(String.format("%.2f", val))))
                                            .build())
                                    .build())
                            .group(OptionGroup.createBuilder()
                                    .name(Component.translatable("burnit.config.sounds.fire"))
                                    .description(OptionDescription.of(Component.translatable("burnit.config.sounds.fire.desc")))
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.fire.toggle"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.fire.toggle.desc")))
                                            .binding(
                                                    clientDefaults.fireToggle,
                                                    () -> clientConfig.fireToggle,
                                                    val -> clientConfig.fireToggle = val
                                            )
                                            .controller(opt -> BooleanControllerBuilder.create(opt)
                                                    .formatValue(val -> val ? Component.translatable("burnit.config.sounds.fire.toggle.on") : Component.translatable("burnit.config.sounds.fire.toggle.off"))
                                                    .coloured(true))
                                            .build())
                                    .option(Option.<String>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.fire.string"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.fire.string.desc")))
                                            .binding(
                                                    clientDefaults.fireString,
                                                    () -> clientConfig.fireString,
                                                    val -> clientConfig.fireString = val
                                            )
                                            .controller(StringControllerBuilder::create)
                                            .build())
                                    .option(Option.<Float>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.fire.pitch"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.fire.pitch.desc")))
                                            .binding(
                                                    clientDefaults.firePitch,
                                                    () -> clientConfig.firePitch,
                                                    val -> clientConfig.firePitch = val
                                            )
                                            .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                    .range(0f, 2f)
                                                    .step(0.1f)
                                                    .formatValue(val -> Component.literal(String.format("%.1f", val))))
                                            .build())
                                    .option(Option.<Float>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.fire.volume"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.fire.volume.desc")))
                                            .binding(
                                                    clientDefaults.fireVolume,
                                                    () -> clientConfig.fireVolume,
                                                    val -> clientConfig.fireVolume = val
                                            )
                                            .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                    .range(0f, 1f)
                                                    .step(0.05f)
                                                    .formatValue(val -> Component.literal(String.format("%.2f", val))))
                                            .build())
                                    .build())
                            .group(OptionGroup.createBuilder()
                                    .name(Component.translatable("burnit.config.sounds.extinguish"))
                                    .description(OptionDescription.of(Component.translatable("burnit.config.sounds.extinguish.desc")))
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.extinguish.toggle"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.extinguish.toggle.desc")))
                                            .binding(
                                                    clientDefaults.extinguishToggle,
                                                    () -> clientConfig.extinguishToggle,
                                                    val -> clientConfig.extinguishToggle = val
                                            )
                                            .controller(opt -> BooleanControllerBuilder.create(opt)
                                                    .formatValue(val -> val ? Component.translatable("burnit.config.sounds.extinguish.toggle.on") : Component.translatable("burnit.config.sounds.extinguish.toggle.off"))
                                                    .coloured(true))
                                            .build())
                                    .option(Option.<String>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.extinguish.string"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.extinguish.string.desc")))
                                            .binding(
                                                    clientDefaults.extinguishString,
                                                    () -> clientConfig.extinguishString,
                                                    val -> clientConfig.extinguishString = val
                                            )
                                            .controller(StringControllerBuilder::create)
                                            .build())
                                    .option(Option.<Float>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.extinguish.pitch"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.extinguish.pitch.desc")))
                                            .binding(
                                                    clientDefaults.extinguishPitch,
                                                    () -> clientConfig.extinguishPitch,
                                                    val -> clientConfig.extinguishPitch = val
                                            )
                                            .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                    .range(0f, 2f)
                                                    .step(0.1f)
                                                    .formatValue(val -> Component.literal(String.format("%.1f", val))))
                                            .build())
                                    .option(Option.<Float>createBuilder()
                                            .name(Component.translatable("burnit.config.sounds.extinguish.volume"))
                                            .description(OptionDescription.of(Component.translatable("burnit.config.sounds.extinguish.volume.desc")))
                                            .binding(
                                                    clientDefaults.extinguishVolume,
                                                    () -> clientConfig.extinguishVolume,
                                                    val -> clientConfig.extinguishVolume = val
                                            )
                                            .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                    .range(0f, 1f)
                                                    .step(0.05f)
                                                    .formatValue(val -> Component.literal(String.format("%.2f", val))))
                                            .build())
                                    .build())
                            .build())
                    .save(() -> {
                        ConfigLoader.HANDLER.save();
                        ConfigLoaderClient.HANDLER.save();
                    });
        }).generateScreen(parent);
    }
}