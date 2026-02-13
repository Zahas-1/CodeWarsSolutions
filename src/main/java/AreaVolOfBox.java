public class AreaVolOfBox {
    public static int[] getSize(int w,int h,int d) {

        int[] result = new int[2];

        // Calculate Surface Area: 2*(w*h + h*d + d*w)
        result[0] = 2 * (w * h + h * d + d * w);

        // Calculate Volume: w*h*d
        result[1] = w * h * d;

        return result;
    }
}
