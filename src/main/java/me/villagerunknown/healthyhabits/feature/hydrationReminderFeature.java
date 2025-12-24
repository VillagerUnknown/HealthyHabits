package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import net.minecraft.sound.SoundEvents;

public class hydrationReminderFeature extends reminderFeature {
	
	public hydrationReminderFeature() {
		super(
			"hydration",
			"have a drink",
			Healthyhabits.CONFIG.enableHydrationReminders,
			Healthyhabits.CONFIG.enableHydrationReminderSounds,
			Healthyhabits.CONFIG.hydrationReminderMessage,
			Healthyhabits.CONFIG.hydrationReminderFrequencyInMinutes,
			Healthyhabits.CONFIG.hydrationBreakInMinutes,
			Healthyhabits.CONFIG.hydrationBreakShowsMenu,
			SoundEvents.ENTITY_GENERIC_DRINK.value()
		);
	}
	
}
