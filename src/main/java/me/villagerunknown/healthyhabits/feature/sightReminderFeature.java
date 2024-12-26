package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class sightReminderFeature extends reminderFeature {
	
	public sightReminderFeature() {
		super(
			"sight",
			"look away",
			Healthyhabits.CONFIG.enableSightReminders,
			Healthyhabits.CONFIG.enableSightReminderSounds,
			Healthyhabits.CONFIG.sightReminderMessage,
			Healthyhabits.CONFIG.sightReminderFrequencyInMinutes,
			Healthyhabits.CONFIG.sightBreakInMinutes,
			Healthyhabits.CONFIG.sightBreakShowsMenu,
			SoundEvents.ITEM_SPYGLASS_USE
		);
	}
	
}
