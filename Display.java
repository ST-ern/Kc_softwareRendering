import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Display extends Canvas {

    private final JFrame m_frame;  // 用于展示的window
    private final Bitmap m_frameBuffer;
    private final BufferedImage m_displayImage;
    private final byte[] m_displayComponents;
    private BufferStrategy m_bufferStrategy;
    private final Graphics m_graphics;


    /**
     * @author stern
     * @date 2020-01-12 10:50
     * @description creates and initializes a new display.
    */
    public Display(int width, int height, String title) {
        // 设定窗口尺寸
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);

        m_frameBuffer = new Bitmap(width, height);
        m_displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        m_displayComponents =
                ( (DataBufferByte)(m_displayImage.getRaster().getDataBuffer()) ).getData();
        m_frameBuffer.Clear((byte)0x80);
        m_frameBuffer.DrawPixel(100,100,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xff);



        // create a JFrame to show this Diaplay
        m_frame = new JFrame();
        m_frame.add(this);
        m_frame.pack();
        m_frame.setResizable(false);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setLocationRelativeTo(null);
        m_frame.setTitle(title);
        m_frame.setVisible(true);

        //?
        createBufferStrategy(1);
        m_bufferStrategy = getBufferStrategy();
        m_graphics = m_bufferStrategy.getDrawGraphics();
    }

    public void SwapBuffers() {
        m_frameBuffer.CopyToIntArray(m_displayComponents);
        m_graphics.drawImage(m_displayImage, 0, 0,
                m_frameBuffer.getM_width(), m_frameBuffer.getM_height(), null);
        m_bufferStrategy.show();
    }
}
