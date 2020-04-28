package panJ.uepoznan;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.Observable;

public class GyroscopeListener extends Observable implements SensorEventListener {

    float[] xyz;

    public float[] getXyz() {
        return xyz;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xyz = event.values;
        setChanged();
        notifyObservers();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
