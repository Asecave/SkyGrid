package com.asecave;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_16_R3.EntityShulker;

public class SkyGrid extends JavaPlugin implements Listener {

	Logger logger = Logger.getLogger("Minecraft");

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		logger.info("SkyGrid has been enabled!");
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return new Generator();
	}

	@EventHandler
	public void blockActivated(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		if (b != null && b.getType().equals(Material.END_PORTAL_FRAME)) {
			if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE
					|| e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
				if (e.getPlayer().isSneaking()) {
					if (((EndPortalFrame) b.getBlockData()).hasEye()) {
						ItemStack item = new ItemStack(b.getType());
						b.getWorld().dropItemNaturally(b.getLocation(), item);
						b.getWorld().createExplosion(b.getLocation(), 1f);
						b.breakNaturally();
					}
				}
			}
		} else if (b != null && b.getType().equals(Material.PURPUR_BLOCK)) {
			if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE
					|| e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
				b.breakNaturally(new ItemStack(Material.STONE));
				e.getPlayer().getWorld().spawnParticle(Particle.BLOCK_CRACK, b.getLocation().add(0.5d, 0.5d, 0.5f), 100, 0.2d, 0.2d, 0.2d, Material.PURPUR_BLOCK.createBlockData());
				e.getPlayer().getWorld().spawnParticle(Particle.SMOKE_LARGE, b.getLocation().add(0.5d, 0.5d, 0.5f), 20, 0d, 0d, 0d, 0.05d);
				e.getPlayer().playSound(b.getLocation(), Sound.BLOCK_LODESTONE_PLACE, 1f, 2f);
				e.getPlayer().playSound(b.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
				float r = (float) Math.random();
				if (r < 0.02f) {
					Item item = e.getPlayer().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.ELYTRA));
					item.setVelocity(new Vector(0d, 0.4d, 0d));
					e.getPlayer().playSound(b.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 1f, 1f);
				}
			}
		}
	}

	@EventHandler
	public void onMobSpawn(EntitySpawnEvent e) {
		if (e.getEntityType() == EntityType.ZOMBIFIED_PIGLIN) {
			if (Math.random() < 0.1f) {
				e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.WITHER_SKELETON);
				e.setCancelled(true);
			}
			if (Math.random() < 0.1f) {
				e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.BLAZE);
				e.setCancelled(true);
			}
		} else if (e.getEntityType() == EntityType.ENDERMAN) {
			if (Math.abs(e.getLocation().getBlockX()) > 1000 || Math.abs(e.getLocation().getBlockZ()) > 1000) {
				if (Math.random() < 0.05f) {
					Shulker shulker = (Shulker) e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.SHULKER);
					shulker.setRemoveWhenFarAway(true);
					e.setCancelled(true);
				}
			}
		}
	}
}
