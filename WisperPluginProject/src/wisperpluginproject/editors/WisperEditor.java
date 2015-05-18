package wisperpluginproject.editors;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import wisperpluginproject.views.WisperOutlinePage;

public class WisperEditor extends AbstractTextEditor {

	private ColorManager colorManager;
	private WisperOutlinePage outlinePage = null;
	
	public WisperEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		
		//アウトラインビューからの要求？
		if (adapter.equals(IContentOutlinePage.class)) {
			if(outlinePage == null)
			{
				outlinePage = new WisperOutlinePage(this);
			}
			return outlinePage;
		}
		
		return super.getAdapter(adapter);
	}
	
	public ISourceViewer getISourceViewer()
	{
		return getSourceViewer();
	}
	
}
