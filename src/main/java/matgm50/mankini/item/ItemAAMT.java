package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.client.model.ModelAAMT;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class ItemAAMT extends ItemArmor implements IMankini {


    public ItemAAMT() {

        super(ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.CHEST);
        setUnlocalizedName(ItemLib.ModItems.AETHERIC_MAKNINI_NAME.getUnlocalisedName());
		setRegistryName(ItemLib.ModItems.AETHERIC_MAKNINI_NAME.getRegistryName());
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);

    }

    

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

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
