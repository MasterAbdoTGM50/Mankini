package matgm50.mankini.entity.hostile;

import javax.annotation.Nullable;

import matgm50.mankini.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMankiniSpider extends EntitySpider
{
    public EntityMankiniSpider(World worldIn)
    {
        super(worldIn);
    }

    public static void registerFixesMankiniSpider(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityMankiniSpider.class);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SPIDER_AMBIENT;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_SPIDER_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SPIDER_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
    	ItemStack creeperKini = new ItemStack(ModItems.dyeable_mankini);

        if (this.world.rand.nextInt(100) == 0)
        {
            EntitySkeleton entityskeleton = new EntitySkeleton(this.world);
            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData)null);
            this.world.spawnEntity(entityskeleton);
            entityskeleton.setItemStackToSlot(EntityEquipmentSlot.CHEST, creeperKini);
            entityskeleton.startRiding(this);
        }
        
        else if (this.world.rand.nextInt(100) < 10)
        {
        	
            EntityMankiniCreeper mankinicreeper = new EntityMankiniCreeper(this.world);
            mankinicreeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            mankinicreeper.onInitialSpawn(difficulty, (IEntityLivingData)null);
            this.world.spawnEntity(mankinicreeper);
            mankinicreeper.setItemStackToSlot(EntityEquipmentSlot.CHEST, creeperKini);
            mankinicreeper.startRiding(this);
        }

        if (livingdata == null)
        {
            livingdata = new EntityMankiniSpider.GroupData();

            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty())
            {
                ((EntityMankiniSpider.GroupData)livingdata).setRandomEffect(this.world.rand);
            }
        }

        if (livingdata instanceof EntityMankiniSpider.GroupData)
        {
            Potion potion = ((EntityMankiniSpider.GroupData)livingdata).effect;

            if (potion != null)
            {
                this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
        }

        return livingdata;
    }
}