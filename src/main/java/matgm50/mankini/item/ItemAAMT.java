package matgm50.mankini.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matgm50.mankini.Mankini;
import matgm50.mankini.client.model.ModelAAMT;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class ItemAAMT extends ItemArmor implements IMankini {


    public ItemAAMT() {

        super(ArmorMaterial.DIAMOND, 0, 1);
        setUnlocalizedName(ItemLib.AETHERIC_MAKNINI_NAME);
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);

    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {

        itemIcon = par1IconRegister.registerIcon(ModLib.ID.toLowerCase() + ":" + "aethericmankini");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, int slot) {

        ModelBiped model = new ModelAAMT(0.5F);

        model.isSneak = player.isSneaking();
        model.isRiding = player.isRiding();
        model.isChild = player.isChild();

        if(player instanceof EntityPlayer) {

            EntityPlayer playerR = (EntityPlayer) player;

            ItemStack ItemInUse = playerR.getHeldItem();

            model.heldItemRight = ItemInUse != null ? 1 : 0;

            if (ItemInUse != null && playerR.getItemInUseCount() > 0) {

                EnumAction Action = ItemInUse.getItemUseAction();

                if (Action == EnumAction.block) {

                    model.heldItemRight = 3;

                } else if (Action == EnumAction.bow) {

                    model.aimedBow = true;

                }

            }

        }

        return model;

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return "mankini:textures/armors/aethericmankini.png";

    }

}
