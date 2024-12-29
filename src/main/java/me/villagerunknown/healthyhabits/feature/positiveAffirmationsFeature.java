package me.villagerunknown.healthyhabits.feature;

import me.villagerunknown.healthyhabits.Healthyhabits;
import me.villagerunknown.platform.builder.StringsListBuilder;
import me.villagerunknown.platform.timer.TickTimer;
import me.villagerunknown.platform.util.*;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.StatFormatter;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.*;

import static me.villagerunknown.healthyhabits.Healthyhabits.MOD_ID;

public class positiveAffirmationsFeature {
	
	public static final List<String> POSITIVE_AFFIRMATIONS = List.of(
			"You’re doing great!",
			"Keep up the great job!",
			"You’re doing amazing — keep going!",
			"Every step you take brings you closer to your goals!",
			"You have the power to overcome any challenge.",
			"Small progress is still progress. Keep moving forward!",
			"Your creativity knows no bounds — keep building!",
			"Remember, you’re stronger than you think.",
			"You’ve got this! One block at a time.",
			"Success is built one action at a time. Keep going!",
			"Patience and perseverance will always lead you to success.",
			"Trust yourself — you’re capable of achieving great things!",
			"You are worthy of all the good things coming your way.",
			"Believe in yourself — you’ve got what it takes!",
			"You are enough, just as you are.",
			"You are unique and your perspective matters!",
			"Your potential is limitless — don’t stop now!",
			"You have the strength to face anything that comes your way.",
			"You are doing better than you think — trust the process.",
			"No challenge is too big for you to conquer.",
			"Today is another opportunity for growth. Seize it!",
			"You are capable of achieving greatness — keep believing in yourself!",
			"Take a deep breath — you’re doing great.",
			"Slow down and enjoy the journey, step by step.",
			"It’s okay to take a break — relaxation helps you grow stronger.",
			"You are in control of your progress. Breathe and enjoy the moment.",
			"Let go of stress and let peace guide you.",
			"Remember to breathe. You are safe and you are enough.",
			"It’s okay to pause and reset — you deserve it.",
			"You’ve got the strength to overcome any setback. Stay calm.",
			"Relax and trust the process — everything will fall into place.",
			"Breathe deeply and allow yourself to feel at peace.",
			"Every challenge is an opportunity to grow. Keep pushing forward!",
			"You’re making progress even on the days it feels slow.",
			"Your hard work will pay off — keep building your dreams!",
			"The best is yet to come — keep moving forward!",
			"Your determination will turn obstacles into stepping stones.",
			"Don’t forget to celebrate every small win. You deserve it!",
			"Great things take time — keep putting in the effort.",
			"Believe in your power to create and shape your world.",
			"You’re on the right path — keep going!",
			"The effort you put in today will create the future you want.",
			"Take care of yourself — you’re important.",
			"It’s okay to take breaks. Your well-being comes first.",
			"Balance is key — make sure to rest and recharge.",
			"You deserve time to relax and unwind. Don’t forget that.",
			"You are worthy of self-love and self-care. Make time for it.",
			"Take a moment to appreciate how far you’ve come.",
			"You are deserving of peace and tranquility in every moment.",
			"Self-care isn’t selfish — it’s essential. Remember to rest.",
			"Every step of your journey is worth celebrating.",
			"Remember to be kind to yourself — you’re doing your best!",
			"Failures are just lessons in disguise — you’ll rise stronger.",
			"Each setback is a setup for an even greater comeback.",
			"You can overcome any challenge. Keep pushing forward!",
			"It’s okay to stumble. The important thing is to keep going.",
			"Every obstacle is an opportunity to learn and grow.",
			"You’re resilient — nothing can stop you if you don’t give up.",
			"Mistakes are part of the journey. You’re learning with every step.",
			"Keep going, even when it’s tough. You’re stronger than you know.",
			"Challenges are simply opportunities to show your strength.",
			"You’ve faced obstacles before and come out on top — you can do it again!",
			"Your hard work is paying off. Enjoy the fruits of your labor!",
			"You’re creating something amazing — keep going!",
			"Your success is the result of your dedication and perseverance.",
			"Look at how far you’ve come. Keep shining!",
			"Every achievement, no matter how small, is a step toward greatness.",
			"You are creating your own path to success — keep walking it!",
			"Your effort has led to something wonderful. You should be proud!",
			"You’re accomplishing great things. Don’t forget to celebrate yourself!",
			"Focus on the present — it’s where the magic happens.",
			"Take a moment to pause and enjoy the game as it is now.",
			"You are exactly where you need to be right now. Trust the journey.",
			"Enjoy every step of your adventure — each moment is special.",
			"The journey is just as important as the destination. Stay present.",
			"Breathe in the present moment — it’s all you need.",
			"The here and now is all you can control. Enjoy this moment.",
			"Relax and take in your surroundings. You’ve earned this peace.",
			"The present moment is full of opportunities. Embrace it!",
			"Take a deep breath and appreciate how far you’ve come."
	);
	
