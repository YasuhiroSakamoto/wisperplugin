package wisperpluginproject.views.wisperview;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import wisperpluginproject.editors.WisperEditor;

public class WisperViewPart extends ViewPart {

	private Label lblDesc;
	
	public WisperViewPart() {
	}

	@Override
	public void createPartControl(Composite parent) {

		//表示用ラベルの作成
		lblDesc = new Label(parent, SWT.None);
		
		//エディタの取得
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

		//アクティブなエディタの取得
		IEditorPart editor = window.getActivePage().getActiveEditor();
		if(editor instanceof WisperEditor)
		{
			WisperEditor wisperEditor = (WisperEditor)editor;
			wisperEditor.getISourceViewer().getTextWidget().addCaretListener(new CaretListener() {
				@Override
				public void caretMoved(CaretEvent event) {
					lblDesc.setText("カーソル位置->" + String.valueOf(event.caretOffset));
				}
			});
		}
		else
		{
			lblDesc.setText("WisperEditorを使ってあげてください");
		}
		
	}

	@Override
	public void setFocus() {
	}

}
