package me.villagerunknown.healthyhabits;

import me.villagerunknown.healthyhabits.feature.*;
import me.villagerunknown.platform.Platform;
import me.villagerunknown.platform.PlatformMod;
import me.villagerunknown.platform.manager.featureManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class Healthyhabits implements ModInitializer {
	
	public static PlatformMod<HealthyhabitsConfigData> MOD = Platform.register( "healthyhabits", Healthyhabits.class, HealthyhabitsConfigData.class );
	public static String MOD_ID = MOD.getModId();
	public static Logger LOGGER = MOD.getLogger();
	public static HealthyhabitsConfigData CONFIG = MOD.getConfig();
	
	@Override
	public void onInitialize() {
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
		
		featureManager.loadFeatures();
	}
	
}
