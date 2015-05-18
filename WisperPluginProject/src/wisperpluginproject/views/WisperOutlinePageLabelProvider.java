package wisperpluginproject.views;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import wisperpluginproject.Activator;

public class WisperOutlinePageLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getImage(Object element) {
		
		WisperOutlinePageOutlineNode node = (WisperOutlinePageOutlineNode)element;
		
		//階層を調べる
		int depth = node.label.split("-").length;
		
		if(depth <= 5)
		{
			return Activator.getImage("/icons/" + String.valueOf(depth) + ".png");
		}
		else
		{
			//6階層以降は用意していない
			return null;
		}
	}

	@Override
	public String getText(Object element) {
		WisperOutlinePageOutlineNode node = (WisperOutlinePageOutlineNode)element;
		return node.label + "@" + node.desc;
	}

}