	public static final List<String> POSITIVE_AFFIRMATIONS_FOR_SLEEP = List.of(
			"I am winding down and preparing my mind and body for rest.",
			"I deserve to relax and take care of myself before sleep.",
			"The day is done, and now it’s time to let go of everything.",
			"I am grateful for the moments of today, and I release any stress or worries.",
			"I trust that tomorrow will bring new opportunities and experiences.",
			"My body knows exactly how to relax, and I allow it to do so now.",
			"I create space for peace and calmness to surround me as I prepare for sleep.",
			"Each breath I take helps me feel more at ease and ready for rest.",
			"I let go of all the energy from today and make room for deep, rejuvenating sleep.",
			"Sleep is a time for my body and mind to recharge, and I welcome it with open arms.",
			"I am grateful for this day and let go of all its challenges.",
			"I release all thoughts and embrace the peacefulness of the night.",
			"I am safe and secure, surrounded by peace and warmth.",
			"I trust that my body knows how to rest and recharge.",
			"I am releasing tension with each breath I take.",
			"The night is a time for healing, and I welcome it fully.",
			"I give myself permission to rest and relax deeply.",
			"My mind is slowing down, and I welcome the stillness.",
			"I honor my need for rest and prepare my body for deep sleep.",
			"I am deserving of peaceful, restorative sleep.",
			"I am releasing all the stress from today, allowing my body to relax.",
			"My body is calm, and my mind is at ease.",
			"As I rest, I allow my energy to be restored and renewed.",
			"I choose peace over worry as I prepare for a good night’s sleep.",
			"I embrace the quiet of the night and find comfort in it.",
			"I let go of any distractions and focus only on my breath.",
			"I trust that everything I need will be there for me tomorrow.",
			"Tonight, I will sleep deeply and wake up refreshed.",
			"I release the need to control and allow myself to simply be.",
			"I am letting go of the events of today and focusing on relaxation.",
			"I am thankful for the opportunity to rest my body and mind.",
			"I am wrapped in a cocoon of peace, preparing for deep, restful sleep.",
			"My mind is clear, calm, and peaceful as I approach sleep.",
			"I trust in the process of rest and surrender to it fully.",
			"I give myself the gift of relaxation and serenity before sleep.",
			"I am letting go of today and preparing for a new tomorrow.",
			"I deserve deep rest and peaceful sleep.",
			"I am at peace with myself and my surroundings.",
			"Tonight, I let go of all tension in my body.",
			"I am grateful for the gift of rest, and I embrace it with open arms.",
			"I breathe in calmness and exhale the stresses of the day.",
			"I am thankful for this time to nurture my body and soul with rest.",
			"My body is ready to relax, and my mind is ready to quiet down.",
			"I am deserving of peaceful moments as I transition into sleep.",
			"I trust in the peaceful rhythm of the night.",
			"Sleep comes easily to me when I let go of the day.",
			"I welcome the stillness of the night with a calm and open heart.",
			"Each moment of rest renews me physically and emotionally.",
			"I am worthy of taking time to care for myself and my well-being.",
			"I trust that my mind and body are working together to help me sleep.",
			"I release all negativity and welcome positivity into my space.",
			"My thoughts are slowing, and my body is relaxing.",
			"I am grateful for the quiet moments before sleep that soothe my spirit.",
			"I embrace the peaceful rhythm of each breath as I prepare for sleep.",
			"I am letting go of all my responsibilities for now and focusing on rest.",
			"I release any remaining stress or tension from my day.",
			"I allow myself to be still and let go of all thoughts.",
			"As I breathe deeply, my body relaxes, and my mind settles into calmness.",
			"I am grateful for the opportunity to restore my energy tonight.",
			"I am grateful for this moment of peace before I sleep.",
			"I trust that everything is unfolding as it should, and I can rest easy.",
			"My mind is filled with thoughts of peace and relaxation as I prepare for bed.",
			"I am giving myself permission to let go of everything and rest deeply.",
			"I am at peace with today, and I release it as I prepare for sleep.",
			"I am grateful for the comfort of my bed and the security it provides.",
			"I choose to focus on my breath, allowing everything else to fade away.",
			"I let go of the need to be productive and give myself permission to rest.",
			"I am grateful for this moment of stillness as I wind down for the night.",
			"I am free from any anxiety, and I invite relaxation into my being.",
			"I am ready to release the day’s worries and embrace peaceful rest.",
			"I trust that the night will bring me the deep sleep I need.",
			"I trust that tomorrow will unfold beautifully, and I am ready to rest.",
			"I am giving myself the time and space I need to rest and recharge.",
			"I am safe and grounded, ready for a peaceful night’s sleep.",
			"I am grateful for the quiet moments that bring calmness to my mind.",
			"I allow my mind to slow down and prepare for a night of restful sleep.",
			"I am worthy of rest, and I embrace this time to care for myself.",
			"I release any tension in my body and allow myself to fully relax.",
			"I am slowing my thoughts, allowing peace to fill my mind.",
			"I am grateful for the healing that happens while I sleep.",
			"I am ready to surrender to the calm and peaceful energy of sleep.",
			"I welcome the stillness of the night and trust that it will rejuvenate me.",
			"I choose to let go of all thoughts that keep me awake and invite rest.",
			"I release any worries, knowing they can be dealt with tomorrow.",
			"I am embracing the quiet and calm that will guide me to sleep.",
			"My body is a vessel of peace, and it is ready to rest.",
			"I honor the need for sleep and let go of the day with gratitude.",
			"As I lie down, I trust that my body will find its natural rhythm for rest.",
			"I am worthy of a peaceful, uninterrupted sleep.",
			"I let go of my to-do list and allow myself to relax deeply.",
			"I am fully present in the moment, releasing everything that no longer serves me.",
			"I am grateful for this peaceful space and time to rest.",
			"My body is ready to sleep, and my mind is calm and clear.",
			"I release the busyness of the day and embrace the serenity of the night.",
			"I choose rest over worry, and I give myself permission to relax.",
			"I trust that tonight will bring me the rest I need to feel rejuvenated.",
			"I embrace the peace of the night and let go of any lingering tension.",
			"Sleep is a natural, healing process, and I trust in its power.",
			"I am grateful for the opportunity to rest, heal, and restore my energy.",
			"I am surrounded by peace, and I surrender to the calm of the night."
	);
	
