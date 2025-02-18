package cz.adaptech.tesseract4android.sample;

import androidx.core.math.MathUtils;

import org.opencv.core.Scalar;

public class ColorController {

    public enum BasicColor{

        BLUE(0, 0, 255),

        LIME_DARK(0, 109, 37),
        LIME_LIGHT(164, 248, 124),
        PURE_WHITE(255, 255, 255);

        byte[] byteColorConstituents = new byte[3];

        private static byte unsignedIntToSignedByte(int unsignedIntByte){
            return (byte) (unsignedIntByte & 0xFF);
        }
        private static short byteToUnsignedShort(byte signedByte){
            return (short) (signedByte & 0xFF);
        }

        BasicColor(int blue, int green, int red){
            int[] array = new int[3];
            array[0] = blue;
            array[1] = green;
            array[2] = red;
            for(int i=0; i<array.length; i++){
                if(array[0] < 0 || array[0] > 255){
                    throw new IllegalArgumentException("BasicColor value \""+array[i]+"\" at position #"+i+" is out of bounds for unsigned byte.");
                }else{
                    byteColorConstituents[i] = unsignedIntToSignedByte(array[i]);
                }
            }

        }
    }

    private BasicColor primaryColor = null;

    public ColorController(BasicColor primaryColor){
        this.primaryColor = primaryColor;
    }

    public Scalar getScalar(short transparency){
        transparency = (short) MathUtils.clamp((short)0, transparency, (short)255);
        return new Scalar(BasicColor.byteToUnsignedShort(primaryColor.byteColorConstituents[0]), BasicColor.byteToUnsignedShort(primaryColor.byteColorConstituents[1]), BasicColor.byteToUnsignedShort(primaryColor.byteColorConstituents[2]), transparency);
    }

}
