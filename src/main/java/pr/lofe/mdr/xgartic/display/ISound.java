package pr.lofe.mdr.xgartic.display;

import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class ISound extends DisplayObject {

    private final String sound;
    private final SoundCategory category;
    private final float volume, pitch;

    public ISound(String sound, SoundCategory category, float volume, float pitch) {
        this.sound = sound;
        this.category = category;
        this.volume = volume;
        this.pitch = pitch;
    }

    public ISound(String sound, float volume, float pitch) {
        this.sound = sound;
        this.category = SoundCategory.MASTER;
        this.volume = volume;
        this.pitch = pitch;
    }

    public ISound(String sound) {
        this.sound = sound;
        this.category = SoundCategory.MASTER;
        this.volume = 1;
        this.pitch = 1;
    }

    @Override
    public void show(Player... players) {
        for(Player player: players) {
            player.playSound(player, sound, category, volume, pitch);
        }
    }
}
