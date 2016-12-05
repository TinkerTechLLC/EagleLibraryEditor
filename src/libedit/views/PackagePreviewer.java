package libedit.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import libedit.models.containers.Pkg;
import libedit.models.enums.Rot.Rotation;
import libedit.models.objects.SMD;

public class PackagePreviewer extends JPanel {

    PackagePreviewer pp = this;
    float            range;
    Pkg              pkg;

    /**
     * Create the panel.
     */
    public PackagePreviewer() {
        addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                range += e.getWheelRotation();
                pp.repaint();
            }
        });
        System.out.println("Constructing new previewer");
        range = 50;
    }

    public void setPackage(Pkg pkg) {
        this.pkg = pkg;
    }

    private float pxPerMM() {
        return ((float) this.getWidth()) / range;
    }

    private int mmToPx(float mm) {
        int ret = Math.round(pxPerMM() * mm);
        System.out.println("mm: " + mm + " px: " + ret);
        return ret;
    }

    private float pxToMm(int px) {
        float ret = pxPerMM() * px;
        System.out.println(" px: " + px + "mm: " + ret);
        return ret;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Painting previewer");
        System.out.println("px / mm: " + pxPerMM());
        Graphics2D g2d = (Graphics2D) g;

        // Paint center mark
        g2d.setColor(Color.GREEN);
        int markLength = 5;
        Stroke s = new BasicStroke(2);
        g2d.setStroke(s);
        g2d.drawLine(this.getWidth() / 2 - markLength, this.getHeight() / 2,
                this.getWidth() / 2 + markLength, this.getHeight() / 2);
        g2d.drawLine(this.getWidth() / 2, this.getHeight() / 2 - markLength, this.getWidth() / 2,
                this.getHeight() / 2 + markLength);

        if (pkg == null) {
            return;
        }

        for (SMD smd : pkg.getSMDPads()) {
            System.out.println("Printing pad " + smd.getName());
            paintSMDPad(smd, g2d);
        }
    }

    private void paintSMDPad(SMD smd, Graphics2D g2d) {
        System.out.println("Painting SMD pad");
        float w, h, x, y;
        if (smd.getRot() == Rotation.R0 || smd.getRot() == Rotation.R180) {
            w = smd.getX();
            h = smd.getY();
        }
        else {
            w = smd.getY();
            h = smd.getX();
        }
        x = smd.getDx() - w / 2;
        y = smd.getDy() - h / 2;
        g2d.setColor(Color.RED);
        g2d.fillRect(mmToPx(x) + this.getWidth() / 2, mmToPx(y) + this.getHeight() / 2, mmToPx(w), mmToPx(h));
    }

}
