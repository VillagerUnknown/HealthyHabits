package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class hearingReminderFeature extends reminderFeature {
	
	public hearingReminderFeature(){
		super(
			"hearing",
			"reduce the volume",
			Healthyhabits.CONFIG.enableHearingReminders,
			Healthyhabits.CONFIG.enableHearingReminderSounds,
			Healthyhabits.CONFIG.hearingReminderMessage,
			Healthyhabits.CONFIG.hearingReminderFrequencyInMinutes,
			Healthyhabits.CONFIG.hearingBreakInMinutes,
			Healthyhabits.CONFIG.hearingBreakShowsMenu,
			SoundEvents.BLOCK_BELL_RESONATE
		);
	}
	
}
