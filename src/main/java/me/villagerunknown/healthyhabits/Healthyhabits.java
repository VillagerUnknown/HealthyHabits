package me.villagerunknown.healthyhabits;

import me.villagerunknown.healthyhabits.feature.*;
import me.villagerunknown.platform.Platform;
import me.villagerunknown.platform.PlatformMod;
import me.villagerunknown.platform.manager.featureManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class Healthyhabits implements ModInitializer {
	
	public static PlatformMod<HealthyhabitsConfigData> MOD = null;
	public static String MOD_ID = null;
	public static Logger LOGGER = null;
	public static HealthyhabitsConfigData CONFIG = null;
	
	@Override
	public void onInitialize() {
		// # Register Mod w/ Platform
		MOD = Platform.register( "healthyhabits", Healthyhabits.class, HealthyhabitsConfigData.class );
		
		MOD_ID = MOD.getModId();
		LOGGER = MOD.getLogger();
		CONFIG = MOD.getConfig();
		
		// # Initialize Mod
		init();
	}
	
	private static void init() {
		Platform.init_mod( MOD );
		
		// # Activate Features
		featureManager.addFeature( "positiveAffirmations", positiveAffirmationsFeature::execute );
		featureManager.addFeature( "autoQuit", autoQuitFeature::execute );
		
		featureManager.addFeature( "eatingReminder", new eatingReminderFeature()::execute );
		featureManager.addFeature( "hearingReminder", new hearingReminderFeature()::execute );
		featureManager.addFeature( "hydrationReminder", new hydrationReminderFeature()::execute );
		featureManager.addFeature( "sedentaryReminder", new sedentaryReminderFeature()::execute );
		featureManager.addFeature( "sessionReminder", new sessionReminderFeature()::execute );
		featureManager.addFeature( "sightReminder", new sightReminderFeature()::execute );
	}
	
}