	public static final List<String> POSITIVE_AFFIRMATIONS_FOR_DEATH = List.of(
			"You’ve faced defeat, but it’s not the end. You’re stronger for it!",
			"Every setback is just another opportunity to grow. Get back up!",
			"You’re not defined by this moment. Keep pushing forward — success is on the horizon.",
			"Failure is just the first step toward mastery. You’ll do better next time!",
			"Mistakes are proof that you’re trying. Keep going, you’ll get it next time!",
			"This is just part of the journey. Every failure brings you closer to victory.",
			"It’s not how many times you fall, it’s how many times you rise. You got this!",
			"Don’t worry, you’ll come back stronger. Every defeat is a lesson.",
			"Take a deep breath — you’ll be back on your feet in no time!",
			"Every time you fall, you learn. Keep going — you’re improving with every step!",
			"This setback is just temporary — you’ll be back at it and better than before!",
			"You’re doing great, and setbacks like this won’t stop you. Try again!",
			"Remember, every defeat is just a lesson in disguise. You’ve got this!",
			"You’ve faced challenges before and overcome them. This is no different!",
			"You’ve got the strength and determination to bounce back. Keep fighting!",
			"Each defeat teaches you something valuable. You’re becoming even better!",
			"Take a breather, regroup, and get back in there — you’re unstoppable!",
			"You’ve got the perseverance and skill to succeed. This is just a temporary setback!",
			"Don’t let this stop you — you’re capable of anything you set your mind to!",
			"You’ve learned something important. Now, get ready to conquer it next time!",
			"It’s okay to fail — it’s just a step on the path to success.",
			"Be kind to yourself. You’re doing amazing, and this is part of the process.",
			"Everyone makes mistakes. What matters is that you keep going.",
			"You gave it your best shot. Now take a moment to regroup and try again.",
			"It’s okay to take your time. The journey is as important as the destination.",
			"You are doing better than you think. Don’t be hard on yourself.",
			"Failure isn’t final. It’s just an opportunity to try again with more wisdom.",
			"You’re learning and growing with every challenge. Keep going!",
			"Every death is a lesson — you’re building resilience and strength!",
			"You’re a champion for trying. Take your time, and you’ll nail it next time!",
			"A little setback won’t hold you back. You’re on your way to success!",
			"Stay focused and keep your head high — you’re almost there!",
			"You’ve got the skills and the determination to succeed. Don’t stop now!",
			"It’s just a small setback in the grand adventure. Keep going strong!",
			"Your journey isn’t over yet — keep your spirit high and keep going!",
			"You’ve got a strong heart and an even stronger will. Keep fighting!",
			"You may have fallen, but you’re getting stronger every time you rise.",
			"The world is full of challenges — but you’ve got the power to overcome them!",
			"Every step forward is a victory. Keep pushing forward!",
			"One setback doesn’t define your journey. Stay determined, and you’ll prevail!",
			"Every defeat is a new lesson. Keep learning, and you’ll keep improving.",
			"You are gaining valuable experience with each challenge you face.",
			"This is just one challenge. There will be many more victories ahead!",
			"What seems like a failure is just a stepping stone to greatness.",
			"The more you try, the better you get. Keep practicing and learning!",
			"The best players don’t avoid failure — they learn from it and come back stronger.",
			"You’re building strength and skill with every setback. Keep at it!",
			"Every challenge you face is an opportunity to improve. Keep going!",
			"You’ve faced challenges before and overcome them. This is no different.",
			"Every loss is a lesson. The more you learn, the closer you get to success!",
			"You’re on a journey, and every step, even the tough ones, brings you closer to success.",
			"Don’t give up — you’re one step away from a breakthrough!",
			"You’ve come this far, and you’re capable of achieving even more!",
			"Even the greatest adventurers fail sometimes. What matters is that you keep trying.",
			"The path to success is never straight — keep moving forward!",
			"You’ve overcome so much already. This challenge is just another step.",
			"Stay determined. The more you push, the closer you get to your goal.",
			"You are resilient, and nothing can stop you from reaching your goal.",
			"Even if you fall, you always rise again. Keep going strong!",
			"Every time you pick yourself up, you get closer to victory. You’re doing great!",
			"This is just a chapter of your story. The next one will be even better!",
			"Your perseverance is inspiring. Keep going, your victory is near!",
			"Failure is just a part of the journey. Victory is waiting for you!",
			"You’re so much closer than you think — keep your head up!",
			"The best is yet to come. Keep pushing forward!",
			"The path to victory is never easy, but you’re making it!",
			"Success is built on persistence. You’re doing the work — it will pay off!",
			"Your determination is stronger than any setback. Keep going!",
			"This is just one battle, not the end of the war. Keep fighting!",
			"You’ve got everything you need to succeed. This death is just part of the adventure."
	);
	
