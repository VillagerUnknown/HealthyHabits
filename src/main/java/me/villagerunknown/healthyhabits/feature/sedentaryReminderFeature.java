package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import net.minecraft.sound.SoundEvents;

public class sedentaryReminderFeature extends reminderFeature {
	
	public sedentaryReminderFeature() {
		super(
			"sedentary",
			"move around",
			Healthyhabits.CONFIG.enableSedentaryReminders,
			Healthyhabits.CONFIG.enableSedentaryReminderSounds,
			Healthyhabits.CONFIG.sedentaryReminderMessage,
			Healthyhabits.CONFIG.sedentaryReminderFrequencyInMinutes,
			Healthyhabits.CONFIG.sedentaryBreakInMinutes,
			Healthyhabits.CONFIG.sedentaryBreakShowsMenu,
			SoundEvents.ENTITY_SKELETON_DEATH
		);
	}
	
}
