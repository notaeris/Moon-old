package io.github.notaeris.moon.util;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

@Getter
public class ItemBuilder {

    private final ItemStack itemStack;

    /**
     * Constructor for ItemBuilder
     *
     * @param material the material
     * @param amount   the amount
     */
    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    /**
     * Constructor for ItemBuilder
     *
     * @param material the material
     */
    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * Constructor for ItemBuilder
     *
     * @param material the material
     * @param amount   the amount
     * @param value    the value
     */
    public ItemBuilder(Material material, int amount, short value) {
        this.itemStack = new ItemStack(material, amount, value);
    }

    /**
     * Set the name of an item
     *
     * @param name the name
     * @return the new item name
     */
    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = this.getItemStack().getItemMeta();
        itemMeta.setDisplayName(name);
        this.getItemStack().setItemMeta(itemMeta);
        return this;
    }

    /**
     * Set the lore of an item
     *
     * @param lore the lore
     * @return the new item lore
     */
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = this.getItemStack().getItemMeta();
        itemMeta.setLore(lore);
        this.getItemStack().setItemMeta(itemMeta);
        return this;
    }

    /**
     * Add a enchant to an item
     *
     * @param enchantment the enchant
     * @param level       the level
     * @return the new item enchant
     */
    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        ItemMeta itemMeta = this.getItemStack().getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        this.getItemStack().setItemMeta(itemMeta);
        return this;
    }

    /**
     * Set the color of a item
     *
     * @param color the color
     * @return the item color
     */
    public ItemBuilder setColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) this.getItemStack().getItemMeta();
        leatherArmorMeta.setColor(color);
        this.getItemStack().setItemMeta(leatherArmorMeta);
        return this;
    }

    /**
     * Create a itemstack
     *
     * @return the itemstack
     */
    public ItemStack create() {
        return this.getItemStack();
    }
}