	public static final List<String> POSITIVE_AFFIRMATIONS_FOR_REFLECTION = List.of(
			"I made meaningful progress today, and my efforts are valuable.",
			"I dedicated my time and energy to creating something important.",
			"Each task I completed brings me closer to my goals.",
			"I am proud of the work I accomplished and the focus I maintained.",
			"I showed up, gave my best, and made the most of this session.",
			"The work I completed today reflects my dedication and growth.",
			"I have the power to make a positive impact with my efforts.",
			"Every step I take builds momentum toward achieving my larger vision.",
			"I trust in my abilities and the progress I’ve made so far.",
			"The work I’ve done today is a reflection of my commitment and discipline.",
			"I am constantly improving, and today was a step forward.",
			"I gave my best effort, and that is enough.",
			"I am grateful for the opportunity to work and contribute today.",
			"I celebrate the progress I’ve made, no matter how small it may seem.",
			"I trust the process, and I am proud of what I’ve achieved today.",
			"I created something unique and personal today.",
			"Every step I took today was a step toward building my vision.",
			"I made significant progress in the world I’m shaping and exploring.",
			"The challenges I faced today only made me stronger and more creative.",
			"I’ve transformed my surroundings with my own hands and ideas.",
			"I trust my instincts and creativity to guide me through new experiences.",
			"Each decision I make brings me closer to the world I envision.",
			"I learned something new and used that knowledge to improve my journey.",
			"I’ve crafted tools and resources that help me thrive in my environment.",
			"Every resource I gather is a step toward creating something extraordinary.",
			"I explored new places and encountered new opportunities for growth.",
			"I’ve faced challenges head-on and come out more resilient and resourceful.",
			"The world around me is constantly evolving because of my efforts.",
			"I’m building a legacy with every creation and every choice I make.",
			"The paths I’ve forged and the structures I’ve built reflect my dedication and vision.",
			"Every adventure I embark on brings me closer to mastering my surroundings.",
			"The obstacles I encounter are just opportunities for me to grow stronger and more skilled.",
			"I trust the process of creation, knowing that every step matters.",
			"I’m proud of how I adapt to new challenges and find innovative solutions.",
			"The progress I made today is a testament to my perseverance and imagination.",
			"I am proud of the unique environment I’ve shaped through my efforts.",
			"Each day, my skills grow, and my world becomes more vibrant and exciting.",
			"I’ve turned challenges into opportunities, building a better world with each action.",
			"Every project I start is a chance to express my creativity and vision.",
			"I trust my decisions and know they are leading me to greater achievements.",
			"I’ve mastered new techniques and applied them to make my world even more impressive.",
			"My creations are reflections of my imagination and determination.",
			"I embrace the unknown, knowing it holds endless opportunities for discovery and growth.",
			"I am proud of my resilience—no obstacle is too big to overcome.",
			"My actions today have set the stage for even greater accomplishments tomorrow.",
			"With every resource I gather, I’m preparing myself for bigger and better things.",
			"I’ve made my surroundings a place of beauty and functionality through my hard work.",
			"I am constantly evolving, adapting to new challenges with confidence and creativity.",
			"The progress I make is always meaningful, no matter how small the steps may seem.",
			"I am resourceful and inventive, always finding new ways to improve my world.",
			"I’ve created a space that is a true reflection of my dreams and ambitions.",
			"Every action I take today brings me closer to completing my grand vision.",
			"I’ve learned valuable lessons through both successes and setbacks, making me wiser.",
			"I trust the process of building and know that every piece contributes to something greater.",
			"I am an architect of my environment, and every creation adds to my growing legacy.",
			"I made the most of my time today, and the progress is evident in the world around me.",
			"I’ve faced obstacles, but I adapted and completed everything I set out to do.",
			"The work I’ve done today has laid a strong foundation for future successes.",
			"I can see how my efforts today are already paying off in the world I’m building.",
			"The results of my work today show the power of consistent focus and creativity.",
			"I’ve explored new areas and uncovered valuable resources that will help me in the future.",
			"I’m amazed by what I’ve built and accomplished in such a short amount of time.",
			"Every task I completed today has moved me closer to the bigger picture I’m creating.",
			"I used my time wisely and made the most out of every minute spent creating.",
			"In a short amount of time, I’ve transformed my surroundings into something meaningful.",
			"I’ve faced challenges head-on and turned them into valuable learning experiences.",
			"I’ve gathered the resources I need and used them effectively to improve my world.",
			"The work I’ve done today is a clear reflection of my dedication and skill.",
			"I’ve made real progress today, and I can see the results of my hard work."
	);
	
