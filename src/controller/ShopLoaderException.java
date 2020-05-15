package controller;

/**
 * When inventory capacity is reached.
 */
public class ShopLoaderException extends Exception
{
    public ShopLoaderException(String s)
    {
        super(s);
    }
}