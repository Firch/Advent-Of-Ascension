package net.tslat.aoa3.entity.npcs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityZalVendor extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityZalVendor(World world) {
		super(world, entityWidth, 1.875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.6875f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_ZAL_VENDOR;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.zal_vendor." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 5), new ItemStack(ItemRegister.lunarade, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunaradeMug, 1), new ItemStack(ItemRegister.coinCopper, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(WeaponRegister.bowSlingshot, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 15), new ItemStack(ItemRegister.popShot, 1)));

		return newList;
	}
}
