package controller;

public class ShopFactory 
{
    public static ShopLoader makeShopLoader(String fileName)
    {
        ShopLoader loader = null;
        if (fileName.endsWith(".csv"))
        {
            loader = new CSVFileLoader();
        } 

        return loader;
    }
}