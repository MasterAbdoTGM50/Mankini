package matgm50.mankini.entity.hostile;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMankiniEnderman extends EntityEnderman
{
    private static final UUID ATTACKING_SPEED_BOOST_ID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST = (new AttributeModifier(ATTACKING_SPEED_BOOST_ID, "Attacking speed boost", 0.15000000596046448D, 0)).setSaved(false);
    private static final Set<Block> CARRIABLE_BLOCKS = Sets.<Block>newIdentityHashSet();
    private static final DataParameter<Optional<IBlockState>> CARRIED_BLOCK = EntityDataManager.<Optional<IBlockState>>createKey(EntityMankiniEnderman.class, DataSerializers.OPTIONAL_BLOCK_STATE);
    private static final DataParameter<Boolean> SCREAMING = EntityDataManager.<Boolean>createKey(EntityMankiniEnderman.class, DataSerializers.BOOLEAN);
    private int lastCreepySound;
    private int targetChangeTime;

    public EntityMankiniEnderman(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityMankiniEnderman.AIFindPlayer(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMankiniEndermite.class, 10, true, false, new Predicate<EntityMankiniEndermite>()
        {
            public boolean apply(@Nullable EntityMankiniEndermite p_apply_1_)
            {
                return p_apply_1_.isSpawnedByPlayer();
            }
        }));
    }
    
    public static void registerFixesMankiniEnderman(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, "MankiniEnderman");
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
    }
    
    private boolean shouldAttackPlayer(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.armorInventory[3];

        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.PUMPKIN))
        {
            return false;
        }
        else
        {
            Vec3d vec3d = player.getLook(1.0F).normalize();
            Vec3d vec3d1 = new Vec3d(this.posX - player.posX, this.getEntityBoundingBox().minY + (double)this.getEyeHeight() - (player.posY + (double)player.getEyeHeight()), this.posZ - player.posZ);
            double d0 = vec3d1.lengthVector();
            vec3d1 = vec3d1.normalize();
            double d1 = vec3d.dotProduct(vec3d1);
            return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
        }
    }
    
    protected boolean teleportRandomly()
    {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(d0, d1, d2);
    }
    
    private boolean teleportTo(double x, double y, double z)
    {
        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
        boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());

        if (flag)
        {
            this.worldObj.playSound((EntityPlayer)null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
            this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
        }

        return flag;
    }

    static class AIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        private final EntityMankiniEnderman enderman;
        /** The player */
        private EntityPlayer player;
        private int aggroTime;
        private int teleportTime;

        public AIFindPlayer(EntityMankiniEnderman p_i45842_1_)
        {
            super(p_i45842_1_, EntityPlayer.class, false);
            this.enderman = p_i45842_1_;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            double d0 = this.getTargetDistance();
            this.player = this.enderman.worldObj.getNearestAttackablePlayer(this.enderman.posX, this.enderman.posY, this.enderman.posZ, d0, d0, (Function<EntityPlayer, Double>)null, new Predicate<EntityPlayer>()
            {
                public boolean apply(@Nullable EntityPlayer p_apply_1_)
                {
                    return p_apply_1_ != null && AIFindPlayer.this.enderman.shouldAttackPlayer(p_apply_1_);
                }
            });
            return this.player != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.aggroTime = 5;
            this.teleportTime = 0;
        }

        /**
         * Resets the task
         */
        public void resetTask()
        {
            this.player = null;
            super.resetTask();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean continueExecuting()
        {
            if (this.player != null)
            {
                if (!this.enderman.shouldAttackPlayer(this.player))
                {
                    return false;
                }
                else
                {
                    this.enderman.faceEntity(this.player, 10.0F, 10.0F);
                    return true;
                }
            }
            else
            {
                return this.targetEntity != null && ((EntityPlayer)this.targetEntity).isEntityAlive() ? true : super.continueExecuting();
            }
        }

        /**
         * Updates the task
         */
        public void updateTask()
        {
            if (this.player != null)
            {
                if (--this.aggroTime <= 0)
                {
                    this.targetEntity = this.player;
                    this.player = null;
                    super.startExecuting();
                }
            }
            else
            {
                if (this.targetEntity != null)
                {
                    if (this.enderman.shouldAttackPlayer((EntityPlayer)this.targetEntity))
                    {
                        if (((EntityPlayer)this.targetEntity).getDistanceSqToEntity(this.enderman) < 16.0D)
                        {
                            this.enderman.teleportRandomly();
                        }

                        this.teleportTime = 0;
                    }
                    else if (((EntityPlayer)this.targetEntity).getDistanceSqToEntity(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.targetEntity))
                    {
                        this.teleportTime = 0;
                    }
                }

                super.updateTask();
            }
        }
    }
    
}
