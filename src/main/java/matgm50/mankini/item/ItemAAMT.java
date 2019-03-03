package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.client.model.ModelAAMT;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class ItemAAMT extends ItemArmor implements IMankini {

    public ItemAAMT(Item.Properties builder) {
        super(ArmorMaterial.DIAMOND, EntityEquipmentSlot.CHEST, builder.group(Mankini.tabMankini).maxStackSize(1));
    }

//    @Override
//	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
//		return armorType == EntityEquipmentSlot.CHEST;
//    }

	@Nullable
	@Override
	public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EntityEquipmentSlot.CHEST;
	}

	@OnlyIn(Dist.CLIENT)
    private ModelAAMT model;
    
    @Override
	@OnlyIn(Dist.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if (armorSlot == EntityEquipmentSlot.CHEST) {
			if (model == null) model = new ModelAAMT(0.5F);
			return model;
		}

		return null;
    }
    
    /*
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, int slot) {

        ModelBiped model = new ModelAAMT(0.5F);

        model.isSneak = player.isSneaking();
        model.isRiding = player.isRiding();
        model.isChild = player.isChild();

        if(player instanceof EntityPlayer) {

            EntityPlayer playerR = (EntityPlayer) player;

            ItemStack ItemInUse = playerR.getHeldItemMainhand();

          //  model.heldItemRight = ItemInUse != null ? 1 : 0;

            if (ItemInUse != null && playerR.getItemInUseCount() > 0) {

                EnumAction Action = ItemInUse.getItemUseAction();

                if (Action == EnumAction.BLOCK) {

                   // model.heldItemRight = 3;

                } else if (Action == EnumAction.BOW) {

                  //  model.aimedBow = true;

                }

            }

        }

        return model;

    }
    
    
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		return new ModelResourceLocation(ModLib.MOD_ID + ":" + getUnlocalizedName(stack).substring(5), "inventory");
	}
    */
    
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return "mankini:textures/models/aetheric_mankini.png";
	}
}
