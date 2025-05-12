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
		// # Initialize mod with Platform
		Platform.init_mod( MOD );
		
		// # Activate Features
		featureManager.addFeature( "positive-affirmations", positiveAffirmationsFeature::execute );
		featureManager.addFeature( "auto-quit", autoQuitFeature::execute );
		
		featureManager.addFeature( "eating-reminder", new eatingReminderFeature()::execute );
		featureManager.addFeature( "hearing-reminder", new hearingReminderFeature()::execute );
		featureManager.addFeature( "hydration-reminder", new hydrationReminderFeature()::execute );
		featureManager.addFeature( "sedentary-reminder", new sedentaryReminderFeature()::execute );
		featureManager.addFeature( "session-reminder", new sessionReminderFeature()::execute );
		featureManager.addFeature( "sight-reminder", new sightReminderFeature()::execute );
		
		// # Load Features
		featureManager.loadFeatures();
	}
	
}
