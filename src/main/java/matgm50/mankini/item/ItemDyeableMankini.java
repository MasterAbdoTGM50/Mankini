package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmorDyeable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

/**
 * Created by MasterAbdoTGM50 on 5/26/2014.
 */

public class ItemDyeableMankini extends ItemArmorDyeable implements IMankini {

  //  private IIcon iconNormal;
   // private IIcon iconOverlay;

    public ItemDyeableMankini(Item.Properties builder) {
        super(ArmorMaterial.LEATHER, EntityEquipmentSlot.CHEST, builder.group(Mankini.tabMankini).maxStackSize(1));
    }

//    @OnlyIn(Dist.CLIENT)
//    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
//
//        if (par2 > 0) {
//
//            return 16777215;
//
//        } else {
//
//            int j = this.getColor(par1ItemStack);
//
//            if (j < 0)
//            {
//                j = 16777215;
//            }
//
//            return j;
//        }
//    }


    @Override
    public boolean hasColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getChildTag("display");
        return nbttagcompound != null && nbttagcompound.contains("color", 99);
    }

    @Override
    public int getColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getChildTag("display");
        return nbttagcompound != null && nbttagcompound.contains("color", 99) ? nbttagcompound.getInt("color") : 10511680;
    }

    @Override
    public void removeColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getChildTag("display");
        if (nbttagcompound != null && nbttagcompound.hasKey("color")) {
            nbttagcompound.removeTag("color");
        }
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        stack.getOrCreateChildTag("display").setInt("color", color);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if(type == null){return "mankini:textures/models/mankini.png";}
        else return "mankini:textures/models/mankini_over.png";
    }
}