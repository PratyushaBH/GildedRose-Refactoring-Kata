package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        GildedRose app = buildApp("foo", 0, 0);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void testUpdateQualityNormalItemDecreaseQuality() {
        GildedRose app = buildApp("Foo", 10, 20);

        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);

    }

    @Test
    void testUpdateQualityNormalItemDecreaseQualityAfterSellin() {
        GildedRose app = buildApp("Foo", 0, 20);

        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);

    }

    @Test
    void testUpdateQualityNormalItemDecreaseQualityNotNegative() {
        GildedRose app = buildApp("Foo", 10, 0);

        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

    }

    @Test
    void testUpdateQualityIncreasesForAgedBrie() {
        GildedRose app = buildApp("Aged Brie", 2, 0);

        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);

    }

    @Test
    void testUpdateQualityIncreasesForAgedBrieMaxQuality50() {
        GildedRose app = buildApp("Aged Brie", 2, 50);

        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForSulfurasDoesDecreaseSellInOrQuality() {
        GildedRose app = buildApp("Sulfuras, Hand of Ragnaros", 0, 80);

        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForBackStagePassesWhenSellInGreaterThan10() {
        GildedRose app = buildApp("Backstage passes to a TAFKAL80ETC concert", 15, 20);

        app.updateQuality();

        assertEquals(14, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForBackStagePassesWhenSellInLessThanOrEqualTo10() {
        GildedRose app = buildApp("Backstage passes to a TAFKAL80ETC concert", 10, 45);

        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
        assertEquals(47, app.items[0].quality);

        app.updateQuality();

        assertEquals(8, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);

        app.updateQuality();

        // max quality 50
        assertEquals(7, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testUpdateQualityForBackStagePassesWhenSellInLessThanOrEqualTo5() {
        GildedRose app = buildApp("Backstage passes to a TAFKAL80ETC concert", 5, 45);

        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);

        app.updateQuality();

        // max 50
        assertEquals(3, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();

        // max quality 50
        assertEquals(2, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testUpdateQualityForBackStagePassesQualityDropsToZero() {
        String name = "Backstage passes to a TAFKAL80ETC concert";
        int sellIn = 1;
        int quality = 45;
        GildedRose app = buildApp(name, sellIn, quality);

        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);

        app.updateQuality();

        // min 0
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForConjureItemsSellInGreaterThan0() {
        GildedRose app = buildApp("Conjured Mana Cake", 3, 6);

        app.updateQuality();

        assertEquals(2, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForConjureItemsSellInGreaterThan0MinQuality0() {
        GildedRose app = buildApp("Conjured Mana Cake", 3, 1);

        app.updateQuality();

        assertEquals(2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForConjureItemsSellInLessThan0() {
        GildedRose app = buildApp("Conjured Mana Cake", -1, 5);

        app.updateQuality();

        assertEquals(-2, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);

    }

    @Test
    void testUpdateQualityForConjureItemsSellInLessThan0Min0() {
        GildedRose app = buildApp("Conjured Mana Cake", -1, 3);

        app.updateQuality();

        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

    }

    private GildedRose buildApp(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        return new GildedRose(items);
    }

}
