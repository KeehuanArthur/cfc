package org.cfchome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by arthurlee on 1/19/16.
 */
public class NetworkStateReceiver extends BroadcastReceiver
{
    private static final String TAG = "NetworkStateReceiver";

    public static String NETWORK_CONNECTED = "org.cfchome.network_connection_established";
    public static String NETWORK_DISCONNECTED = "org.cfchome.network_connection_closed";

    @Override
    public void onReceive(final Context context, final Intent intent)
    {

        //Log.d(TAG, "Network connectivity change-----------");

        if (intent.getExtras() != null)
        {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

            if (ni != null && ni.isConnectedOrConnecting())
            {
                //Log.i(TAG, "Network " + ni.getTypeName() + " connected--------------");
                Constants.no_internet_connection = false;
            }
            else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE))
            {
                //Log.d(TAG, "There's no network connectivity--------------");
                Constants.no_internet_connection = true;
            }
        }
    }
}