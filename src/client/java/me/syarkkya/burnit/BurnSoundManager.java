package me.syarkkya.burnit;

import me.syarkkya.burnit.config.ConfigLoaderClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import java.util.ArrayList;
import java.util.List;

public class BurnSoundManager {
    public static final List<BurnSound> activeSounds = new ArrayList<>();
    private static SoundEvent getSound(String id, SoundEvent fallback) {
        try {
            return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(id));
        } catch (Exception e) {
            return fallback;
        }
    }

    public static void startSounds(Player player) {
        ConfigLoaderClient cfg = ConfigLoaderClient.getConfig();
        if (cfg.fireToggle && activeSounds.isEmpty()) {
            SoundEvent fireEv = getSound(cfg.fireString, net.minecraft.sounds.SoundEvents.FIRE_AMBIENT);
            BurnSound s1 = new BurnSound(player, fireEv, cfg.firePitch, cfg.fireVolume, true);
            BurnSound s1_double = new BurnSound(player, fireEv, cfg.firePitch * 1.5f, cfg.fireVolume, true);

            Minecraft.getInstance().getSoundManager().play(s1);
            Minecraft.getInstance().getSoundManager().play(s1_double);

            activeSounds.add(s1);
            activeSounds.add(s1_double);
        }
        if (cfg.burnToggle) {
            SoundEvent burnEv = getSound(cfg.burnString, net.minecraft.sounds.SoundEvents.LAVA_EXTINGUISH);
            BurnSound s2 = new BurnSound(player, burnEv, cfg.burnPitch, cfg.burnVolume, false);
            Minecraft.getInstance().getSoundManager().play(s2);
        }
    }

    public static void stopSounds(Player player) {
        if (activeSounds.isEmpty()) return;
        activeSounds.forEach(BurnSound::forceStop);
        activeSounds.clear();
        ConfigLoaderClient cfg = ConfigLoaderClient.getConfig();
        if (cfg.extinguishToggle) {
            SoundEvent extEv = getSound(cfg.extinguishString, net.minecraft.sounds.SoundEvents.LAVA_EXTINGUISH);
            player.level().playSound(player, player.blockPosition(), extEv,
                    SoundSource.PLAYERS, cfg.extinguishVolume, cfg.extinguishPitch);
        }
    }
}