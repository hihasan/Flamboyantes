package com.flamboyantes.util;

import android.view.Display;

import com.flamboyantes.R;
import com.flamboyantes.model.ModelOne;
import com.flamboyantes.model.ModelTwo;

public class Utils {
    public static ModelOne[] modelOne;
    public static ModelTwo[] modelTwo;

    public static void setModelOne(){
        modelOne = new ModelOne[]{
                new ModelOne(R.drawable.gallery, "Decol Mbayi Don", "5"),
                new ModelOne(R.drawable.gallery,"Florance Buanga","5"),
                new ModelOne(R.drawable.gallery,"Les Juniors Archanges","5"),
                new ModelOne(R.drawable.gallery," Tshiendelele ","5")
        };
    }

    public static void setModelTwo(){
        modelTwo = new ModelTwo[]{
                new ModelTwo(R.drawable.gallery, "Decol Mbayi Don", "5"),
                new ModelTwo(R.drawable.gallery,"Florance Buanga","5")
        };
    }
}