	public static StringsListBuilder positiveAffirmations = new StringsListBuilder( Healthyhabits.MOD_ID + "-affirmations.json", POSITIVE_AFFIRMATIONS );
	public static StringsListBuilder positiveAffirmationsForSleep = new StringsListBuilder( Healthyhabits.MOD_ID + "-affirmations-sleep.json", POSITIVE_AFFIRMATIONS_FOR_SLEEP );
	public static StringsListBuilder positiveAffirmationsForDeath = new StringsListBuilder( Healthyhabits.MOD_ID + "-affirmations-death.json", POSITIVE_AFFIRMATIONS_FOR_DEATH );
	public static StringsListBuilder positiveAffirmationsForReflection = new StringsListBuilder( Healthyhabits.MOD_ID + "-affirmations-reflection.json", POSITIVE_AFFIRMATIONS_FOR_REFLECTION );
	
	public static final Identifier TOTAL_AFFIRMATIONS_ID = RegistryUtil.registerStat( "total_affirmations", Healthyhabits.MOD_ID, StatFormatter.DEFAULT );
	
	static Map<UUID, TickTimer> playerTimers = new HashMap<>();
	static Map<UUID, TickTimer> displayTimers = new HashMap<>();
	
	private static final Random rand = new Random();
	
	public static void execute() {
		affirmationOnTimer();
		affirmationOnDamage();
		affirmationOnDeath();
	}
	
