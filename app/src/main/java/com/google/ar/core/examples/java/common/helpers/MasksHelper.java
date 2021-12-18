package com.google.ar.core.examples.java.common.helpers;

import android.content.Context;
import android.util.Log;

import com.google.ar.core.AugmentedFace;
import com.google.ar.core.examples.java.augmentedfaces.AugmentedFaceRenderer;
import com.google.ar.core.examples.java.common.rendering.ObjectRenderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasksHelper {
    private static final float[] DEFAULT_COLOR = new float[] {0f, 0f, 0f, 0f};



    private final List<Object[]> maskObjects = new ArrayList<>();
    private final List<String[]> positionObjects = new ArrayList<>();
    private final List<Float[]> scaleObjects = new ArrayList<>();

    private Map<String, Float[]> PositionsMap = new HashMap<String, Float[]>() {{
        put("FOREHEAD", new Float[] {-0.05f, 0.045f, 0.06f}); // FOREHEAD
        put("FOREHEAD_RIGHT", new Float[] {-0.02f, 0.050f, 0.05f}); // FOREHEAD_RIGHT
        put("FOREHEAD_LEFT", new Float[] {0.025f, 0.050f, 0.05f}); // FOREHEAD_LEFT
        put("NOSE", new Float[] {0.00f, -0.007f, 0.06f}); // NOSE
        put("MOUTH", new Float[] {-0.05f, 0.045f, 0.06f}); // MOUTH
        put("MUSTACHE", new Float[] {-0.05f, 0.045f, 0.06f}); // MUSTACHE
        put("EYES", new Float[] {-0.05f, 0.045f, 0.06f}); // EYES
    }};

    public enum Positions {
        FOREHEAD {
            @Override
            public String toString() {
                return "FOREHEAD";
            }
        },
        FOREHEAD_RIGHT {
            @Override
            public String toString() {
                return "FOREHEAD_RIGHT";
            }
        },
        FOREHEAD_LEFT {
            @Override
            public String toString() {
                return "FOREHEAD_LEFT";
            }
        },
        NOSE {
            @Override
            public String toString() {
                return "NOSE";
            }
        },
        MOUTH {
            @Override
            public String toString() {
                return "MOUTH";
            }
        },
        MUSTACHE {
            @Override
            public String toString() {
                return "MUSTACHE";
            }
        },
        EYES {
            @Override
            public String toString() {
                return "EYES";
            }
        }
    }
    private final List<float[]> tempSquareMatrixList = new ArrayList<>();
    public MasksHelper(){
        final float[] tempSquareMatrix = new float[16];

        for(int i = 0; i < 10; i++){
            tempSquareMatrixList.add(tempSquareMatrix);
        }
    }

    public void addMaskObjects(Context context, String[][] mask){
        if(mask.length > 9){
            throw new IllegalArgumentException("One mask cannot have more than 9 parts");
        }

        if(mask.length < 1){
            throw new IllegalArgumentException("One mask must have at least 1 part");
        }

        Object[] maskArray = new Object[9];
        String[] maskPositionArray = new String[9];
        Float[] maskScaleArray = new Float[9];
        int i = 0;
        for (String[] part : mask){
            if(part.length < 1){
                throw new IllegalArgumentException("Array must have at least 1 argument, first argument means a texture, second argument means a position if need, third argument means a scale if need, fourth argument means a 3d obj if need");
            }
            try {
                String texture = part[0];
                String position = "";
                if(part.length > 1){
                    position = part[1];
                }

                float scale = 1;
                if(part.length > 2){
                    scale = Float.valueOf(part[2].trim()).floatValue();
                }

                String form = "";
                if(part.length > 3){
                    form = part[3];
                }

                if(position.isEmpty()){
                    AugmentedFaceRenderer tempObject = new AugmentedFaceRenderer();
                    tempObject.createOnGlThread(context, texture);
                    tempObject.setMaterialProperties(0.0f, 1.0f, 0.1f, 6.0f);
                    maskArray[i] = tempObject;
                }else{
                    ObjectRenderer tempObject = new ObjectRenderer();
                    tempObject.createOnGlThread(context, form, texture);
                    tempObject.setMaterialProperties(0.0f, 1.0f, 0.1f, 6.0f);
                    tempObject.setBlendMode(ObjectRenderer.BlendMode.AlphaBlending);
                    maskArray[i] = tempObject;
                }



                maskPositionArray[i] = position;
                maskScaleArray[i] = scale;
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        maskObjects.add(maskArray);
        positionObjects.add(maskPositionArray);
        scaleObjects.add(maskScaleArray);
    }
    public void renderMask(AugmentedFace face,float[] viewMatrix, float[] projectionMatrix, final float[] colorCorrectionRgba,float[] modelMatrix, Integer index) {
        Object[] mask = maskObjects.get(index);
        String[] maskPosition = positionObjects.get(index);
        Float[] maskScale = scaleObjects.get(index);

        int i = 0;
        for(Object part :mask){
            if(part == null){
                break;
            }
            String position = maskPosition[i];
            Float[] positionArr = getPositionByName(position);
            Float scale = maskScale[i];

            if(positionArr == null){
                AugmentedFaceRenderer partRendered = (AugmentedFaceRenderer) part;

                partRendered.draw(projectionMatrix, viewMatrix, modelMatrix, colorCorrectionRgba, face);
            }else{
                ObjectRenderer partRendered = (ObjectRenderer)part;
                face.getCenterPose().compose(face.getCenterPose().makeTranslation(positionArr[0],positionArr[1],positionArr[2])).toMatrix(tempSquareMatrixList.get(i), 0);
                partRendered.updateModelMatrix(tempSquareMatrixList.get(i), scale);
                partRendered.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, DEFAULT_COLOR);
            }


            i++;
        }
    }




    public Float[] getPositionByName(String name){
        if(name != null && name.isEmpty()){
            return null;
        }
        return PositionsMap.get(name);
    }


    public Integer getMaskCount(){
        return maskObjects.size();
    }
}
