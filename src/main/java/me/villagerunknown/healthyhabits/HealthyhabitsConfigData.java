package me.villagerunknown.healthyhabits;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "villagerunknown-healthyhabits")
public class HealthyhabitsConfigData implements me.shedaniel.autoconfig.ConfigData {
	
	/**
	 * General
	 */
	
	@ConfigEntry.Category("General")
	public int toastReminderDurationInSeconds = 10;
	
	@ConfigEntry.Category("General")
	public float defaultSoundVolume = 0.5F;
	
	@ConfigEntry.Category("General")
	public boolean revealCoordinatesOnDeath = true;
	
	/**
	 * Affirmations
	 */
	
	@ConfigEntry.Category("Affirmations")
	public boolean enablePositiveAffirmations = true;
	
	@ConfigEntry.Category("Affirmations")
	public boolean enablePositiveAffirmationSounds = true;
	
	@ConfigEntry.Category("Affirmations")
	public int positiveAffirmationFrequencyInMinutes = 1;
	
	@ConfigEntry.Category("Affirmations")
	public int positiveAffirmationOnScreenInSeconds = 10;
	
	/**
	 * Auto-Quit
	 */
	
	@ConfigEntry.Category("Auto-Quit")
	public int autoQuitInMinutes = 0;
	
	@ConfigEntry.Category("Auto-Quit")
	public int autoQuitWarningInMinutes = 5;
	
	@ConfigEntry.Category("Auto-Quit")
	public boolean requireBedInteractionToQuit = true;
	
	@ConfigEntry.Category("Auto-Quit")
	public String autoQuitDisconnectMessage = "Your session has ended to improve your quality of life.";
	
	/**
	 * Eating
	 */
	
	@ConfigEntry.Category("Eating")
	public boolean enableEatingReminders = true;
	
	@ConfigEntry.Category("Eating")
	public boolean enableEatingReminderSounds = true;
	
	@ConfigEntry.Category("Eating")
	public int eatingReminderFrequencyInMinutes = 60;
	
	@ConfigEntry.Category("Eating")
	public int eatingBreakInMinutes = 5;
	
	@ConfigEntry.Category("Eating")
	public boolean eatingBreakShowsMenu = true;
	
	@ConfigEntry.Category("Eating")
	public String eatingReminderMessage = "Snack time";
	
	/**
	 * Hearing
	 */
	
	@ConfigEntry.Category("Hearing")
	public boolean enableHearingReminders = true;
	
	@ConfigEntry.Category("Hearing")
	public boolean enableHearingReminderSounds = true;
	
	@ConfigEntry.Category("Hearing")
	public int hearingReminderFrequencyInMinutes = 60;
	
	@ConfigEntry.Category("Hearing")
	public int hearingBreakInMinutes = 10;
	
	@ConfigEntry.Category("Hearing")
	public boolean hearingBreakShowsMenu = true;
	
	@ConfigEntry.Category("Hearing")
	public String hearingReminderMessage = "Reduce the volume";
	
	/**
	 * Hydration
	 */
	
	@ConfigEntry.Category("Hydration")
	public boolean enableHydrationReminders = true;
	
	@ConfigEntry.Category("Hydration")
	public boolean enableHydrationReminderSounds = true;
	
	@ConfigEntry.Category("Hydration")
	public int hydrationReminderFrequencyInMinutes = 15;
	
	@ConfigEntry.Category("Hydration")
	public int hydrationBreakInMinutes = 0;
	
	@ConfigEntry.Category("Hydration")
	public boolean hydrationBreakShowsMenu = true;
	
	@ConfigEntry.Category("Hydration")
	public String hydrationReminderMessage = "Drink time";
	
	/**
	 * Sedentary
	 */
	
	@ConfigEntry.Category("Sedentary")
	public boolean enableSedentaryReminders = true;
	
	@ConfigEntry.Category("Sedentary")
	public boolean enableSedentaryReminderSounds = true;
	
	@ConfigEntry.Category("Sedentary")
	public int sedentaryReminderFrequencyInMinutes = 60;
	
	@ConfigEntry.Category("Sedentary")
	public int sedentaryBreakInMinutes = 5;
	
	@ConfigEntry.Category("Sedentary")
	public boolean sedentaryBreakShowsMenu = true;
	
	@ConfigEntry.Category("Sedentary")
	public String sedentaryReminderMessage = "Stretch time";
	
	/**
	 * Session
	 */
	
	@ConfigEntry.Category("Session")
	public boolean enableSessionReminders = true;
	
	@ConfigEntry.Category("Session")
	public boolean enableSessionReminderSounds = true;
	
	@ConfigEntry.Category("Session")
	public int sessionReminderFrequencyInMinutes = 240;
	
	@ConfigEntry.Category("Session")
	public int sessionBreakInMinutes = 20;
	
	@ConfigEntry.Category("Session")
	public boolean sessionBreakShowsMenu = true;
	
	@ConfigEntry.Category("Session")
	public String sessionReminderMessage = "Touch grass";
	
	/**
	 * Sight
	 */
	
	@ConfigEntry.Category("Sight")
	public boolean enableSightReminders = true;
	
	@ConfigEntry.Category("Sight")
	public boolean enableSightReminderSounds = true;
	
	@ConfigEntry.Category("Sight")
	public int sightReminderFrequencyInMinutes = 20;
	
	@ConfigEntry.Category("Sight")
	public int sightBreakInMinutes = 1;
	
	@ConfigEntry.Category("Sight")
	public boolean sightBreakShowsMenu = true;
	
	@ConfigEntry.Category("Sight")
	public String sightReminderMessage = "Look away";
	
}
