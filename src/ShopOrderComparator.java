import java.util.Comparator;

public enum ShopOrderComparator {
    NAME(new NameComparator()),
    PRICE(new PriceComparator()),
    STATUS(new StatusComparator()),
    NAME_PRICE(new NamePriceComparator(new NameComparator(), new PriceComparator()));

    private Comparator<ShopOrder> comparator;

    ShopOrderComparator(Comparator<ShopOrder> comparator) {
        this.comparator = comparator;
    }

    public Comparator<ShopOrder> getComparator(){
        return comparator;
    }
}
