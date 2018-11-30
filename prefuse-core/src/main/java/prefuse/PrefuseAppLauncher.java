/*     */ package prefuse;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.net.URL;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrefuseAppLauncher
/*     */   extends JFrame
/*     */ {
/*     */   private AppDescription[] desc;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  29 */     setLookAndFeel();
/*  30 */     AppDescription[] desc = getAppDescriptions();
/*  31 */     JFrame f = new PrefuseAppLauncher(desc);
/*  32 */     f.pack();
/*  33 */     f.setVisible(true);
/*     */   }
/*     */   
/*     */   public static AppDescription[] getAppDescriptions() {
/*  37 */     AppDescription[] desc = {
/*  38 */       new AppDescription("Radial Graph Explorer", "radial.png", "prefuse.radialexplorer.RadialGraphExplorer", "/friendster.xml"), 
/*  39 */       new AppDescription("Graph Editor", "editor.png", "prefuse.grapheditor.GraphEditor", null), 
/*  40 */       new AppDescription("Data Mountain", "datamountain.png", "prefuse.datamountain.DataMountain", "/data.xml"), 
/*  41 */       new AppDescription("Hyperbolic Tree", "hyperbolic.png", "prefuse.hyperbolictree.HyperbolicTree", "/chitest.hdir") };
/*     */     
/*  43 */     return desc;
/*     */   }
/*     */   
/*     */   public static final void setLookAndFeel() {
/*     */     try {
/*  48 */       String laf = UIManager.getSystemLookAndFeelClassName();
/*  49 */       UIManager.setLookAndFeel(laf);
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  56 */   private Action launcher = new LaunchAction();
/*     */   
/*     */   public PrefuseAppLauncher(AppDescription[] desc) {
/*  59 */     super("prefuse application launcher");
/*  60 */     this.desc = desc;
/*  61 */     initFrame();
/*  62 */     setDefaultCloseOperation(3);
/*     */   }
/*     */   
/*     */   public void initFrame() {
/*  66 */     Container c = getContentPane();
/*  67 */     c.setLayout(new BoxLayout(c, 1));
/*     */     
/*  69 */     int numcols = 2;
/*  70 */     for (int i = 0; i < this.desc.length; i += numcols) {
/*  71 */       c.add(Box.createVerticalStrut(5));
/*  72 */       c.add(Box.createVerticalGlue());
/*  73 */       Box b = new Box(0);
/*     */       
/*  75 */       for (int j = 0; (j < numcols) && (i + j < this.desc.length); j++) {
/*  76 */         b.add(Box.createHorizontalStrut(5));
/*  77 */         b.add(Box.createHorizontalGlue());
/*  78 */         JButton but = new JButton("An Application");
/*  79 */         but.setFont(new Font("Verdana", 1, 18));
/*  80 */         but.putClientProperty("app", this.desc[(i + j)]);
/*  81 */         but.setAction(this.launcher);
/*  82 */         but.setText(this.desc[(i + j)].name);
/*  83 */         if (this.desc[(i + j)].image != null) {
/*  84 */           URL url = getClass().getResource(this.desc[(i + j)].image);
/*  85 */           but.setIcon(new ImageIcon(url));
/*     */         }
/*  87 */         but.setVerticalTextPosition(3);
/*  88 */         but.setHorizontalTextPosition(0);
/*  89 */         b.add(but);
/*     */       }
/*  91 */       b.add(Box.createHorizontalStrut(5));
/*  92 */       b.add(Box.createHorizontalGlue());
/*  93 */       c.add(b);
/*     */     }
/*  95 */     c.add(Box.createVerticalStrut(5));
/*  96 */     c.add(Box.createVerticalGlue());
/*  97 */     validate();
/*     */   }
/*     */   
/*     */   public static class AppDescription {
/*     */     public static final int FRAME = 0;
/*     */     public static final int APP = 1;
/*     */     public int type;
/*     */     public String name;
/*     */     public String arg;
/*     */     public String image;
/*     */     public String classname;
/*     */     
/* 109 */     public AppDescription(int type, String name, String image, String classname, String arg) { this.type = type;
/* 110 */       this.name = name;
/* 111 */       this.image = image;
/* 112 */       this.classname = classname;
/* 113 */       this.arg = arg;
/*     */     }
/*     */     
/* 116 */     public AppDescription(String name, String image, String classname, String arg) { this(0, name, image, classname, arg); }
/*     */     
/*     */     public void launch() {
/* 119 */       if (this.type == 0) {
/*     */         try { Object o;
/* 122 */           if (this.arg == null) {
/* 123 */             o = Class.forName(this.classname).newInstance();
/*     */           } else {
/* 125 */             Constructor c = Class.forName(this.classname)
/* 126 */               .getConstructor(new Class[] { String.class });
/* 127 */             o = c.newInstance(new Object[] { this.arg });
/*     */           }
/* 129 */           if (!(o instanceof JFrame)) return;
/* 130 */           JFrame f = (JFrame)o;
/* 131 */           f.setDefaultCloseOperation(1);
/*     */         }
/*     */         catch (Exception ex) {
/* 134 */           ex.printStackTrace();
/* 135 */           JOptionPane.showMessageDialog(null, "Sorry, could not load application!", "Error", 0);
/*     */         }
/*     */       } else
/*     */         try {
/* 139 */           Runtime rt = Runtime.getRuntime();
/* 140 */           rt.exec(this.classname);
/*     */         } catch (Exception ex) {
/* 142 */           ex.printStackTrace();
/* 143 */           JOptionPane.showMessageDialog(null, "Sorry, could not load application!", "Error", 0);
/*     */         }
/*     */     }
/*     */   }
/*     */   
/*     */   public class LaunchAction extends AbstractAction {
/*     */     public LaunchAction() {}
/*     */     
/* 151 */     public void actionPerformed(ActionEvent e) { JComponent jc = (JComponent)e.getSource();
/* 152 */       PrefuseAppLauncher.AppDescription d = (PrefuseAppLauncher.AppDescription)jc.getClientProperty("app");
/* 153 */       d.launch();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/vad/work/JAVA/2018.11.30/prefuse-apps.jar!/prefuse/PrefuseAppLauncher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */