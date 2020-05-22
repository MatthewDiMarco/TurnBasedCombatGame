package controller;

/**
 * This exception can be thrown inside the "load()" method of a shop loader 
 * whenever an error occurs.
 */
public class ShopLoaderException extends Exception
{
    public ShopLoaderException(String s)
    {
        super(s);
    }
}