package com.gmail.molnardad.quester.listeners;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

import com.gmail.molnardad.quester.Quest;
import com.gmail.molnardad.quester.QuestManager;
import com.gmail.molnardad.quester.Quester;
import com.gmail.molnardad.quester.objectives.Objective;
import com.gmail.molnardad.quester.objectives.ShearObjective;

public class ShearListener implements Listener {

	private QuestManager qm = Quester.qMan;
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onShear(PlayerShearEntityEvent event) {
		if(event.getEntity().getType() == EntityType.SHEEP) {
			Player player = event.getPlayer();
			Sheep sheep = (Sheep) event.getEntity();
	    	Quest quest = qm.getPlayerQuest(player.getName());
		    if(quest != null) {
		    	if(!quest.allowedWorld(player.getWorld().getName().toLowerCase()))
		    		return;
				List<Objective> objs = quest.getObjectives();
		    	for(int i = 0; i < objs.size(); i++) {
		    		if(objs.get(i).getType().equalsIgnoreCase("SHEAR")) {
		    			if(!qm.isObjectiveActive(player, i)){
		    				continue;
		    			}
		    			ShearObjective obj = (ShearObjective)objs.get(i);
		    			if(obj.check(sheep.getColor())) {
		    				qm.incProgress(player, i);
		    				return;
		    			}
		    		}
		    	}
			}
		}
	}
}
