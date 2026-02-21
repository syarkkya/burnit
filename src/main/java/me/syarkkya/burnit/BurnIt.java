package me.syarkkya.burnit;

import me.syarkkya.burnit.config.ConfigLoader;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.world.inventory.AbstractContainerMenu;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class BurnIt implements ModInitializer {
	public static final Map<AbstractContainerMenu, Boolean> isFireSet = Collections.synchronizedMap(new WeakHashMap<>());
	public static final ResourceLocation ITEMS_BURNED = ResourceLocation.fromNamespaceAndPath("burnit", "items_burned");

	@Override
	public void onInitialize() {
		ConfigLoader.init();

		Registry.register(BuiltInRegistries.CUSTOM_STAT, "items_burned", ITEMS_BURNED);
		Stats.CUSTOM.get(ITEMS_BURNED, StatFormatter.DEFAULT);
	}
}