import java.util.Comparator;

public class NamePriceComparator implements Comparator<ShopOrder> {

    private NameComparator nameComparator;
    private PriceComparator priceComparator;

    public NamePriceComparator(NameComparator nameComparator, PriceComparator priceComparator) {
        this.nameComparator = nameComparator;
        this.priceComparator = priceComparator;
    }

    @Override
    public int compare(ShopOrder o1, ShopOrder o2) {

        int result = nameComparator.compare(o1, o2);

        if (result != 0) {
            return result;
        } else {
            return priceComparator.compare(o1, o2);
        }
    }
}
