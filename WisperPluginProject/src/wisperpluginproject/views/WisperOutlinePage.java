package wisperpluginproject.views;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import wisperpluginproject.editors.WisperEditor;

public class WisperOutlinePage extends ContentOutlinePage implements ISelectionChangedListener {

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
		treeViewer.addSelectionChangedListener(this);
		
		//初期状態としての構文解析
		rootNode = WisperParser.parseDocuments(editor.getDocumentProvider().getDocument(editor.getEditorInput()));
		
		//データ要素の根元を設定
		treeViewer.setInput(rootNode);
		
	}
	
	/**
	 * ツリーの選択変更ハンドラ
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		
	}

	
}
