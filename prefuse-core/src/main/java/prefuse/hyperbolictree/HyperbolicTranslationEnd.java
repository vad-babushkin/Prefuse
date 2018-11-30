/*    */ package prefuse.hyperbolictree;
/*    */ 
/*    */ import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

import javax.swing.*;
import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HyperbolicTranslationEnd
/*    */   extends AbstractAction
/*    */ {
/*    */   public void run(ItemRegistry registry, double frac)
/*    */   {
/* 20 */     Iterator nodeIter = registry.getNodeItems(false);
/* 21 */     while (nodeIter.hasNext()) {
/* 22 */       NodeItem n = (NodeItem)nodeIter.next();
/* 23 */       HyperbolicParams np = getParams(n);
/* 24 */       if (np != null) {
/* 25 */         np.zo[0] = np.z[0];
/* 26 */         np.zo[1] = np.z[1];
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private HyperbolicParams getParams(VisualItem n) {
/* 32 */     return (HyperbolicParams)n.getVizAttribute("hyperbolicParams");
/*    */   }
/*    */ }


/* Location:              /home/vad/work/JAVA/2018.11.30/prefuse-apps.jar!/prefuse/hyperbolictree/HyperbolicTranslationEnd.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */