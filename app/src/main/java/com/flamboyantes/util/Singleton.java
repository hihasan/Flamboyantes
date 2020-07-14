package com.flamboyantes.util;

import android.content.Context;
import androidx.fragment.app.Fragment;

public class Singleton {
    private static Singleton ourInstance = new Singleton();
    private Context context;
    private Fragment whichFragmentItIs;

    private Singleton() {
    }

    public static Singleton getInstance() {
        return ourInstance;
    }

    public Context getContext() {
        if (context == null) {
            throw new RuntimeException("Context is null in Singleton singleton. Check if setContext() is called properly.");
        }
        return context;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }



    public void setWhichFragmentItIs(Fragment whichFragmentItIs) {
        this.whichFragmentItIs = whichFragmentItIs;
    }

    public Fragment getWhichFragmentItIs() {
        return whichFragmentItIs;
    }

    public static Singleton getOurInstance() {
        return ourInstance;
    }

}
