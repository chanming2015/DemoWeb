package com.github.chanming.designmodel.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Description: 
 * Create Date:2016年7月14日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class CurrentDisplay implements Observer, DisplayElement
{

    private float temp;
    private float humidity;
    private float pressure;

    /** @author XuMaoSen
     */
    public CurrentDisplay(Observable observable)
    {
        observable.addObserver(this);
    }

    /** @author XuMaoSen
     */
    @Override
    public void display()
    {
        System.out.println(String.format("temp:%d\nhumidity:%d\npressure:%d\n", temp, humidity,
                pressure));
    }

    /** @author XuMaoSen
     */
    @Override
    public void update(Observable o, Object arg)
    {
        if ((o instanceof WeatherObs) && (arg instanceof WeatherData))
        {
            WeatherData data = (WeatherData) arg;
            temp = data.getTemp();
            humidity = data.getHumidity();
            pressure = data.getPressure();
            display();
        }
    }

}
