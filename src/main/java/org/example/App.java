package org.example;

import org.example.dao.CityDaoImpl;

public class App
{
    public static void main( String[] args )
    {
        CityDaoImpl cityDao = new CityDaoImpl();

        cityDao.getFromDistrict("lisboa").forEach(System.out::println);
    }
}
