package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            switch(items[i].name) {
                case "Aged Brie":
                    updateAgedBrie(items[i]);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    updateSulfuras(items[i]);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    updateBackStagePasses(items[i]);
                    break;
                case "Conjured Mana Cake":
                    updateConjuredItems(items[i]);
                    break;
                default :
                    updateNormalItem(items[i]);

            }
        }
    }

    private void updateConjuredItems(Item item) {
        decreaseQuality(item, item.sellIn <= 0 ? 4 : 2 );
        item.sellIn--;
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item, item.sellIn <= 0 ? 2 : 1);
        item.sellIn--;
    }

    private void updateBackStagePasses(Item item) {
        if (item.sellIn <= 0 ) {
            item.quality = 0;
        } else if (item.sellIn < 6) {
            increaseQuality(item, 3);
        } else if (item.sellIn < 11) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
        item.sellIn--;
    }

    private void updateSulfuras(Item item) {
        // Do Nothing
    }

    private void updateAgedBrie(Item item) {
        item.sellIn--;
        increaseQuality(item, 1);
    }

    private void increaseQuality(Item item, int delta) {
        item.quality = Math.min(50, item.quality + delta);
    }

    private void decreaseQuality(Item item, int delta) {
        item.quality = Math.max(0, item.quality - delta);
    }
}
