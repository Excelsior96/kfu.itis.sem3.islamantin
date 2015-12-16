/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingpainting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
/**
 *
 * @author islam
 */
public class SwingPainting extends JApplet {
    public static ColorSelectionField csf1;
    public static ColorSelectionField csf2;
    public static ColorSelectionField csf3;
    public static RPanel rp;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Swing Painting");
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1,3));
         JPanel colorp = new JPanel();
         jp.add(colorp);
         colorp.setLayout(new GridLayout(1,3));
         csf1 = new ColorSelectionField();
        csf2 = new ColorSelectionField();
        csf3 = new ColorSelectionField();
        csf1.setBackground(Color.red);
        csf2.setBackground(Color.green);
        csf3.setBackground(Color.blue);
        csf1.addActionListener(new ChangeColorActionListener());
        csf2.addActionListener(new ChangeColorActionListener());
        csf3.addActionListener(new ChangeColorActionListener());
        colorp.add(csf1);
        colorp.add(csf2);
        colorp.add(csf3);
        JButton reverce = new JButton("ЯR");
        reverce.addActionListener(new ReverceImageActionListener());
        colorp.add(reverce);
        //
        frame.add(jp, BorderLayout.SOUTH);       
        SwingPainting applet = new SwingPainting();
        frame.getContentPane();
        frame.add(applet);
        rp = new RPanel();
        frame.add(rp);
        applet.start();
        frame.setSize(500, 500);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // задаётся размер для BufferedImage
        rp.setBISize(rp.getWidth(), rp.getHeight());
  }
    public static class ReverceImageActionListener implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent e) {
              rp.reflectImage();
          }
     }
    
    public static class ChangeColorActionListener implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent e) {
              rp.setColor(csf1.getValue(), csf2.getValue(), csf3.getValue());
          }
     }
    
//    старый код, который рисует солнышко на фоне неба :)
    
//    public static class ChangeColorDocumentListener implements DocumentListener {
//        public void insertUpdate(DocumentEvent e) {
//                rp.setColor(csf1.getValue(), csf2.getValue(), csf3.getValue());
//        }
//        public void removeUpdate(DocumentEvent e) {
//            rp.setColor(csf1.getValue(), csf2.getValue(), csf3.getValue());
//        }
//        public void changedUpdate(DocumentEvent e) {
//            rp.setColor(csf1.getValue(), csf2.getValue(), csf3.getValue());
//        }
//    }
  
//        JFrame frame = new JFrame();
//        frame.setSize(460, 500);
//        frame.setTitle("Sun and sky");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JButton jb = new JButton(); 
//
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//              frame.setVisible(true);
//           }
//        });
////        Custom component = new Custom();
////        frame.add(component);
//        frame.getContentPane().validate();
//        frame.getContentPane().repaint();
//    }
//    
//    static class Custom extends JLabel {
//
//        @Override
//        public void paintComponent(Graphics g) {
//            int margin = 10;
//            Dimension dim = getSize();
//            super.paintComponent(g);
//            g.setColor(Color.blue);
//            g.fillRect(margin, margin, dim.width - margin * 2, dim.height - margin * 2);
//            g.setColor(Color.yellow);
//            g.fillOval(margin+50, margin+50, 100, 100);
//            g.drawLine(margin+100, margin+100, dim.width - margin * 2 + 10, dim.height - margin * 2);
//            g.drawLine(margin+100, margin+100, dim.width - margin * 2 + 10, dim.height - margin * 2 - 100);
//            g.drawLine(margin+100, margin+100, dim.width - margin * 2 + 10, dim.height - margin * 2 - 200);
//            g.drawLine(margin+100, margin+100, dim.width - margin * 2 + 10, dim.height - margin * 2 - 300);
//            g.drawLine(margin+100, margin+100, dim.width - margin * 2 -100, dim.height - margin * 2 +10);
//            g.drawLine(margin+100, margin+100, dim.width - margin * 2 -200, dim.height - margin * 2 +10);
//            g.drawLine(margin+100, margin+100, dim.width - margin * 2 -300, dim.height - margin * 2 +10);
//        }
//    }
}






//панель рисования
class RPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener
{
  int brushSize=20;
  int eraiserSize =brushSize*2;
  private Graphics2D graph; 
  private Point line=new Point(0,0);
  Color customColor;
  BufferedImage bi;
  
  public RPanel()
  {
      customColor = Color.BLACK;
    setBackground(new Color(255, 255, 255));
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
  }
  
  public void setColor(int r, int g, int b){
      customColor = new Color(r,g,b);
  }
  
  
  // метод отражения изображений
  public void reflectImage(){
//      задаём преобразование
      AffineTransform at = new AffineTransform();
      at = AffineTransform.getScaleInstance(-1, 1);
      at.translate(-bi.getWidth(),0);
      AffineTransformOp  op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      bi = op.filter(bi, null);
//      рисуем
      graph.drawImage(bi, at, null);
      repaint();
  }
  
  
          // создаётся bufferedimage из main метода с задаными размерами
  public void setBISize(int w, int h){
       bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
  }
  
  @Override
    public void paintComponent(Graphics g) {
         g.drawImage(bi,0, 0, Color.WHITE, this);
    }
  
  public void mouseClicked(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mouseMoved(MouseEvent e){}
  
  @Override
  public void mousePressed(MouseEvent e)
  {
    line.move(e.getX(), e.getY());
  }
  @Override
  public void mouseDragged(MouseEvent e)
  {     
    graph = (Graphics2D) bi.getGraphics();
    if (e.isMetaDown()) 
    {
      graph.setColor(getBackground());
      graph.fillOval(e.getX() - (eraiserSize/2), e.getY() - (eraiserSize/2), eraiserSize, eraiserSize);
    }
    else
    {
      graph.setColor(customColor);
      graph.fillOval(e.getX() - (brushSize/2), e.getY() - (brushSize/2), brushSize, brushSize);
    }
    line.move(e.getX(), e.getY());
    graph.dispose();
    repaint();
  }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        brushSize = brushSize + e.getWheelRotation()*2;
        eraiserSize =brushSize*2;
    }
}


// поле выбора цвета
class ColorSelectionField extends JTextField implements KeyListener {
    
    private final int maxlength = 3;
    private final int maxvalue = 255;
    private String oldtext;
    
    public ColorSelectionField(){
        oldtext = "";
        addKeyListener(this);
    }
    
    
    public int getMaxLength(){
        return maxlength;
    }

    public void keyPressed(KeyEvent e){
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        Pattern pat = Pattern.compile("\\d{0,3}");
        Matcher m = pat.matcher(this.getText());
        if(!m.matches() || Integer.parseInt(this.getText())>maxvalue){
            this.setText(oldtext);
            return;
        }
        oldtext = this.getText();
    }
    
    public int getValue(){
        if (oldtext.equals("")){
            return 0;
        }
        return Integer.parseInt(oldtext);
    }
    
    public void keyTyped(KeyEvent e){
    }
}
    

     
