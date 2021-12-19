package com.google.ar.core.examples.java.augmentedfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.ar.core.examples.java.common.MaskConfig;
import com.google.ar.core.examples.java.common.helpers.MasksHelper;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Как буд-то это какое-то приложение и нам нужно открыть экран с масками в реальном времени
        // Инициализимуем маски, и сохраняем маски в конфиг MaskConfig.setInstance(maskConfig);
        initMasksConfig();

        // Теперь эти маски будут доступны на следующем экране, открываем его
        Intent intent = new Intent(this, AugmentedFacesActivity.class);
        startActivity(intent);
        finish();
    }

    private void initMasksConfig() {
        String[] mask1part1 = {"models/3_deer_horns.png", MasksHelper.Positions.FOREHEAD.toString(),"2.4", "models/flat_square.obj"} ;
        String[][] mask1 = {mask1part1};

        String[] mask2part1 = {"models/cap_ducs.png",MasksHelper.Positions.FOREHEAD.toString(),"1", "models/cap3.obj"} ;
//        String[] mask2part2 = {"models/part_cap.png"};
        String[] mask2part2 = {"models/part_cap.png", MasksHelper.Positions.FOREHEAD.toString(),"2.4", "models/flat_square2.obj"};
        String[][] mask2 = {mask2part1,mask2part2};

        String[] mask2_1part1 = {"models/cap_duck5.png",MasksHelper.Positions.FOREHEAD.toString(),"2", "models/flat_square4.obj"} ;
//        String[] mask2part2 = {"models/part_cap.png"};
        String[][] mask2_1 = {mask2_1part1};

        String[] mask3part1 = {"models/nose_fox.png",MasksHelper.Positions.NOSE.toString(),"1", "models/nose_fox.obj"} ;
        String[] mask3part2 = {"models/ear_fox.png",MasksHelper.Positions.FOREHEAD_RIGHT.toString(),"1", "models/forehead_right.obj"} ;
        String[] mask3part3 = {"models/ear_fox.png",MasksHelper.Positions.FOREHEAD_LEFT.toString(),"1", "models/forehead_left.obj"} ;
        String[] mask3part4 = {"models/nose_fox2.png",MasksHelper.Positions.NOSE.toString(),"1", "models/nose_fox2.obj"} ;
        String[][] mask3 = {mask3part1,mask3part2,mask3part3,mask3part4};

        String[] mask4part1 = {"models/glass.png"};
        String[][] mask4 = {mask4part1};

        String[] mask5part1 = {"models/nose_bear.png",MasksHelper.Positions.NOSE.toString(),"1", "models/nose.obj"} ;
        String[] mask5part2 = {"models/ear_bear.png",MasksHelper.Positions.FOREHEAD_RIGHT.toString(),"1", "models/ear_bear_right.obj"} ;
        String[] mask5part3 = {"models/ear_bear.png",MasksHelper.Positions.FOREHEAD_LEFT.toString(),"1", "models/ear_bear_left.obj"} ;
        String[][] mask5 = {mask5part1,mask5part2,mask5part3};

        String[] mask6part1 = {"models/cap_green.png",MasksHelper.Positions.FOREHEAD.toString(),"2.1", "models/flat_square3.obj"} ;
        String[] mask6part2 = {"models/glass2.png"};
        String[][] mask6 = {mask6part1,mask6part2};

        List<String[][]> maskConfig = new ArrayList<>();

        maskConfig.add(mask1);
        maskConfig.add(mask2);
        maskConfig.add(mask2_1);

        maskConfig.add(mask3);
        maskConfig.add(mask4);
        maskConfig.add(mask5);
        maskConfig.add(mask6);
        MaskConfig.setInstance(maskConfig);
    }
}