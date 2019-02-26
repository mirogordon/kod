import java.util.Comparator;

public class PriceComparator implements Comparator<ShopOrder> {
    @Override
    public int compare(ShopOrder o1, ShopOrder o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
