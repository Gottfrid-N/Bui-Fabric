package se.gottfrid_n.bui.vault;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import se.gottfrid_n.api.vault.ItemRegistration;
import se.gottfrid_n.bui.Bui;

public class BuiItems extends ItemRegistration {
    public static Item registerBlockItem(String id, BlockItem blockItem, String stage) {
        return registerItem(id, blockItem, stage);
    }
    public static Item registerBlockItem(String id, BlockItem blockItem) {return registerItem(id, blockItem);}
    public static Item registerItem(String id, Item item, String stage) {
        Bui.logRegister(id, "item", stage);
        return registerItem(id, item);
    }
    public static Item registerItem(String id, Item item) {
        return lowRegisterItem(id, item, Bui.IDENTIFIER);
    }
    public static void toItemGroup(Item item, ItemGroup itemGroup) {
        lowToItemGroup(item, itemGroup);
    }
}
