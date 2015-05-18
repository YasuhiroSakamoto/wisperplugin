package wisperpluginproject;

import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "WisperPluginProject"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private static HashMap<String, Image> imageCash;
	
	/**
	 * The constructor
	 */
	public Activator() {
		imageCash = new HashMap<String, Image>();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	public static Image getImage(String path)
	{
		ImageDescriptor desc;
		Image image;
		
		image = imageCash.get(path);
		if(image == null)
		{
			desc = imageDescriptorFromPlugin(PLUGIN_ID, path);
			image = desc.createImage();
			imageCash.put(path, image);
		}
		return image;
		
	}

}
