package matgm50.mankini.entity.hostile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityMankiniEndermite extends EntityEndermite
{
    private int lifetime;
    private boolean playerSpawned;

    public EntityMankiniEndermite(World worldIn)
    {
        super(worldIn);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ENDERMITE_AMBIENT;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_ENDERMITE_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ENDERMITE_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_ENDERMITE_STEP, 0.15F, 1.0F);
    }

    public static void registerFixesMankiniEndermite(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityMankiniEndermite.class);
    }
}