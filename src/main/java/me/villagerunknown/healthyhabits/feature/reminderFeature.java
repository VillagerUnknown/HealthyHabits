package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import me.villagerunknown.platform.network.ShowPlayerGameMenuPayload;
import me.villagerunknown.platform.network.ToastMessagePayload;
import me.villagerunknown.platform.timer.TickTimer;
import me.villagerunknown.platform.util.EntityUtil;
import me.villagerunknown.platform.util.RegistryUtil;
import me.villagerunknown.platform.util.ToastUtil;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

abstract class reminderFeature {
	
	public static final Identifier TOTAL_BREAKS_ID = RegistryUtil.registerStat( "total_breaks", Healthyhabits.MOD_ID, StatFormatter.DEFAULT );
	public static final Identifier TOTAL_REMINDERS_ID = RegistryUtil.registerStat( "total_reminders", Healthyhabits.MOD_ID, StatFormatter.DEFAULT );
	
	protected String reminderType;
	protected String reminderAction;
	
	protected boolean enableReminders;
	protected boolean enableReminderSounds;
	protected String reminderMessages;
	protected int reminderFrequencyInMinutes;
	protected int breakInMinutes;
	protected boolean breakShowMenu;
	protected SoundEvent alertSound;
	
	protected Map<UUID, TickTimer> playerTimers = new HashMap<>();
	protected Map<UUID, TickTimer> breakTimers = new HashMap<>();
	
	public reminderFeature(
			String reminderType,
			String reminderAction,
			boolean enableReminders,
			boolean enableReminderSounds,
			String reminderMessages,
			int reminderFrequencyInMinutes,
			int breakInMinutes,
			boolean breakShowMenu,
			SoundEvent alertSound
	) {
		this.reminderType = reminderType;
		this.reminderAction = reminderAction;
		
		this.enableReminders = enableReminders;
		this.enableReminderSounds = enableReminderSounds;
		this.reminderMessages = reminderMessages;
		this.reminderFrequencyInMinutes = reminderFrequencyInMinutes;
		this.breakInMinutes = breakInMinutes;
		this.breakShowMenu = breakShowMenu;
		this.alertSound = alertSound;
	}
	
	public void execute() {
		// # Player joins the server
		ServerPlayConnectionEvents.JOIN.register((serverPlayNetworkHandler, packetSender, minecraftServer) -> {
			TickTimer timer = new TickTimer(reminderFrequencyInMinutes);
			playerTimers.put(serverPlayNetworkHandler.player.getUuid(), timer);
		});
		
		// # Server ticks
		ServerTickEvents.START_SERVER_TICK.register(minecraftServer -> {
			for(Map.Entry<UUID, TickTimer> playerData : playerTimers.entrySet()) {
				UUID playerUUID = playerData.getKey();
				TickTimer playerTimer = playerData.getValue();
				
				if(!breakTimers.containsKey(playerUUID)) {
					playerTimer.tick();
				} // if
				
				if(enableReminders && playerTimer.isAlarmActivated()) {
					ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayer(playerUUID);
					
					if( null != player ) {
						if(enableReminderSounds) {
							EntityUtil.playSound( player, alertSound, SoundCategory.MASTER, Healthyhabits.CONFIG.defaultSoundVolume, 1, true );
						} // if
						
						String title = reminderMessages.toUpperCase() + "!";
						String message;
						long duration = Healthyhabits.CONFIG.toastReminderDurationInSeconds * ToastUtil.TOAST_DURATION_SECOND;
						
						if (breakInMinutes > 0) {
							TickTimer breakTimer = new TickTimer(breakInMinutes);
							breakTimers.put(player.getUuid(), breakTimer);
							
							message = "This is your " + breakInMinutes + " minute " + reminderType + " break to " + reminderAction + "!";
							duration = breakInMinutes * ToastUtil.TOAST_DURATION_MINUTE;
							
							player.incrementStat( TOTAL_BREAKS_ID );
						} else {
							message = "This is your " + reminderFrequencyInMinutes + " minute " + reminderType + " break to " + reminderAction + "!";
							
							player.incrementStat( TOTAL_REMINDERS_ID );
						} // if, else
						
						ServerPlayNetworking.send( player, new ToastMessagePayload( title, message, duration ));
						
						if( breakShowMenu && minecraftServer.isSingleplayer() ) {
							ServerPlayNetworking.send( player, new ShowPlayerGameMenuPayload());
						} // if
						
						playerTimer.resetAlarmActivation();
					} // if
				} // if
			} // for
			
			if (!breakTimers.isEmpty()) {
				for (Map.Entry<UUID, TickTimer> breakData : breakTimers.entrySet()) {
					UUID playerUUID = breakData.getKey();
					TickTimer breakTimer = breakData.getValue();
					
					breakTimer.tick();
					
					if (breakTimer.isAlarmActivated()) {
						breakTimers.remove(playerUUID);
					} // if
				} // for
			} // if
		});
		
		// # Player leaves the server
		ServerPlayConnectionEvents.DISCONNECT.register((serverPlayNetworkHandler, minecraftServer) -> {
			playerTimers.remove(serverPlayNetworkHandler.player.getUuid());
		});
		
	}
	
}
