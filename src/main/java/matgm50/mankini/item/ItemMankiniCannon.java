package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCannon extends Item {

    public ItemMankiniCannon() {

        super();
        setUnlocalizedName(ItemLib.MANKINI_CANNON_NAME);
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);
        setFull3D();

    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        if (!par2World.isRemote) {

            par2World.spawnEntityInWorld(new EntityMankiniCapsule(par2World, par3EntityPlayer, MankiniHelper.getFirstFoundMankini(par3EntityPlayer)));

        }

        par3EntityPlayer.inventory.consumeInventoryItem(MankiniHelper.getFirstFoundMankini(par3EntityPlayer).getItem());
        par3EntityPlayer.inventory.markDirty();

        return par1ItemStack;

    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {

        itemIcon = par1IconRegister.registerIcon(ModLib.ID.toLowerCase() + ":" + "mankinicannon");

    }

}
