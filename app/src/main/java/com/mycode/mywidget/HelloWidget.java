package com.mycode.mywidget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class HelloWidget extends AppWidgetProvider{

    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Создаем новый RemoteViews
        RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.activity_main);

        //Intent for Broadcast
        Intent active = new Intent(context, HelloWidget.class);
        active.setAction(ACTION_WIDGET_RECEIVER);
        active.putExtra("msg","Hello");

        //create Pending
        PendingIntent actioPendingIntent = PendingIntent.getBroadcast(context,0,active,0);

        //event
        remoteView.setOnClickPendingIntent(R.id.button,actioPendingIntent);

        //update widget
        appWidgetManager.updateAppWidget(appWidgetIds,remoteView);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if(ACTION_WIDGET_RECEIVER.equals(action)){
            String msg = "null";
            try{
                msg = intent.getStringExtra("msg");
            }catch (NullPointerException e){
                Log.e("Error","msg = null");
            }
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }

        super.onReceive(context, intent);
    }
}
