package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import net.minecraft.sound.SoundEvents;

public class sessionReminderFeature extends reminderFeature {
	
	public sessionReminderFeature() {
		super(
			"session",
			"do other things",
			Healthyhabits.CONFIG.enableSessionReminders,
			Healthyhabits.CONFIG.enableSessionReminderSounds,
			Healthyhabits.CONFIG.sessionReminderMessage,
			Healthyhabits.CONFIG.sessionReminderFrequencyInMinutes,
			Healthyhabits.CONFIG.sessionBreakInMinutes,
			Healthyhabits.CONFIG.sessionBreakShowsMenu,
			SoundEvents.BLOCK_GRASS_PLACE
		);
	}
	
}
