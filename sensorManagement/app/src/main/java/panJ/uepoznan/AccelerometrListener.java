package panJ.uepoznan;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.util.Observable;

public class AccelerometrListener extends Observable implements SensorEventListener {

    private static final String LOG_NAME = "AccelerometrListener";

    float [] xyz;

    public float[] getXyz() {
        return xyz;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //dostajemy notyfikacje od menadzera sensorow
        xyz = event.values;

        if(xyz.length == 3){
            StringBuilder sb = new StringBuilder();
            sb.append("xyz[0] = ").append(xyz[0]).append('\n');
            sb.append("xyz[1] = ").append(xyz[1]).append('\n');
            sb.append("xyz[2] = ").append(xyz[2]).append('\n');

            Log.d(LOG_NAME,sb.toString());
        } else {
            Log.d(LOG_NAME,"Brak danych. Długosc tablicy = "+xyz.length);
        }
        setChanged();
        notifyObservers();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
