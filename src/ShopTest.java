import java.io.File;
import java.util.*;

public class ShopTest {
    public static void main(String[] args) {
        File file = new File("zamówienia.csv");
        File file2 = new File("zamówienia_fin.csv");
        Import importFile = new Import();

        ArrayList<ShopOrder> orderLists = importFile.getImport(file); //zaczyt z pliku

        Collections.sort(orderLists, ShopOrderComparator.NAME_PRICE.getComparator());

        System.out.println("Zaimportowane zamówienia, posortowane po (nazwa, cena)");
        for (ShopOrder order : orderLists) {
            System.out.println(order);
        }
        Scanner scan = new Scanner(System.in);

        ShopOrderAction[] actions = ShopOrderAction.values();
        System.out.println("Co chcesz wykonać z danymi zamówieniami?");


        for (ShopOrderAction action : actions) {
            System.out.println(action);
        }
        String actionChoice = scan.nextLine().toUpperCase();

        switch (actionChoice) {
            case "END": {
                System.out.println("Koniec programu");
                break;
            }
            case "ADD": {
                addMethod(file2, orderLists, scan);
                break;
            }
            case "CHANGE_STATUS": {
                changeStatus(orderLists, scan);
                break;
            }
            case "SORT": {
                sortMethod(orderLists, scan);
                break;
            }

            default: {
                System.out.println("Nieprawidłowa wartość!");
            }
        }
    }

    private static void changeStatus(ArrayList<ShopOrder> orderLists, Scanner scan) {
        System.out.println("Podaj id zamówienia, które chcesz zmienić");
        int id = scan.nextInt();
        ShopOrder foundOrder = null;
        for (ShopOrder order : orderLists) {
            if (order.getId() == id) {
                foundOrder = order;
                break;
            }
        }
        if (foundOrder != null) {
            System.out.println("Zmieniasz status zamówienia:");
            System.out.println(foundOrder);

            Map<Status, List<Status>> statusMap = possibleStatus();
            List<Status> availableStatutes = statusMap.get(foundOrder.getStatus());
            if(!availableStatutes.isEmpty()){
                System.out.println("Wybierz status na jakis chcesz zmienic zamówienie: ()");
                for (Status status : availableStatutes) {
                    System.out.println(status);
                }
                String givenStatus = scan.next();
                foundOrder.setStatus(Status.valueOf(givenStatus));
                System.out.println("Status zamówienia o id: " +foundOrder.getId()+ " został zmieniony na: " +givenStatus);
                System.out.println(foundOrder);

            } else {
                System.out.println("Nie możesz zmienić statusu zamówienia, które ma status: " +foundOrder.getStatus());
            }
        } else {
            System.out.println("Zamówienie o id: " + id + " nie zostało znalezione w systemie");
        }
    }

    private static HashMap<Status, List<Status>> possibleStatus() {
        HashMap<Status, List<Status>> result = new HashMap<>();
        result.put(Status.ZLOZONE, Arrays.asList(Status.PRZYGOTOWANE, Status.ANULOWANE, Status.TRANSPORT, Status.ZREALIZOWANE));
        result.put(Status.PRZYGOTOWANE, Arrays.asList(Status.ANULOWANE, Status.TRANSPORT, Status.ZREALIZOWANE));
        result.put(Status.ANULOWANE, Collections.EMPTY_LIST);
        result.put(Status.ZREALIZOWANE, Collections.EMPTY_LIST);
        result.put(Status.TRANSPORT, Arrays.asList(Status.ZREALIZOWANE));
        return result;
    }

    private static void addMethod(File file2, ArrayList<ShopOrder> orderLists, Scanner scan) {
        Export export = new Export();
        System.out.println("Podaj id");
        int tmpId = scan.nextInt();
        scan.nextLine();
        System.out.println("Podaj nazwę zamówienia");
        String tmpName = scan.nextLine();
        String tmpName2 = tmpName + "-" + tmpId;
        System.out.println("Podaj cenę zamówienia");
        double tmpPrice = scan.nextDouble();
        scan.nextLine();
        orderLists.add(new ShopOrder(tmpId, tmpName2, tmpPrice, Status.ZLOZONE));
        export.exportCsv(orderLists, file2);
        for (ShopOrder orderList : orderLists) {
            System.out.println(orderList);
        }
    }


    private static void sortMethod(ArrayList<ShopOrder> orderLists, Scanner scan) {
        ShopOrderComparator[] comparators = ShopOrderComparator.values();
        System.out.println("Po czym posortować?");
        for (ShopOrderComparator comparator : comparators) {
            System.out.println(comparator);
        }
        String tmp2 = scan.nextLine().toUpperCase();
        try {
            ShopOrderComparator shopOrderComparator = ShopOrderComparator.valueOf(tmp2);
            Collections.sort(orderLists, shopOrderComparator.getComparator());
            for (ShopOrder orderList : orderLists) {
                System.out.println(orderList);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Nieprawidłowa wartość!");
        }
    }

}



