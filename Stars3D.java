import com.sun.org.apache.xml.internal.security.Init;

/**
 * @author stern
 * @date 2020/1/12 13:52
 */
public class Stars3D {
    private final float m_speed;
    private final float m_spread;

    private final float m_starX[];
    private final float m_starY[];
    private final float m_starZ[];

    public Stars3D(int numStars, float spread, float speed) {
        m_spread = spread;
        m_speed = speed;

        m_starX = new float[numStars];
        m_starY = new float[numStars];
        m_starZ = new float[numStars];

        for(int i=0; i<m_starX.length; i++) {
            InitStar(i);
        }
    }

    private void InitStar(int index) {
        m_starX[index] = 2 * ((float)Math.random() - 0.5f) * m_spread;
        m_starY[index] = 2 * ((float)Math.random() - 0.5f) * m_spread;
        m_starZ[index] = ((float)Math.random() + 0.00001f) * m_spread;
    }

    public void UpdateAndRender(Bitmap target, float delta) {
        final float tanHalfFOV = (float)Math.tan(Math.toRadians(70.0/2.0));  // add to change 3D affection

        // stars are draw on a black background
        target.Clear((byte)0x00);

        float halfWidth = target.getM_width()/2.0f;
        float halfHeight = target.getM_height()/2.0f;
        for(int i=0; i<m_starX.length; i++) {
            m_starZ[i] -= delta * m_speed;
            if(m_starZ[i] <= 0) {
                InitStar(i);
            }

            int x = (int)((m_starX[i]/ (m_starZ[i] * tanHalfFOV)) * halfWidth + halfWidth);
            int y = (int)((m_starY[i]/ (m_starZ[i] * tanHalfFOV)) * halfHeight + halfHeight);

            if (x<0 || x>= target.getM_width()  ||
                    (y<0 || y>= target.getM_height()) ) {
                InitStar(i);
            } else {
                target.DrawPixel(x, y, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff);
            }
        }
    }
}
