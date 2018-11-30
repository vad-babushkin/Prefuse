package prefuse.demos.applets;

import prefuse.util.ui.JPrefuseApplet;


public class RadialGraphView extends JPrefuseApplet {

	public void init() {
		this.setContentPane(
				prefuse.demos.RadialGraphView.demo("data/socialnet.xml", "name"));
	}

} // end of class RadialGraphView
