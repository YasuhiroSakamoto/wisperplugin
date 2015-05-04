package wisperpluginproject.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class WisperEditor extends TextEditor {

	private ColorManager colorManager;

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
	
}
