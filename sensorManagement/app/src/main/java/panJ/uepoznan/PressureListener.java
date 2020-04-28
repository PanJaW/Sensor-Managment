package panJ.uepoznan;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.Observable;

public class PressureListener extends Observable implements SensorEventListener{

    float pressure;

    public float getPressure() {
        return pressure;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        pressure = event.values[0];
        setChanged();
        notifyObservers();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
