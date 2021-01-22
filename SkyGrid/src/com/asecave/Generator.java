package com.asecave;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;

public class Generator extends ChunkGenerator {

	@Override
	public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid biome) {
		ChunkData data = createChunkData(world);

		for (int x = -cx % 5; x < 16; x += 5) {
			for (int y = 0; y < 256; y += 5) {
				for (int z = -cz % 5; z < 16; z += 5) {
					if (x < 0 || z < 0) {
						continue;
					}

					if (world.getEnvironment() == Environment.NORMAL) {

						Material block = Material.AIR;

						if (y < 150 && y > 0) {

							int r = random.nextInt(100);
							if (r < 10 && r > 0) {
								block = Material.ANDESITE;
							} else if (r < 20 && r > 10) {
								block = Material.DIORITE;
							} else if (r < 30 && r > 20) {
								block = Material.GRANITE;
							} else {
								block = Material.STONE;
							}
							if (y <= 150 && y >= 130) {
								if (random.nextFloat() < 0.05f) {
									block = Material.GRAVEL;
								}
							}
							if (y <= 150 && y >= 30) {
								if (random.nextFloat() < 0.15f) {
									block = Material.COAL_ORE;
								}
							}
							if (y <= 140 && y >= 30) {
								if (random.nextFloat() < 0.1f) {
									block = Material.IRON_ORE;
								}
							}
							if (y <= 20 && y >= 1) {
								if (random.nextFloat() < 0.03f) {
									block = Material.LAVA;
								}
							}
							if (y <= 90 && y >= 20) {
								if (random.nextFloat() < 0.01f) {
									block = Material.GOLD_ORE;
								}
							}
							if (y <= 60 && y >= 10) {
								if (random.nextFloat() < 0.12f) {
									block = Material.REDSTONE_ORE;
								}
							}
							if (y <= 40 && y >= 1) {
								if (random.nextFloat() < 0.005f) {
									block = Material.DIAMOND_ORE;
								}
							}
							if (y <= 80 && y >= 1) {
								if (random.nextFloat() < 0.005f) {
									block = Material.LAPIS_ORE;
								}
							}
							if (y <= 80 && y >= 1) {
								if (random.nextFloat() < 0.003f) {
									block = Material.EMERALD_ORE;
								}
							}
							data.setBlock(x, y, z, block);
						}
						if (y == 0) {
							if (random.nextFloat() < 0.01f) {
								data.setBlock(x, y, z, Material.END_PORTAL_FRAME);
							} else {
								data.setBlock(x, y, z, Material.BEDROCK);
							}
						}
						if (y == 150) {
							if (random.nextFloat() < 0.02f) {
								data.setBlock(x, y, z, Material.SAND);
							} else {
								data.setBlock(x, y, z, Material.DIRT);
							}
						}
						if (y == 155) {
							if (random.nextFloat() < 0.03f) {
								data.setBlock(x, y, z, Material.WATER);
							} else {
								if (random.nextFloat() < 0.05f) {
									data.setBlock(x, y, z, Material.SAND);
									if (random.nextFloat() < 0.1f) {
										data.setBlock(x, y - 1, z, Material.SAND);
										data.setBlock(x, y + 1, z, Material.CACTUS);
									}
									if (random.nextFloat() < 0.1f) {
										data.setBlock(x, y - 1, z, Material.SAND);
										if (x + 1 < 16) {
											data.setBlock(x + 1, y, z, Material.WATER);
										} else {
											data.setBlock(x - 1, y, z, Material.WATER);
										}
										data.setBlock(x, y + 1, z, Material.SUGAR_CANE);
									}
								} else {
									data.setBlock(x, y, z, Material.GRASS_BLOCK);
									if (random.nextFloat() < 0.1f) {
										data.setBlock(x, y + 1, z, Material.GRASS);
									}
								}
							}
						}
						if (y == 160) {
							if (random.nextFloat() < 0.1f) {
								switch (random.nextInt(6)) {
								case 0:
									block = Material.OAK_LOG;
									break;
								case 1:
									block = Material.SPRUCE_LOG;
									break;
								case 2:
									block = Material.BIRCH_LOG;
									break;
								case 3:
									block = Material.JUNGLE_LOG;
									break;
								case 4:
									block = Material.ACACIA_LOG;
									break;
								case 5:
									block = Material.DARK_OAK_LOG;
									break;
								}
							}
							data.setBlock(x, y, z, block);
							if (block == Material.JUNGLE_LOG && random.nextFloat() < 0.1f) {
								data.setBlock(x, y - 1, z, Material.VINE);
							}
						}
						if (y == 165 || y == 170) {
							if (random.nextFloat() < 0.5f) {
								switch (random.nextInt(6)) {
								case 0:
									block = Material.OAK_LEAVES;
									break;
								case 1:
									block = Material.SPRUCE_LEAVES;
									break;
								case 2:
									block = Material.BIRCH_LEAVES;
									break;
								case 3:
									block = Material.JUNGLE_LEAVES;
									break;
								case 4:
									block = Material.ACACIA_LEAVES;
									break;
								case 5:
									block = Material.DARK_OAK_LEAVES;
									break;
								}
								data.setBlock(x, y, z, block.createBlockData("[distance=7,persistent=true]"));
							}
						}
					} else if (world.getEnvironment() == Environment.NETHER) {

						Material block = Material.AIR;

						int r = random.nextInt(100);
						if (r < 5 && r > 0) {
							block = Material.BASALT;
						} else if (r < 10 && r > 5) {
							block = Material.BLACKSTONE;
						} else if (r < 15 && r > 10) {
							block = Material.MAGMA_BLOCK;
						} else if (r < 20 && r > 15) {
							block = Material.LAVA;
						} else {
							block = Material.NETHERRACK;
						}
						data.setBlock(x, y, z, block);

						if (random.nextFloat() < 0.05f) {
							data.setBlock(x, y, z, Material.SOUL_SOIL);
						}
						if (random.nextFloat() < 0.05f) {
							data.setBlock(x, y, z, Material.SOUL_SAND);
							if (random.nextFloat() < 0.1f) {
								data.setBlock(x, y + 1, z, Material.NETHER_WART);
							}
						}

						if (random.nextFloat() < 0.1f) {
							data.setBlock(x, y, z, Material.NETHER_QUARTZ_ORE);
						}
						if (random.nextFloat() < 0.05f) {
							data.setBlock(x, y, z, Material.NETHER_GOLD_ORE);
						}
						if (y <= 50) {
							if (random.nextFloat() < 0.005f) {
								data.setBlock(x, y, z, Material.ANCIENT_DEBRIS);
							}
						}
						if (y <= 30) {
							if (random.nextFloat() < 0.35f) {
								data.setBlock(x, y, z, Material.LAVA);
							}
						}
						if (y <= 150 && y >= 100) {
							if (random.nextFloat() < 0.15f) {
								data.setBlock(x, y, z, Material.CRIMSON_STEM);
								if (random.nextFloat() < 0.1f) {
									data.setBlock(x, y - 1, z, Material.WEEPING_VINES);
								}
							} else if (random.nextFloat() < 0.15f) {
								data.setBlock(x, y, z, Material.WARPED_STEM);
								if (random.nextFloat() < 0.1f) {
									data.setBlock(x, y + 1, z, Material.TWISTING_VINES);
								}
							}
						}
						if (y == 0) {
							data.setBlock(x, y, z, Material.BEDROCK);
						}

					} else if (world.getEnvironment() == Environment.THE_END) {
						if (y >= 20 && y <= 70) {
							if (Math.abs(cx) > 8 || Math.abs(cz) > 8) {
								if (random.nextFloat() < 0.5f) {
									data.setBlock(x, y, z, Material.END_STONE);
									if (Math.abs(cx) > 63 || Math.abs(cz) > 63) {
										if (random.nextFloat() < 0.01f) {
											data.setBlock(x, y, z, Material.PURPUR_BLOCK);
										} else if (random.nextFloat() < 0.005f) {
											data.setBlock(x, y + 1, z, Material.CHORUS_FLOWER);
										}
									}
								}
							}
						} else {
							data.setBlock(x, y, z, Material.END_STONE);
						}
					}
				}
			}
		}
		return data;
	}
}
