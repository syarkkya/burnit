package me.syarkkya.burnit;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class BurnSound extends AbstractTickableSoundInstance {
    private final Player player;

    public BurnSound(Player player, SoundEvent soundEvent, float pitch, float volume, boolean looping) {
        super(soundEvent, SoundSource.PLAYERS, player.level().random);
        this.player = player;
        this.looping = looping;
        this.delay = 0;
        this.pitch = pitch;
        this.volume = volume;
    }

    @Override
    public void tick() {
        if (this.player.isRemoved() || (!this.looping && this.isStopped())) {
            this.stop();
        } else {
            this.x = (float) this.player.getX();
            this.y = (float) this.player.getY();
            this.z = (float) this.player.getZ();
        }
    }

    public void forceStop() {
        this.stop();
    }
}