package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ItemLib;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCapsule extends Item {

    public ItemMankiniCapsule() {

        super();
        setUnlocalizedName(ItemLib.MANKINI_CAPSULE_NAME);
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);
        setRegistryName(ItemLib.MANKINI_CAPSULE_NAME);
        GameRegistry.register(this);

    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        return par1ItemStack;

    }



}
