package com.example.gibson.carlife.Model;

import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.Model.Product.ProductType;

import java.util.ArrayList;

public class DataManagement {
  private static OrderCollection orderCollection;
  private static ArrayList<Product> products;
  private static ArrayList<ProductBrand> productBrands;
  private static ArrayList<ProductType> productTypes;

  public static OrderCollection getOrderCollection() {
    if (orderCollection == null)
      orderCollection = new OrderCollection();
    return orderCollection;
  }

  public static ArrayList<Product> getProducts() {
    if (products == null)
      products = new ArrayList<>();
    return products;
  }

  public static ArrayList<Product> getProducts(int numberOfItem) {
    if (products == null)
      products = new ArrayList<>();

    return (ArrayList<Product>) getNumberOfArrayList(products, numberOfItem);
  }

  public static ArrayList<ProductBrand> getProductBrands() {
    if (productBrands == null)
      productBrands = new ArrayList<>();

    return productBrands;
  }

  public static ArrayList<ProductBrand> getProductBrands(int numberOfItem) {
    if (productBrands == null)
      productBrands = new ArrayList<>();
    return (ArrayList<ProductBrand>) getNumberOfArrayList(productBrands, numberOfItem);
  }

  public static ArrayList<ProductType> getProductTypes() {
    if (productTypes == null)
      productTypes = new ArrayList<>();
    return productTypes;
  }

  public static ArrayList<ProductType> getProductTypes(int numberOfItem) {
    if (productTypes == null)
      productTypes = new ArrayList<>();
    return (ArrayList<ProductType>) getNumberOfArrayList(productTypes, numberOfItem);
  }

  static ArrayList getNumberOfArrayList(ArrayList list, int number) {
    ArrayList arrayList = new ArrayList();
    for (int i = 0; i < list.size() &&  i < number; i++) {
      arrayList.add(list.get(i));
    }
    return arrayList;
  }

  public static ArrayList<Product> getBrandOfProduct(String brandName) {
    ArrayList<Product> arrayList = new ArrayList();
    for (Product product : products) {
      if (product.product_brand.equalsIgnoreCase(brandName))
        arrayList.add(product);
    }
    return arrayList;
  }

  public static ArrayList<Product> getTypeOfProduct(String typeName) {
    ArrayList<Product> arrayList = new ArrayList();
    for (Product product : products) {
      if (product.product_type.equalsIgnoreCase(typeName))
        arrayList.add(product);
    }
    return arrayList;
  }
}
