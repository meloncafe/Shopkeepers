package com.nisovin.shopkeepers.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.nisovin.shopkeepers.config.settings.BooleanSetting;
import com.nisovin.shopkeepers.config.settings.IntegerSetting;
import com.nisovin.shopkeepers.util.ItemData;

public class Settings {

	private Settings() {
	}

	private static final List<Setting<?>> ALL_SETTINGS = new ArrayList<>();

	private static <T extends Setting<?>> T add(T setting) {
		ALL_SETTINGS.add(setting);
		return setting;
	}

	/*
	 * General Settings
	 */
	public static final Setting<Integer> CONFIG_VERSION = add(new IntegerSetting("config-version", 2));
	public static final Setting<Boolean> DEBUG = add(new BooleanSetting("debug", false));

	public static int configVersion = 2;
	public static boolean debug = false;
	// See DebugOptions for all available options.
	public static List<String> debugOptions = new ArrayList<>(0);
	public static boolean enableMetrics = true;

	/*
	 * Shopkeeper Data
	 */
	public static String fileEncoding = "UTF-8";
	public static boolean saveInstantly = true;

	/*
	 * Plugin Compatibility
	 */
	public static boolean enableSpawnVerifier = false;
	public static boolean bypassSpawnBlocking = true;
	public static boolean checkShopInteractionResult = false;

	public static boolean enableWorldGuardRestrictions = false;
	public static boolean requireWorldGuardAllowShopFlag = false;
	public static boolean registerWorldGuardAllowShopFlag = true;

	public static boolean enableTownyRestrictions = false;

	/*
	 * Shop Creation (and removal)
	 */
	public static ItemData shopCreationItem = new ItemData(Material.VILLAGER_SPAWN_EGG);
	public static boolean preventShopCreationItemRegularUsage = false;
	public static boolean deletingPlayerShopReturnsCreationItem = false;

	public static boolean createPlayerShopWithCommand = false;

	public static boolean requireChestRecentlyPlaced = true;
	public static int maxChestDistance = 15;
	public static int maxShopsPerPlayer = 0;
	public static String maxShopsPermOptions = "10,15,25";

	public static boolean protectChests = true;
	public static boolean preventItemMovement = true;
	public static boolean deleteShopkeeperOnBreakChest = false;

	public static int playerShopkeeperInactiveDays = 0;

	/*
	 * Shop (Object) Types
	 */
	public static List<String> enabledLivingShops = Arrays.asList(
			EntityType.VILLAGER.name(),
			EntityType.COW.name(),
			EntityType.MUSHROOM_COW.name(),
			EntityType.SHEEP.name(),
			EntityType.PIG.name(),
			EntityType.CHICKEN.name(),
			EntityType.OCELOT.name(),
			EntityType.RABBIT.name(),
			EntityType.WOLF.name(),
			EntityType.SNOWMAN.name(),
			EntityType.IRON_GOLEM.name(),
			EntityType.BLAZE.name(),
			EntityType.SILVERFISH.name(),
			EntityType.POLAR_BEAR.name(), // MC 1.10
			EntityType.SKELETON.name(),
			EntityType.STRAY.name(), // MC 1.11
			EntityType.WITHER_SKELETON.name(), // MC 1.11
			EntityType.SPIDER.name(),
			EntityType.CAVE_SPIDER.name(),
			EntityType.CREEPER.name(),
			EntityType.WITCH.name(),
			EntityType.ENDERMAN.name(),
			EntityType.ZOMBIE.name(),
			EntityType.ZOMBIE_VILLAGER.name(), // MC 1.11
			EntityType.PIG_ZOMBIE.name(),
			EntityType.HUSK.name(), // MC 1.11
			EntityType.GIANT.name(),
			EntityType.GHAST.name(),
			EntityType.SLIME.name(),
			EntityType.MAGMA_CUBE.name(),
			EntityType.SQUID.name(),
			EntityType.HORSE.name(),
			EntityType.MULE.name(),
			EntityType.DONKEY.name(),
			EntityType.SKELETON_HORSE.name(),
			EntityType.ZOMBIE_HORSE.name(),
			EntityType.EVOKER.name(), // MC 1.11
			EntityType.VEX.name(), // MC 1.11
			EntityType.VINDICATOR.name(), // MC 1.11
			EntityType.ILLUSIONER.name(), // MC 1.12
			EntityType.PARROT.name(), // MC 1.12
			EntityType.TURTLE.name(), // MC 1.13
			EntityType.PHANTOM.name(), // MC 1.13
			EntityType.COD.name(), // MC 1.13
			EntityType.SALMON.name(), // MC 1.13
			EntityType.PUFFERFISH.name(), // MC 1.13
			EntityType.TROPICAL_FISH.name(), // MC 1.13
			EntityType.DROWNED.name(), // MC 1.13
			EntityType.DOLPHIN.name(), // MC 1.13
			EntityType.CAT.name(), // MC 1.14
			EntityType.PANDA.name(), // MC 1.14
			EntityType.PILLAGER.name(), // MC 1.14
			EntityType.RAVAGER.name(), // MC 1.14
			EntityType.LLAMA.name(), // MC 1.11
			EntityType.TRADER_LLAMA.name(), // MC 1.14
			EntityType.WANDERING_TRADER.name(), // MC 1.14
			EntityType.FOX.name(), // MC 1.14
			"BEE" // MC 1.15
	);