	public static void sendMessage(List<String> list, ServerPlayerEntity player) {
		if( null != displayTimers.get( player.getUuid() ) ) {
			displayTimers.remove( player.getUuid() );
		}
		
		TickTimer displayTimer = new TickTimer( 0, Healthyhabits.CONFIG.positiveAffirmationOnScreenInSeconds );
		
		String randomAffirmation = list.get(rand.nextInt(list.size()));
		
		displayTimer.putData( "affirmation", randomAffirmation );
		
		displayTimers.put( player.getUuid(), displayTimer );
		
		player.incrementStat( TOTAL_AFFIRMATIONS_ID );
		
		showActionBarMessage( randomAffirmation, player );
		
		playSound( player );
		
		NarratorUtil.narrate( player, randomAffirmation );
	}
	
	private static void showActionBarMessage( String affirmation, ServerPlayerEntity player ) {
		MessageUtil.showActionBarMessage( player, affirmation );
	}
	
	private static void playSound( ServerPlayerEntity player ) {
		if (Healthyhabits.CONFIG.enablePositiveAffirmationSounds) {
			EntityUtil.playSound( player, SoundEvents.BLOCK_BELL_RESONATE, SoundCategory.MASTER, Healthyhabits.CONFIG.defaultSoundVolume, 1, true );
		} // if
	}
	
