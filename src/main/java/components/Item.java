package components;

public enum Item {
    BACKPACK("sauce-labs-backpack"),
    BIKE_LIGHT("sauce-labs-bike-light"),
    BOLT_T_SHIRT("sauce-labs-bolt-t-shirt"),
    JACKET("sauce-labs-fleece-jacket"),
    ONESIE("sauce-labs-onesie"),
    RED_T_SHIRT("test.allthethings()-t-shirt-(red)");

    private final String itemId;
    Item(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
}
