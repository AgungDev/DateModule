package fun5i.module.week;

public class OtherFunction {

    private static final String TAG = "OtherFunction";

    private static final float DP = Math.round(1.33);

    public OtherFunction(){}

    public int strToInt(String str){
        char[] ch  = str.toCharArray();
        return (int)ch[0];
    }

    public float pxToDP(float px){
        return Math.round(DP*px);
    }

    public float dpToPX(float dp){
        return Math.round(DP/dp);
    }

    public int hexToDEC(String hex){
        return Integer.parseInt(hex, 16);
    }

    public int[] stringToARGB(String hex){
        int[] out = new int[4];
        String[] hexSplit = splitStringLength(hex, hex.length());
        //Log.d(TAG, hex+" stringToARGB: "+hexSplit[0]+" "+hexSplit[1]);
        if (hexSplit.length == 4){
            out[0] = hexToDEC(hexSplit[0]); // alpha
            out[1] = hexToDEC(hexSplit[1]);
            out[2] = hexToDEC(hexSplit[2]);
            out[3] = hexToDEC(hexSplit[3]);

        }else if(hexSplit.length == 3){
            out[0] = 0; // 00
            out[1] = hexToDEC(hexSplit[0]); //red
            out[2] = hexToDEC(hexSplit[1]); // green
            out[3] = hexToDEC(hexSplit[2]); // blue
        }else{
            out = new int[]{
                    255, 244, 244, 244
            };
        }
        return out;
    }

    public String[] splitStringLength(String s, int L){
        String[] a = new String[(L==6)?3:4];
        if (L == 6){
            a[0] = s.substring(0, 2);
            a[1] = s.substring(2, 4);
            a[2] = s.substring(4, 6);
        }else{
            a[0] = s.substring(0, 2);
            a[1] = s.substring(2, 4);
            a[2] = s.substring(4, 6);
            a[3] = s.substring(6, 8);
        }
        /*s = s.toUpperCase();
        return s.split("(?<=\\G.{2})");*/

        return a;
    }

    public int dpToPIXEL(float scale){
        return (int) (12 * scale + 0.5f);
    }

    public static String implode(String separator, String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            //data.length - 1 => to not add separator at the end
            if (!data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(data[i]);
                sb.append(separator);
            }
        }
        sb.append(data[data.length - 1].trim());
        return sb.toString();
    }
}
