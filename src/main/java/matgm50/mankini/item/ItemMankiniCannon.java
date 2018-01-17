package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCannon extends Item {
	
	boolean shotFired;

    public ItemMankiniCannon() {

        super();
        setUnlocalizedName(ItemLib.ModItems.MANKINI_CANNON_NAME.getUnlocalisedName());
		setRegistryName(ItemLib.ModItems.MANKINI_CANNON_NAME.getRegistryName());
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack helditem = playerIn.getHeldItem(handIn);
    	
    	ItemStack itemstack = MankiniHelper.findMankini(playerIn);
        
        if (!itemstack.isEmpty() || playerIn.capabilities.isCreativeMode)
        {
        	if(itemstack.isEmpty())
        	{
        		shotFired = false;
        		itemstack = new ItemStack(ModItems.dyeable_mankini);
        	}
        	
        	worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        	if (!worldIn.isRemote)
            {
        		EntityMankiniCapsule mankinicapsule = new EntityMankiniCapsule(worldIn, playerIn, itemstack.copy());
        		//EntityMankiniCapsule mankinicapsule = new EntityMankiniCapsule(worldIn, playerIn, new ItemStack(foundMankini));
        		mankinicapsule.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -10.0F, 0.5F, 1.0F);
                worldIn.spawnEntity(mankinicapsule);
        		shotFired = true;
        	}

        	if (!playerIn.capabilities.isCreativeMode)
            {
        		itemstack.shrink(1);
        		
                if (itemstack.isEmpty())
                {
                    playerIn.inventory.deleteStack(itemstack);
                }
            }
        
        }
        else 
        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        
        if(shotFired = true){
        	return new ActionResult(EnumActionResult.SUCCESS, helditem);
        }
        else {
        	return new ActionResult(EnumActionResult.FAIL, helditem);
        }
    }
}

    