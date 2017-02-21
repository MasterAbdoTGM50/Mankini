package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.lib.ItemLib;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBatMankini extends ItemArmor implements IMankini {
	
    public ItemBatMankini() {

        super(ArmorMaterial.IRON, 0, EntityEquipmentSlot.CHEST);
        setUnlocalizedName(ItemLib.ModItems.MANKINI_BAT_NAME.getUnlocalisedName());
		setRegistryName(ItemLib.ModItems.MANKINI_BAT_NAME.getRegistryName());
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);
        GameRegistry.register(this);

    }

    @SubscribeEvent
	public void PlayerJump(LivingJumpEvent event)
	{
    	
    	if(event.getEntityLiving() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
    		if(!player.isSneaking()){
			if (player.inventory.armorItemInSlot(2) != null){
				if(player.inventory.armorItemInSlot(2).getItem()== ModItems.itemBatMankini){
				player.motionY += 1.1F;
				player.addPotionEffect((new PotionEffect(MobEffects.SLOWNESS, 200, 1)));
				}
			}
    		}
    	}
	}

    public String getName()
    {
    return "battymankini";
    }
    @Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
	{
		return "mankini:" + "textures/models/battymankini.png";
	}
    
  
}