package libedit.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import libedit.models.containers.Pkg;
import libedit.models.enums.Layers;
import libedit.models.enums.Rot.Rotation;
import libedit.models.objects.SMD;
import libedit.models.objects.Wire;

public class PackagePreviewer extends JPanel {

    PackagePreviewer pp        = this;
    final int        MIN_RANGE = 4;
    float            range;
    Pkg              pkg;

    /**
     * Create the panel.
     */
    public PackagePreviewer() {
        addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                range += e.getWheelRotation();
                if (range < MIN_RANGE) {
                    range = MIN_RANGE;
                }
                pp.repaint();
            }
        });
        range = 20;
    }

    public void setPackage(Pkg pkg) {
        this.pkg = pkg;
    }

    private float pxPerMM() {
        return ((float) this.getWidth()) / range;
    }

    private Point mmPointToPxPoint(float x, float y) {
        int xPx = Math.round(this.getWidth() / 2 + Math.round(pxPerMM() * x));
        int yPx = Math.round(this.getHeight() / 2 - Math.round(pxPerMM() * y));
        return new Point(xPx, yPx);
    }

    private int mmToPx(float mm) {
        int ret = Math.round(pxPerMM() * mm);
        // System.out.println("mm: " + mm + " px: " + ret);
        return ret;
    }

    private float pxToMm(int px) {
        float ret = pxPerMM() * px;
        // System.out.println(" px: " + px + "mm: " + ret);
        return ret;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (pkg != null) {
            for (SMD smd : pkg.getSMDPads()) {
                paintSMDPad(smd, g2d);
            }
            for (Wire wire : pkg.getWires()) {
                paintWire(wire, g2d);
            }
        }

        paintGrid(g2d);
        paintCenterMark(g2d);
    }

    private void paintWire(Wire wire, Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        Stroke s = new BasicStroke(2);
        g2d.setStroke(s);
        Point p1 = mmPointToPxPoint(wire.getX1(), wire.getY1());
        Point p2 = mmPointToPxPoint(wire.getX2(), wire.getY2());
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    private void paintSMDPad(SMD smd, Graphics2D g2d) {
        float w, h, x, y;
        if (smd.getRot() == Rotation.R0 || smd.getRot() == Rotation.R180) {
            w = smd.getDx();
            h = smd.getDy();
        }
        else {
            w = smd.getDy();
            h = smd.getDx();
        }
        x = smd.getX() - w / 2;
        y = smd.getY() + h / 2;
        if (smd.getLayer() == Layers.TOP) {
            g2d.setColor(Color.RED);
        }
        else if (smd.getLayer() == Layers.BOTTOM) {
            g2d.setColor(Color.BLUE);
        }
        // An SMD pad may only be on the top or bottom layer, so anything else
        // shouldn't be printed
        else {
            return;
        }
        Point location = this.mmPointToPxPoint(x, y);
        g2d.fillRect(location.x, location.y, mmToPx(w), mmToPx(h));
    }

    private void paintGrid(Graphics2D g2d) {
        int halfLineCount = Math.round((float) this.getWidth() / 2 / pxPerMM());
        int DIRECTION_COUNT = 4;
        Color c = new Color(100, 100, 100, 50);
        g2d.setColor(c);

        Stroke s = new BasicStroke(1);
        g2d.setStroke(s);
        for (int i = 0; i < DIRECTION_COUNT; i++) {
            for (int j = 0; j < halfLineCount; j++) {
                int sign = i % 2 == 0 ? 1 : -1;
                int x1 = i < 2 ? 0 : Math.round(this.getWidth() / 2 + j * pxPerMM() * sign);
                int x2 = i < 2 ? this.getWidth() : x1;
                int y1 = i < 2 ? Math.round(this.getHeight() / 2 + j * pxPerMM() * sign) : 0;
                int y2 = i < 2 ? y1 : this.getHeight();
                g2d.drawLine(x1, y1, x2, y2);
            }
        }
    }

    private void paintCenterMark(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        int markLength = 7;
        Stroke s = new BasicStroke(3);
        g2d.setStroke(s);
        g2d.drawLine(this.getWidth() / 2 - markLength, this.getHeight() / 2,
                this.getWidth() / 2 + markLength, this.getHeight() / 2);
        g2d.drawLine(this.getWidth() / 2, this.getHeight() / 2 - markLength, this.getWidth() / 2,
                this.getHeight() / 2 + markLength);
    }
}
