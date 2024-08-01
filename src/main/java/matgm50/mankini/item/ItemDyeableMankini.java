package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

/**
 * Created by MasterAbdoTGM50 on 5/26/2014.
 */

public class ItemDyeableMankini extends ArmorItem implements IMankini {

	// private IIcon iconNormal;
	// private IIcon iconOverlay;

	public ItemDyeableMankini(Item.Properties builder) {
		super(ModArmorMaterials.MANKINI, Type.CHESTPLATE, builder.stacksTo(1));
	}

//    @OnlyIn(Dist.CLIENT)
//    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
//        if (par2 > 0) {
//            return 16777215;
//        } else {
//            int j = this.getColor(par1ItemStack);
//            if (j < 0) {
//                j = 16777215;
//            }
//            return j;
//        }
//    }


//	@Nullable
//	@Override
//	public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot,
//	                                        ArmorMaterial.Layer layer, boolean innerModel) {
//		return new ResourceLocation("textures/models/mankini_layer_1.png");
//	}
}