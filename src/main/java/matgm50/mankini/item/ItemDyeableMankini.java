package matgm50.mankini.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

/**
 * Created by MasterAbdoTGM50 on 5/26/2014.
 */

public class ItemDyeableMankini extends ItemArmor {

    private IIcon iconNormal;
    private IIcon iconOverlay;

    public ItemDyeableMankini() {

        super(ArmorMaterial.CLOTH, 0, 1);
        setUnlocalizedName(ItemLib.DYEABLE_MAKNINI_NAME);
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);

    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {

        iconNormal = par1IconRegister.registerIcon(ModLib.ID.toLowerCase() + ":" + "mankini");
        iconOverlay = par1IconRegister.registerIcon(ModLib.ID.toLowerCase() + ":" + "mankiniover");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {

        return iconNormal;

    }

    @Override
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
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {

        return true;

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
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int par1, int par2) {

        return par2 == 1 ? this.iconOverlay : super.getIconFromDamageForRenderPass(par1, par2);

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
    public void func_82813_b(ItemStack par1ItemStack, int par2) {

        NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();

        if (nbttagcompound == null) {

            nbttagcompound = new NBTTagCompound();
            par1ItemStack.setTagCompound(nbttagcompound);

        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

        if (!nbttagcompound.hasKey("display", 10)) {

            nbttagcompound.setTag("display", nbttagcompound1);

        }

        nbttagcompound1.setInteger("color", par2);

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return type == null ? "mankini:textures/armors/mankini.png" : "mankini:textures/armors/mankiniover.png";

    }

}
