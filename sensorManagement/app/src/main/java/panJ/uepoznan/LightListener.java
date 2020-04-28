package panJ.uepoznan;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.util.Observable;

public class LightListener extends Observable implements SensorEventListener {

    private final static String LOG_NAME = "LightListener";
    float light;

    public float getLight() {
        return light;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        light = event.values[0];
        Log.d(LOG_NAME,"Zmiana światłosci = "+event.values[0]);
        setChanged();
        notifyObservers();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
