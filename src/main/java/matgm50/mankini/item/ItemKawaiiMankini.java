package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemKawaiiMankini extends ItemArmor implements IMankini {

    public ItemKawaiiMankini() {

        super(ArmorMaterial.GOLD, 0, 1);
        setUnlocalizedName(ItemLib.KAWAII_MAKNINI_NAME);
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);

    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {

        itemIcon = par1IconRegister.registerIcon(ModLib.ID.toLowerCase() + ":" + "kawaiimankini");

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return "mankini:textures/armors/kawaiimankini.png";

    }

}
