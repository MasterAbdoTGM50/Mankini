package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.EntityMankiniCapsule;
//import matgm50.mankini.entity.EntityMankiniWither;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
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

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
    	 
      
          //  --itemStackIn.stackSize;
        
       
        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_snowball_throw, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
       
        if (!worldIn.isRemote) {
            if(MankiniHelper.mankiniinInventory(playerIn) == true){
            	
            	  if (!playerIn.capabilities.isCreativeMode)
                  {	
            playerIn.inventory.removeStackFromSlot(MankiniHelper.mankiniSlot(playerIn));
                  }
            	  
            EntityMankiniCapsule entitymankinicapsule = new EntityMankiniCapsule(worldIn, playerIn, MankiniHelper.getFirstFoundMankini(playerIn));
            entitymankinicapsule.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entitymankinicapsule);
            //par2World.spawnEntityInWo+++rld(new EntityMankiniCapsule(par2World, par3EntityPlayer, MankiniHelper.getFirstFoundMankini(par3EntityPlayer)));
            }
 
        }
        ItemStack stack = new ItemStack(MankiniHelper.getFirstFoundMankini(playerIn).getItem());
   //     --stack.stackSize;
       // par3EntityPlayer.inventory.consumeInventoryItem(MankiniHelper.getFirstFoundMankini(par3EntityPlayer).getItem());
       
        playerIn.inventory.markDirty();
 
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
 
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

    