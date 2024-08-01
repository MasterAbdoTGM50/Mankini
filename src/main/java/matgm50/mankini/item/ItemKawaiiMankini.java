package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemKawaiiMankini extends ArmorItem implements IMankini {

	public ItemKawaiiMankini(Item.Properties builder) {
		super(ModArmorMaterials.KAWAII_MANKINI, Type.CHESTPLATE, builder.stacksTo(1));
	}

//	@Nullable
//	@Override
//	public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
//		return new ResourceLocation(ModLib.MOD_ID, "textures/models/kawaii_mankini_layer_1.png");
//	}
}
