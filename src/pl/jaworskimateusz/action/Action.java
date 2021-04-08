package pl.jaworskimateusz.action;

import pl.jaworskimateusz.objects.Product;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Action extends RecursiveAction {

    private static final int NR_OF_PRODUCTS = 100;
    private List<Product> products;

    // these attributes determine block of products that task has to process
    private int first;
    private int last;

    // stores the increment of the price the product
    private double increment;

    public Action(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < NR_OF_PRODUCTS) {
            // the task has to update the price of less than 10 products and increment prices
            updatePrices();
        } else {
             System.out.println("Task: Pending task: " + getQueuedTaskCount());
             int middle = (last + first) / 2;
             Action actionA = new Action(products, first, middle + 1, increment); // process first half of products
             Action actionB = new Action(products, middle + 1, last, increment); // process second half of products
             invokeAll(actionA, actionB);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            products.get(i).setPrice(product.getPrice() * (increment + 1));
        }
    }
}
