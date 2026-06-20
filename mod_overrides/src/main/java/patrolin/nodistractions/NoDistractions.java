package patrolin.nodistractions;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoDistractions implements ModInitializer {
	@Override
	public void onInitialize() {}
	public static final String MOD_ID = "nodistractions";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  public static final int STARTING_HUNGER = 10;
	/** Offset from max health as a float in half-hearts */
  public static final float HEALTH_SPRINT_RANGE = 1.5f;
	public static boolean cantEat(float health, float maxHealth) {
		return health < maxHealth;
	}
	public static boolean cantSprint(float health, float maxHealth) {
		return health < (maxHealth - HEALTH_SPRINT_RANGE);
	}
}