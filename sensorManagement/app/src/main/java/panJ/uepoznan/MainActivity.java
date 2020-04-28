package panJ.uepoznan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    private SensorManager sensorManager;
    private AccelerometrListener accelerometrListener;
    private LightListener lightListener;
    private PressureListener pressureListener;
    private GyroscopeListener gyroscopeListener;

    private static final String LOG_NAME = "MainActivity";

    private TextView Gx;
    private TextView Gy;
    private TextView Gz;
    private TextView Ax;
    private TextView Ay;
    private TextView Az;
    private TextView LS;
    private TextView P;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometrListener = new AccelerometrListener();
        lightListener = new LightListener();
        pressureListener = new PressureListener();
        gyroscopeListener = new GyroscopeListener();
        Gx = findViewById(R.id.textViewGx);
        Gy = findViewById(R.id.textViewGy);
        Gz = findViewById(R.id.textViewGz);
        Ax = findViewById(R.id.textViewAx);
        Ay = findViewById(R.id.textViewAy);
        Az = findViewById(R.id.textViewAz);
        LS = findViewById(R.id.textViewLS);
        P = findViewById(R.id.textViewP);

        accelerometrListener.addObserver(this);
        lightListener.addObserver(this);
        pressureListener.addObserver(this);
        gyroscopeListener.addObserver(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        registerListener();
    }

    private void registerListener() {
        if(sensorManager != null){
            Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(accelerometrListener,accelSensor,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(LOG_NAME,"Accelerometr registered");

            Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener(lightListener,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(LOG_NAME,"Ligth sensor registered");

            Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensorManager.registerListener(gyroscopeListener,gyroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(LOG_NAME,"Ligth sensor registered");

            Sensor pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            sensorManager.registerListener(pressureListener,pressureSensor,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(LOG_NAME,"Ligth sensor registered");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterListeners();
    }

    private void unregisterListeners() {
        sensorManager.unregisterListener(accelerometrListener);
        sensorManager.unregisterListener(lightListener);
        sensorManager.unregisterListener(pressureListener);
        sensorManager.unregisterListener(gyroscopeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterListeners();
    }

    @Override
    public void update(Observable o, Object arg) {

        if(o != null && o instanceof LightListener){
        LightListener lightListener = (LightListener)o;
        LS.setText(Float.toString(lightListener.getLight()));
    }
        if(o != null && o instanceof PressureListener){
            PressureListener pressureListener = (PressureListener)o;
            P.setText(Float.toString(pressureListener.getPressure()));
        }

        if(o != null && o instanceof AccelerometrListener){
            AccelerometrListener accelerometrListener = (AccelerometrListener)o;
            Ax.setText(Float.toString(accelerometrListener.getXyz()[0]));
            Ay.setText(Float.toString(accelerometrListener.getXyz()[1]));
            Az.setText(Float.toString(accelerometrListener.getXyz()[2]));
        }

        if(o != null && o instanceof GyroscopeListener){
            GyroscopeListener gyroscopeListener = (GyroscopeListener) o;
            Gx.setText(Float.toString(gyroscopeListener.getXyz()[0]));
            Gy.setText(Float.toString(gyroscopeListener.getXyz()[1]));
            Gz.setText(Float.toString(gyroscopeListener.getXyz()[2]));
        }

    }
}
