package com.github.chanming.designmodel.observer;

import java.util.Observable;

/**
 * Description: 
 * Create Date:2016年7月14日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class WeatherObs extends Observable
{

    /** @author XuMaoSen
     */
    public WeatherObs()
    {
    }

    public void measurementsChanged(WeatherData data)
    {
        setChanged();
        notifyObservers(data);
    }

    public void setMeasurements(float temp, float humidity, float pressure)
    {
        WeatherData data = new WeatherData();
        data.setTemp(temp);
        data.setHumidity(humidity);
        data.setPressure(pressure);
        measurementsChanged(data);
    }

}
