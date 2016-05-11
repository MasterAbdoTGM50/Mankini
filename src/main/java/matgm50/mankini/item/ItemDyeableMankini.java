package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ItemLib;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by MasterAbdoTGM50 on 5/26/2014.
 */

public class ItemDyeableMankini extends ItemArmor implements IMankini {

  //  private IIcon iconNormal;
   // private IIcon iconOverlay;

    public ItemDyeableMankini() {

        super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.CHEST);
        setUnlocalizedName(ItemLib.DYEABLE_MAKNINI_NAME);
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);
        setRegistryName(ItemLib.DYEABLE_MAKNINI_NAME);
       GameRegistry.register(this);

    }

   

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {

        if (par2 > 0) {

            return 16777215;

        } else {

            int j = this.getColor(par1ItemStack);

            if (j < 0)
            {
                j = 16777215;
            }

            return j;

        }

    }


    @Override
    public boolean hasColor(ItemStack par1ItemStack) {

        return true;

    }

    @Override
    public int getColor(ItemStack par1ItemStack) {

        NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();

        if (nbttagcompound == null) {

            return 10511680;

        } else {

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.hasKey("color", 3) ? nbttagcompound1.getInteger("color") : 10511680);

        }

    }

   

    @Override
    public void removeColor(ItemStack par1ItemStack) {

        NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();

        if (nbttagcompound != null) {

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1.hasKey("color")) {

                nbttagcompound1.removeTag("color");

            }

        }

    }
    
    @Override
   	@SideOnly(Side.CLIENT)
   	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
   	{
    	if(type == null){return "mankini:textures/models/mankini.png";}
    	else return "mankini:textures/models/mankiniover.png";
    	//return type == null ? "mankini:textures/armors/mankini.png" : "mankini:textures/armors/mankiniover.png";
   	}
   

}
