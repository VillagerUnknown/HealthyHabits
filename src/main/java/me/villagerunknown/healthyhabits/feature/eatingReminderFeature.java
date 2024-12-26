package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class eatingReminderFeature extends reminderFeature {
	
	public eatingReminderFeature() {
		super(
			"eating",
			"have a snack",
			Healthyhabits.CONFIG.enableEatingReminders,
			Healthyhabits.CONFIG.enableEatingReminderSounds,
			Healthyhabits.CONFIG.eatingReminderMessage,
			Healthyhabits.CONFIG.eatingReminderFrequencyInMinutes,
			Healthyhabits.CONFIG.eatingBreakInMinutes,
			Healthyhabits.CONFIG.eatingBreakShowsMenu,
			SoundEvents.ENTITY_GENERIC_EAT
		);
	}
	
}
