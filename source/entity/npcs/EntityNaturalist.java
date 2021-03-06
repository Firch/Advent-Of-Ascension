package net.tslat.aoa3.entity.npcs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityNaturalist extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityNaturalist(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_NATURALIST;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.naturalist." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 64), new ItemStack(ItemRegister.seedsBubbleBerry, 2)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 64), new ItemStack(ItemRegister.seedsHeartFruit, 2)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 64), new ItemStack(ItemRegister.seedsMagicMarang, 2)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 64), new ItemStack(ItemRegister.seedsThornyPlant, 2)));

		return newList;
	}
}
