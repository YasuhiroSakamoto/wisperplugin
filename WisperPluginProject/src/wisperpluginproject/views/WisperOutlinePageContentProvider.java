package wisperpluginproject.views;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class WisperOutlinePageContentProvider implements ITreeContentProvider {
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		WisperOutlinePageOutlineNode node = (WisperOutlinePageOutlineNode)parentElement;
		return node.childs.toArray();
	}

	@Override
	public boolean hasChildren(Object element) {
		WisperOutlinePageOutlineNode node = (WisperOutlinePageOutlineNode)element;
		return node.childs.size() > 0;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		WisperOutlinePageOutlineNode node = (WisperOutlinePageOutlineNode)inputElement;
		return node.childs.toArray();
	}

	@Override
	public Object getParent(Object element) {
		WisperOutlinePageOutlineNode node = (WisperOutlinePageOutlineNode)element;
		return node.parent;
	}
	
}
