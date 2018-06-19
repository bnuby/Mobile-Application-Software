package com.example.gibson.carlife.Model;

import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.Model.Product.ProductType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManagement {
  private static OrderCollection orderCollection;
  private static ArrayList<Product> products;
  private static ArrayList<ProductBrand> productBrands;
  private static ArrayList<ProductType> productTypes;
  private static ArrayList<Favorite> favorites;
  private static ArrayList<History> histories;
  private static HashMap<String, ArrayList<Product>> map;

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

  public static Product getProductsById(int id) {
    for(Product product: products)
      if (product.id == id)
        return product;
    return null;
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

//  public static ArrayList<Favorite> getFavorite(){
//    if (favorites == null)
//      favorites = new ArrayList<>();
//    return favorites;
//  }

  public static ArrayList<Favorite> getFavorite() {
    if (favorites == null)
      favorites = new ArrayList<>();
    return favorites;
  }

  public static Favorite getFavoriteByProductID(int product_id) {
    for(Favorite favorite : favorites)
      if(favorite.product_id == product_id)
        return favorite;

    return null;
  }

  public static void clear() {
    orderCollection.carts.clear();
    orderCollection.pending_refunds.clear();
    orderCollection.cancels.clear();
    orderCollection.paids.clear();
    orderCollection.orders.clear();
    histories.clear();
    favorites.clear();
    orderCollection = null;
    favorites = null;
    histories = null;
  }

  public static ArrayList<History> getHistories() {
    if (histories == null)
      histories = new ArrayList<>();
    return histories;
  }

  public static void addSearchTable(String key, Product value) {
    if(map == null)
      map = new HashMap<>();

    if(map.get(key) == null)
      map.put(key, new ArrayList<Product>());
    map.get(key).add(value);
  }

  public static ArrayList<Product> getMapByKey(String key) {
    if(map == null)
      map = new HashMap<>();

    if(map.get(key) == null)
      map.put(key, new ArrayList<Product>());

    return map.get(key);
  }

  // use to check is request one time or not
  public static boolean checkRequest() {
    if(products != null && productBrands != null && productTypes != null) {
      return true;
    }
    return false;
  }
}