	private static void affirmationOnTimer() {
		// # Player joins the server
		ServerPlayConnectionEvents.JOIN.register((serverPlayNetworkHandler, packetSender, minecraftServer) -> {
			ServerPlayerEntity player = serverPlayNetworkHandler.player;
			
			TickTimer timer = new TickTimer( Healthyhabits.CONFIG.positiveAffirmationFrequencyInMinutes );
			playerTimers.put( player.getUuid(), timer );
			
			if( TimeUtil.isNightTime( player.getServerWorld() ) ) {
				sendMessage(positiveAffirmationsForSleep.getList(), player);
			} else {
				sendMessage(positiveAffirmations.getList(), player);
			} // if, else
		});
		
		// # Server ticks
		ServerTickEvents.START_SERVER_TICK.register(minecraftServer -> {
			for( Map.Entry<UUID, TickTimer> playerData : playerTimers.entrySet() ) {
				UUID playerUUID = playerData.getKey();
				TickTimer playerTimer = playerData.getValue();
				
				playerTimer.tick();
				
				if( playerTimer.isAlarmActivated() ) {
					ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayer( playerUUID );
					
					if( null != player ) {
						if (player.isAlive() && !player.isSleeping()) {
							if( TimeUtil.isNightTime( player.getServerWorld() ) ) {
								sendMessage(positiveAffirmationsForSleep.getList(), player);
							} else {
								sendMessage(positiveAffirmations.getList(), player);
							} // if, else
						} // if
					} // if
					
					playerTimer.resetAlarmActivation();
				} // if
			} // for
			
			if (!displayTimers.isEmpty()) {
				for (Map.Entry<UUID, TickTimer> displayData : displayTimers.entrySet()) {
					UUID playerUUID = displayData.getKey();
					TickTimer displayTimer = displayData.getValue();
					
					if( displayTimer.hasData() ) {
						Object affirmationMessage = displayTimer.getData("affirmation");

						ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayer(playerUUID);

						if (null != player && null != affirmationMessage) {
							if (player.isAlive() && !player.isSleeping()) {
								showActionBarMessage(affirmationMessage.toString(), player);
							} // if
						} // if
					} // if
					
					displayTimer.tick();
					
					if (displayTimer.isAlarmActivated()) {
						displayTimers.remove(playerUUID);
					} // if
				} // for
			} // if
		});
		
		// # Player leaves the server
		ServerPlayConnectionEvents.DISCONNECT.register((serverPlayNetworkHandler, minecraftServer) -> {
			ServerPlayerEntity player = serverPlayNetworkHandler.player;
			displayTimers.remove( player.getUuid() );
			playerTimers.remove( player.getUuid() );
		});
	}
	
	private static void affirmationOnDamage() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, damageSource, amount) -> {
			if( entity.isPlayer() ) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				
				if( Healthyhabits.CONFIG.enablePositiveAffirmations ) {
					player.sendMessageToClient(Text.of("REMAIN CALM AND BREATH"), true);
				} // if
			} // if
			
			return true;
		});
	}
	
	private static void affirmationOnDeath() {
		ServerLivingEntityEvents.ALLOW_DEATH.register((livingEntity, damageSource, v) -> {
			if( livingEntity.isPlayer() ) {
				if( Healthyhabits.CONFIG.revealCoordinatesOnDeath ) {
					livingEntity.sendMessage( Text.of("You died at: " + (int) Math.floor(livingEntity.getPos().getX()) + " " + (int) Math.floor(livingEntity.getPos().getY()) + " " + (int) Math.floor(livingEntity.getPos().getZ())));
				} // if
			} // if
			return true;
		});
		
		ServerPlayerEvents.AFTER_RESPAWN.register((serverPlayerEntity, serverPlayerEntity1, b) -> {
			sendMessage( positiveAffirmationsForDeath.getList(), serverPlayerEntity );
		});
	}
	
}
