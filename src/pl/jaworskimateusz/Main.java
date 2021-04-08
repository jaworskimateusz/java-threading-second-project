package pl.jaworskimateusz;

import pl.jaworskimateusz.objects.Product;
import pl.jaworskimateusz.objects.ProductGenerator;
import pl.jaworskimateusz.action.Action;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Product> products = ProductGenerator.generate(100000);
        Action action = new Action(products, 0, products.size(), 0.15);

        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(action);

        do {
            System.out.println("==========================");
            System.out.println("Main: Active threads: " + pool.getActiveThreadCount());
            System.out.println("Main: Task count: " + pool.getQueuedTaskCount());
            System.out.println("==========================");
            TimeUnit.MILLISECONDS.sleep(5);
        } while(!action.isDone());

        pool.shutdown();

        if (action.isCompletedNormally()) {
            System.out.println("Main: The process has completed normally");
        }

//        for (Product product : products) {
//            System.out.println("Main: Product: " + product.toString());
//        }

    }
}
