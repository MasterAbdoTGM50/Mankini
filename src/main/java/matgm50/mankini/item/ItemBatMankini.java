package matgm50.mankini.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.util.BatHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBatMankini extends ItemArmor implements IMankini {


	BatHandler bat = new BatHandler();

	 


	
    public ItemBatMankini() {

        super(ArmorMaterial.IRON, 0, 1);
        setUnlocalizedName(ItemLib.MANKINI_BAT_NAME);
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);

   
    }
    public static void init() {

        MinecraftForge.EVENT_BUS.register(new ItemBatMankini());

    }
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {

        itemIcon = par1IconRegister.registerIcon(ModLib.ID.toLowerCase() + ":" + "battymankini");

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return "mankini:textures/armors/battymankini.png";

    } 
    
    @SubscribeEvent
	public void PlayerJump(LivingJumpEvent event)
	{
    	
    	if(event.entityLiving instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.entityLiving;
    		if(!player.isSneaking()){
			if (player.inventory.armorItemInSlot(2) != null){
				if(player.inventory.armorItemInSlot(2).getItem()== ModItems.itemBatMankini){
				player.motionY += 1.1F;
				player.addPotionEffect((new PotionEffect(Potion.moveSlowdown.getId(), 200, 1)));
				}
			}
    		}
    	}
	}
    @SubscribeEvent
    public void PlayerFall(LivingFallEvent event)
	{	
    	if(event.entityLiving instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.entityLiving;
    		event.distance = 0F;
    	}

    	}
    

 
    
		    
	}


  
 
   
    
  

