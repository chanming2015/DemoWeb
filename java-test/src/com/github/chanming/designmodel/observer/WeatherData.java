package com.github.chanming.designmodel.observer;

/**
 * Description: 
 * Create Date:2016年7月15日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class WeatherData
{

    private float temp;
    private float humidity;
    private float pressure;

    public float getTemp()
    {
        return temp;
    }

    public void setTemp(float temp)
    {
        this.temp = temp;
    }

    public float getHumidity()
    {
        return humidity;
    }

    public void setHumidity(float humidity)
    {
        this.humidity = humidity;
    }

    public float getPressure()
    {
        return pressure;
    }

    public void setPressure(float pressure)
    {
        this.pressure = pressure;
    }

}
