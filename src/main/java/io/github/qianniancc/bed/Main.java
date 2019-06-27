package io.github.qianniancc.bed;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "bed", name = "Bed", version = "0.0.2")
public class Main {

	@Inject
	private Logger logger;

	public Logger getLogger() {
		return logger;
	}

	@Listener
	public void onEvent(InteractBlockEvent event, @First Player player) {
		String id = event.getTargetBlock().getExtendedState().getType().getId();
		if (id.equals("minecraft:bed")) {
			String command = "sethome -o home";
			Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
					"pm users " + player.getName() + " add group admin");
			Sponge.getCommandManager().process(player, command);
			Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
					"pm users " + player.getName() + " remove group admin");
		}
	}

	@Listener
	public void onServer(GameStartedServerEvent e) {
		this.getLogger().info("插件已经启动！");
	}

}