	public static boolean useLegacyMobBehavior = false;
	public static boolean disableGravity = false;
	public static int gravityChunkRange = 4;
	public static boolean silenceLivingShopEntities = true;

	public static boolean showNameplates = true;
	public static boolean alwaysShowNameplates = false;
	public static String nameplatePrefix = "&a";

	public static boolean enableCitizenShops = false;

	public static boolean enableSignShops = true;
	public static boolean enableSignPostShops = true;
	public static String signShopFirstLine = "[SHOP]";

	/*
	 * Naming
	 */
	public static String nameRegex = "[A-Za-z0-9 ]{3,32}";
	public static boolean namingOfPlayerShopsViaItem = false;
	public static boolean allowRenamingOfPlayerNpcShops = false;

	/*
	 * Editor Menu
	 */
	public static String editorTitle = "Shopkeeper Editor";

	public static ItemData previousPageItem = new ItemData(Material.WRITABLE_BOOK);
	public static ItemData nextPageItem = new ItemData(Material.WRITABLE_BOOK);
	public static ItemData currentPageItem = new ItemData(Material.WRITABLE_BOOK);
	public static ItemData tradeSetupItem = new ItemData(Material.PAPER);

	public static ItemData nameItem = new ItemData(Material.NAME_TAG);

	public static boolean enableChestOptionOnPlayerShop = true;
	public static ItemData chestItem = new ItemData(Material.CHEST);

	public static ItemData deleteItem = new ItemData(Material.BONE);

	/*
	 * Non-shopkeeper villagers
	 */
	public static boolean disableOtherVillagers = false;
	public static boolean blockVillagerSpawns = false;
	public static boolean disableZombieVillagerCuring = false;
	public static boolean hireOtherVillagers = false;

	public static boolean disableWanderingTraders = false;
	public static boolean blockWanderingTraderSpawns = false;
	public static boolean hireWanderingTraders = false;

	/*
	 * Hiring
	 */
	public static ItemData hireItem = new ItemData(Material.EMERALD);
	public static int hireOtherVillagersCosts = 1;
	public static String forHireTitle = "For Hire";
	public static boolean hireRequireCreationPermission = true;

	/*
	 * Trading
	 */
	public static boolean preventTradingWithOwnShop = true;
	public static boolean preventTradingWhileOwnerIsOnline = false;
	public static boolean useStrictItemComparison = false;
	public static boolean enablePurchaseLogging = false;
	public static boolean incrementVillagerStatistics = false;

	public static int taxRate = 0;
	public static boolean taxRoundUp = false;

	/*
	 * Currencies
	 */
	public static ItemData currencyItem = new ItemData(Material.EMERALD);
	public static ItemData zeroCurrencyItem = new ItemData(Material.BARRIER);
	public static ItemData highCurrencyItem = new ItemData(Material.EMERALD_BLOCK);
	public static ItemData zeroHighCurrencyItem = new ItemData(Material.BARRIER);

	// note: this can in general be larger than 64!
	public static int highCurrencyValue = 9;
	public static int highCurrencyMinCost = 20;

	/*
	 * Messages
	 */
	public static String language = "en";
}
