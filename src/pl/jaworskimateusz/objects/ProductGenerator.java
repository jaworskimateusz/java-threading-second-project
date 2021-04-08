package pl.jaworskimateusz.objects;

import java.util.ArrayList;
import java.util.List;

public class ProductGenerator {

    public static List<Product> generate(int size) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            products.add(new Product("Name " + i, 15));
        }
        return products;
    }
}
