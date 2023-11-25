package matgm50.mankini.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by MasterAbdoTGM50 on 5/26/2014.
 */

public class ItemDyeableMankini extends DyeableArmorItem implements IMankini {

	// private IIcon iconNormal;
	// private IIcon iconOverlay;

	public ItemDyeableMankini(Item.Properties builder) {
		super(ArmorMaterials.LEATHER, Type.CHESTPLATE, builder.stacksTo(1));
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


	@Override
	public boolean hasCustomColor(ItemStack stack) {
		CompoundTag tag = stack.getTagElement("display");
		return tag != null && tag.contains("color", 99);
	}

	@Override
	public int getColor(ItemStack stack) {
		CompoundTag tag = stack.getTagElement("display");
		return tag != null && tag.contains("color", 99) ? tag.getInt("color") : 10511680;
	}

	@Override
	public void clearColor(ItemStack stack) {
		CompoundTag tag = stack.getTagElement("display");
		if (tag != null && tag.contains("color")) {
			tag.remove("color");
		}
	}

	@Override
	public void setColor(ItemStack stack, int color) {
		stack.getOrCreateTagElement("display").putInt("color", color);
	}


	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		if (type == null) {
			return "mankini:textures/models/mankini.png";
		} else return "mankini:textures/models/mankini_over.png";
	}
}