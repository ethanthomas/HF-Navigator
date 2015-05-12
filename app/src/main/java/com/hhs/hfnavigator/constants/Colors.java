package com.hhs.hfnavigator.constants;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by user on 1/4/15.
 */
public class Colors {

    public static int[] materialColors = { Color.parseColor("#4AB86E"), Color.parseColor("#3399FF"),
            Color.parseColor("#009688"), Color.parseColor("#E64545"), Color.parseColor("#FF774C")
    };

    public static int[] materialStatusColors = { Color.parseColor("#1E7C1D"), Color.parseColor("#009688"),
            Color.parseColor("#478883"), Color.parseColor("#e51c23"), Color.parseColor("#E66B44")
    };

    public static int randomMaterialColor() {
        return materialColors[new Random().nextInt(materialColors.length)];
    }

}