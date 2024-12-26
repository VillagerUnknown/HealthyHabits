package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import me.villagerunknown.platform.timer.TickTimer;
import me.villagerunknown.platform.util.ClientUtil;
import me.villagerunknown.platform.util.ToastUtil;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class autoQuitFeature {
	
	static Map<UUID, TickTimer> playerTimers = new HashMap<>();
	
	public static void execute() {
		// # Player joins the server
		ServerPlayConnectionEvents.JOIN.register((serverPlayNetworkHandler, packetSender, minecraftServer) -> {
			TickTimer timer = new TickTimer( Healthyhabits.CONFIG.autoQuitInMinutes );
			playerTimers.put( serverPlayNetworkHandler.player.getUuid(), timer );
		});
		
		// # Server ticks
		ServerTickEvents.START_SERVER_TICK.register(minecraftServer -> {
			for (Map.Entry<UUID, TickTimer> playerData : playerTimers.entrySet()) {
				UUID playerUUID = playerData.getKey();
				TickTimer playerTimer = playerData.getValue();
				
				ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayer( playerUUID );
				
				playerTimer.tick();
				
				if( null != player && Healthyhabits.CONFIG.autoQuitInMinutes > 0 ) {
					String title = "Auto-Quit";
					String message;
					Long duration;
					
					if (null == playerTimer.getData( "config_minute_warning" ) && Healthyhabits.CONFIG.autoQuitWarningInMinutes > 0 && playerTimer.getMinutesUntilAlarm() <= Healthyhabits.CONFIG.autoQuitWarningInMinutes && playerTimer.getMinutesUntilAlarm() > 1) {
						if( Healthyhabits.CONFIG.requireBedInteractionToQuit ) {
							message = "Your session ends in less than " + Healthyhabits.CONFIG.autoQuitWarningInMinutes + " minutes. Interact with a bed to quit.";
						} else {
							message = "Your session ends in less than " + Healthyhabits.CONFIG.autoQuitWarningInMinutes + " minutes.";
						} // if, else
						
						duration = ((Healthyhabits.CONFIG.autoQuitWarningInMinutes - 1) * ToastUtil.TOAST_DURATION_MINUTE) - (ToastUtil.TOAST_DURATION_SECOND * 2);
						ToastUtil.showToast( player, title, message, duration );
						playerTimer.putData("config_minute_warning",true);
						
						positiveAffirmationsFeature.sendMessage( positiveAffirmationsFeature.positiveAffirmationsForReflection, player );
					} // if
					
					if (null == playerTimer.getData( "1_minute_warning" ) && Healthyhabits.CONFIG.autoQuitWarningInMinutes > 0 && playerTimer.getMinutesUntilAlarm() <= 1) {
						if( Healthyhabits.CONFIG.requireBedInteractionToQuit ) {
							message = "Your session ends in less than 1 minute. Interact with a bed to quit.";
						} else {
							message = "Your session ends in less than 1 minute.";
						} // if, else
						
						duration = ToastUtil.TOAST_DURATION_MINUTE;
						ToastUtil.showToast( player, title, message, duration );
						playerTimer.putData("1_minute_warning",true);
						
						positiveAffirmationsFeature.sendMessage( positiveAffirmationsFeature.positiveAffirmationsForReflection, player );
					} // if
					
					if (playerTimer.isAlarmActivated() && !Healthyhabits.CONFIG.requireBedInteractionToQuit) {
						shutdown(player);
						playerTimer.resetAlarmActivation();
					} // if
				} // if
			}
		});
		
		// # Player leaves the server
		ServerPlayConnectionEvents.DISCONNECT.register((serverPlayNetworkHandler, minecraftServer) -> {
			playerTimers.remove( serverPlayNetworkHandler.player.getUuid() );
		});
		
		// Player interacts with bed
		EntitySleepEvents.ALLOW_SLEEP_TIME.register((playerEntity, blockPos, b) -> {
			ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
			
			if( Healthyhabits.CONFIG.autoQuitInMinutes > 0 && Healthyhabits.CONFIG.requireBedInteractionToQuit ) {
				TickTimer playerTimer = playerTimers.get( player.getUuid() );
				
				if( playerTimer.getMinutesUntilAlarm() <= Healthyhabits.CONFIG.autoQuitWarningInMinutes || playerTimer.isAlarmActivated() ) {
					shutdown( player );
					playerTimer.resetAlarmActivation();
				} // if
			} // if
			
			return ActionResult.PASS;
		});
	}
	
	private static void shutdown( ServerPlayerEntity player ) {
		MinecraftServer server = player.getServer();
		if( null != server ) {
			if (server.isSingleplayer()) {
				ClientUtil.showMainMenu( player );
			} else {
				ClientUtil.disconnect( player, Healthyhabits.CONFIG.autoQuitDisconnectMessage );
			} // if, else
		} // if
	}
	
}
