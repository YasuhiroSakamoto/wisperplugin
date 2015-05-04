package wisperpluginproject.editors;

import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;

public class WisperContentAssistant extends ContentAssistant {

	private ISourceViewer sourceViewer;
	
	public WisperContentAssistant(ISourceViewer s)
	{
		this.sourceViewer = s;
	}
	
	@Override
	public IContentAssistProcessor getContentAssistProcessor(String contentType) {
		return new WisperContentAssistProcessor();
	}

	
	
}
