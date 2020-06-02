package com.example.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text1,text2,text3,text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=findViewById(R.id.textView);
        text2=findViewById(R.id.textView2);
        text3=findViewById(R.id.textView3);
        text4=findViewById(R.id.textView4);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(getBatteryPlugStatus,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(getBatteryTemp,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(getBatteryLevel,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(getBatteryHealth,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(getBatteryPlugStatus);
    }
    private BroadcastReceiver getBatteryTemp = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int temp=intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
                text2.setText("Temperature:\n"+(temp/10)+"°");
        }
    };
    private BroadcastReceiver getBatteryLevel=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            text3.setText("Battery Level:\n"+level);
        }
    };
    private BroadcastReceiver getBatteryPlugStatus =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status=intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);

            if(status!=0)
            {
                text1.setText("Battery Plugged:\nOn");
            }
            else
            {
                text1.setText("Battery Plugged:\nOf");
            }
        }
    };
    private BroadcastReceiver getBatteryHealth= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int health=intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
            switch (health)
            {
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    text4.setText("Battery Health:\nUnknown");
                    break;
                case BatteryManager.BATTERY_HEALTH_COLD:
                    text4.setText("Battery Health:\nCold");
                    break;
                case BatteryManager.BATTERY_HEALTH_DEAD:
                    text4.setText("Battery Health:\nDead");
                    break;
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    text4.setText("Battery Health:\nGood");
                    break;
                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                    text4.setText("Battery Health:\nOverVoltage");
                    break;
                case  BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    text4.setText("Battery Health:\nOverHeat");
                    break;
                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    text4.setText("This information\nis not found");
                    break;
            }

        }
    };

}
