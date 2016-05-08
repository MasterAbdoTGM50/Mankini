package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.EntityMankiniCapsule;
//import matgm50.mankini.entity.EntityMankiniWither;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        setRegistryName(ItemLib.MANKINI_CANNON_NAME);
        GameRegistry.register(this);

    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        if (!par2World.isRemote) {
        	if(MankiniHelper.mankiniinInventory(par3EntityPlayer) == true){
        		

            par2World.spawnEntityInWorld(new EntityMankiniCapsule(par2World, par3EntityPlayer, MankiniHelper.getFirstFoundMankini(par3EntityPlayer)));
        	}

        }
        ItemStack stack = new ItemStack(MankiniHelper.getFirstFoundMankini(par3EntityPlayer).getItem());
        --stack.stackSize;
       // par3EntityPlayer.inventory.consumeInventoryItem(MankiniHelper.getFirstFoundMankini(par3EntityPlayer).getItem());
        
        par3EntityPlayer.inventory.markDirty();

        return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);

    }
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

	public String getName() {
		// TODO Auto-generated method stub
		return ItemLib.MANKINI_CANNON_NAME;
	}
}

    