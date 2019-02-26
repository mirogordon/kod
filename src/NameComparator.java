import java.util.Comparator;

public class NameComparator implements Comparator<ShopOrder> {
    @Override
    public int compare(ShopOrder o1, ShopOrder o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
