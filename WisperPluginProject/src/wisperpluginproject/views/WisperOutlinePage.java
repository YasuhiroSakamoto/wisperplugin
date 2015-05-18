package wisperpluginproject.views;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.IViewportListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import wisperpluginproject.editors.WisperEditor;

public class WisperOutlinePage extends ContentOutlinePage {

	//エディタの参照
	private WisperEditor editor;

	//ツリーノードの根元
	private WisperOutlinePageOutlineNode rootNode;
	
	/**
	 * コンストラクタ
	 * @param editor エディタ
	 */
	public WisperOutlinePage(WisperEditor editor) {
		this.editor = editor;
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	/**
	 * コントロール作成時
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		//ツリービューを取得
		TreeViewer treeViewer = getTreeViewer();
		
		//コンテンツプロバイダの設定
		treeViewer.setContentProvider(new WisperOutlinePageContentProvider());
		
		//ラベルプロバイダの設定
		treeViewer.setLabelProvider(new WisperOutlinePageLabelProvider());
		
		//ツリーの選択変更ハンドラ
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				TreeSelection selection = (TreeSelection)event.getSelection();
				WisperOutlinePageOutlineNode node  = (WisperOutlinePageOutlineNode)selection.getFirstElement();
				if(node != null)
				{
					editor.getISourceViewer().setSelectedRange(node.region.getOffset(), node.region.getLength());
				}
			}
		});
		
		//初期状態としての構文解析
		rootNode = WisperParser.parseDocuments(editor.getDocumentProvider().getDocument(editor.getEditorInput()));
		
		//データ要素の根元を設定
		treeViewer.setInput(rootNode);

		//テキスト変更リスナーの設定
		editor.getDocumentProvider().getDocument(editor.getEditorInput()).addDocumentListener(new IDocumentListener() {
			@Override
			public void documentChanged(DocumentEvent event) {
				//再度パース
				//初期状態としての構文解析
				rootNode = WisperParser.parseDocuments(editor.getDocumentProvider().getDocument(editor.getEditorInput()));
				
				//データ要素の根元を設定
				getTreeViewer().setInput(rootNode);
			}
			
			@Override
			public void documentAboutToBeChanged(DocumentEvent event) {
			}
		});
		
		//テキストの選択変更リスナ
//		editor.getISourceViewer().getTextWidget().addCaretListener(new CaretListener() {
//			@Override
//			public void caretMoved(CaretEvent event) {
//				
//				//カーソル位置に対応するnodeを取得
//				WisperOutlinePageOutlineNode caretNode = WisperParser.getCaretNode(event.caretOffset, rootNode);
//				
//				if(caretNode != null)
//				{
//					StructuredSelection selection = new StructuredSelection(caretNode);
//					getTreeViewer().setSelection(selection,true);
//				}
//				
//			}
//		});
	
	}
	
}
