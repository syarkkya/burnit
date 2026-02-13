package me.syarkkya.burnit;

import me.syarkkya.burnit.config.ConfigLoaderClient;
import net.fabricmc.api.ClientModInitializer;

public class BurnItClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ConfigLoaderClient.init();
	}
}