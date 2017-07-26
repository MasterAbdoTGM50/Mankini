package matgm50.mankini.lib;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ItemLib {
	    
    public static enum ModItems {
    	
    	DYEABLE_MAKNINI_NAME("dyeablemankini", "itemdyeablemankini"),
    	KAWAII_MAKNINI_NAME("kawaiimankini", "itemkawaiimankini"),
    	AETHERIC_MAKNINI_NAME("aethericmankini", "itemaethericmankini"),
    	MANKINI_CANNON_NAME("mankinicannon", "itemmankinicannon"),
    	MANKINI_CAPSULE_NAME("mankinicapsule", "itemmankinicapsule"),
    	MANKINI_BAT_NAME("mankinibat", "itemmankinibat"),
    	MANKINI_HORSE_ARMOR("ironhorsemankini", "itemironhorsemankini");
    	
    	private String unlocalisedName;
    	private String registryName;
    	
    	ModItems(String unlocalisedName, String registryName) {
    		this.unlocalisedName = unlocalisedName;
    		this.registryName = registryName;
    	}
    	
    	public String getUnlocalisedName() {
    		return unlocalisedName;
    	}
    	
    	public String getRegistryName() {
    		return registryName;
    	}
    }
}