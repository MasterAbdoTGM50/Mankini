package matgm50.mankini.item;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.model.ModelAAMT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class ItemAAMT extends ArmorItem implements IMankini {
	private final LazyLoadedValue<HumanoidModel<?>> model;

	public ItemAAMT(Item.Properties builder) {
		super(ArmorMaterials.DIAMOND, EquipmentSlot.CHEST, builder.stacksTo(1));
		this.model = DistExecutor.unsafeRunForDist(() -> () -> new LazyLoadedValue<>(() -> this.provideArmorModelForSlot(slot)),
				() -> () -> null);
	}

	@Nullable
	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.CHEST;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "mankini:textures/models/aetheric_mankini.png";
	}

	@OnlyIn(Dist.CLIENT)
	public HumanoidModel<?> provideArmorModelForSlot(EquipmentSlot slot) {
		return new ModelAAMT(Minecraft.getInstance().getEntityModels().bakeLayer(ClientHandler.AAMT));
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
				return model.get();
			}
		});
	}
}
