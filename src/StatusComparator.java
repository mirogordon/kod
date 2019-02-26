import java.util.Comparator;

public class StatusComparator implements Comparator<ShopOrder>{
    @Override
    public int compare(ShopOrder o1, ShopOrder o2) {
        return o1.getStatus().name().compareTo(o2.getStatus().name());
    }
}
