package com.projectbronze.wom.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.gui.SlotLocked;

import com.gt22.gt22core.baseclasses.container.ContainerWithPlayerInv;
import com.projectbronze.wom.gui.slot.OutputSlot;
import com.projectbronze.wom.tileEntity.TradeTileEntity;

public class TradeContainer extends ContainerWithPlayerInv
{

	private TradeTileEntity te;

	public TradeContainer(IInventory playerInv, TradeTileEntity te)
	{
		super(playerInv, new Slot[] {new Slot(te, 0, 41, 32), new OutputSlot(te, 1, 119, 32), new SlotLocked(te, 2, 41, 11), new SlotLocked(te, 3, 119, 11)});
		this.te = te;

		for (int y = 0; y < 3; ++y)
		{
			for (int x = 0; x < 9; ++x)
			{
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
		}
	}

	/*
	 * Standart look:
	 * 
	 * @Override
	 * public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
	 * {
	 * ItemStack previous = null;
	 * Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	 * 
	 * if (slot != null && slot.getHasStack()) {
	 * ItemStack current = slot.getStack();
	 * previous = current.copy();
	 * 
	 * // [...] Custom behaviour
	 * 
	 * if (current.stackSize == 0)
	 * slot.putStack((ItemStack) null);
	 * else
	 * slot.onSlotChanged();
	 * 
	 * if (current.stackSize == previous.stackSize)
	 * return null;
	 * slot.onPickupFromSlot(playerIn, current);
	 * }
	 * }
	 * return previous;
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
	{
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack())
		{
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < 2)
			{
				if (!this.mergeItemStack(current, 4, 37, true))
					return null;
			}
			else
			{
				if (!this.mergeItemStack(current, 0, 1, false))
					return null;
			}
			if (current.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (current.stackSize == previous.stackSize)
				return null;
			slot.onPickupFromSlot(playerIn, current);
		}
		return previous;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.te.isUseableByPlayer(playerIn);
	}

}
