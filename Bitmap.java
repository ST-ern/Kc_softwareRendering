import java.util.Arrays;

public class Bitmap {
    private final int m_width;  // width, in pixel, of the img
    private final int m_height;  // height, in pixel, of the img
    private final byte m_compoments[]; //存储具体的argb信息

    /**
     * @author stern
     * @date 2020-01-12 11:01
     * @description just like their name
    */
    public int getM_height() {
        return m_height;
    }
    public int getM_width() {
        return m_width;
    }

    /**
     * @author stern
     * @date 2020-01-12 10:25
     * @description generate a black Bitmap with size and its argb color.
    */
    public Bitmap(int width, int height) {
        m_width = width;
        m_height = height;
        m_compoments = new byte[width * height * 4];
    }

    /**
     * @author stern
     * @date 2020-01-12 10:26
     * @description clear all the color int it.
    */
    public void Clear(byte shade) {
        Arrays.fill(m_compoments, shade);
    }

    /**
     * @author stern
     * @date 2020-01-12 10:27
     * @description generate a color pixel (rgba).
    */
    public void DrawPixel(int x, int y, byte a, byte r, byte g, byte b) {  // change argb to abgr (different type to store)
        int index = (x + y * m_width) * 4;
        m_compoments[index    ] = a;
        m_compoments[index + 1] = r;
        m_compoments[index + 2] = g;
        m_compoments[index + 3] = b;
    }

    /**
     * @author stern
     * @date 2020-01-12 10:28
     * @description together the r,g,b,a char value to a int value
    */
    public void CopyToIntArray(byte[] dest) {
        for(int i=0; i<m_width * m_height; i++) {
            dest[i * 3    ] = m_compoments[i * 4 + 1];
            dest[i * 3 + 1] = m_compoments[i * 4 + 2];
            dest[i * 3 + 2] = m_compoments[i * 4 + 3];
       }
    }

// change char->byte, no need to change to int.
//    /**
//     * @author stern
//     * @date 2020-01-12 10:28
//     * @description together the r,g,b,a char value to a int value
//    */
//    public void CopyToIntArray(int[] dest) {
//        for(int i=0; i<m_width * m_height; i++) {
//            int a = ((int)m_compoments[i * 4]) << 24;
//            int r = ((int)m_compoments[i * 4 + 1]) << 16;
//            int g = ((int)m_compoments[i * 4 + 2]) << 8;
//            int b = ((int)m_compoments[i * 4 + 3]);
//
//            dest[i] = a | r | g | b;  //将argb拼成一个整体的整数表示
//        }
//    }
}
