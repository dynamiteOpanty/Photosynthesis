package net.zuiron.photosynthesis.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.zuiron.photosynthesis.block.entity.FluidPressBlockEntity;
import net.zuiron.photosynthesis.block.entity.KegBlockEntity;
import net.zuiron.photosynthesis.screen.slot.OneCountSlot;
import net.zuiron.photosynthesis.util.FluidStack;

public class FluidPressScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public final FluidPressBlockEntity blockEntity;

    //public FluidStack fluidInputStack;
    public FluidStack fluidOutputStack;

    public FluidPressScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public FluidPressScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity entity, PropertyDelegate delegate) {
        super(ModScreenHandlers.FLUID_PRESS_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) entity), 2);
        this.inventory = (Inventory)entity;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        this.blockEntity = (FluidPressBlockEntity) entity;
        //this.fluidInputStack = new FluidStack(blockEntity.fluidInput.variant, blockEntity.fluidInput.amount);
        this.fluidOutputStack = new FluidStack(blockEntity.fluidOutput.variant, blockEntity.fluidOutput.amount);

        //this.addSlot(new OneCountSlot(inventory, 0, 8, 6));          //bucket

        this.addSlot(new Slot(inventory, 0, 65, 31));           //item
        //this.addSlot(new Slot(inventory, 2, 71, 26));           //item
        //this.addSlot(new Slot(inventory, 3, 89, 26));           //item
        //this.addSlot(new Slot(inventory, 4, 107, 26));           //item

        this.addSlot(new OneCountSlot(inventory, 1, 131, 58));           //bucket

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);
    }

    /*public void setInputFluid(FluidStack stack) {
        fluidInputStack = stack;
    }*/

    public void setOutputFluid(FluidStack stack) {
        fluidOutputStack = stack;
    }


    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledProgress2() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 22; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}