package wisperpluginproject.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

public class WisperParser {

	/**
	 * 文章解析
	 * @param document
	 * @return
	 */
	public static WisperOutlinePageOutlineNode parseDocuments(IDocument document)
	{
		
		WisperOutlinePageOutlineNode root;
		ArrayList<WisperOutlinePageOutlineNode> nodes;
		
		root = new WisperOutlinePageOutlineNode();
		nodes = new ArrayList<WisperOutlinePageOutlineNode>();
		
		//1行ごとのループ(一旦全てフラットで集計する)
		for(int i = 0 ; i < document.getNumberOfLines() ; i++)
		{
			
			try
			{

				//１行の範囲を取得
				IRegion region = document.getLineInformation(i);
				
				//１行の文言を取得
				String lineString = document.get(region.getOffset(), region.getLength());
				
				//最初のスペースまでの文言を取得(そもそも存在しない場合はノードとして)
				int period = lineString.indexOf(" ");
				if(period > -1)
				{
					String labelString = lineString.substring(0, period);
					
					//取得した文言が-と数字で構成されているか？
					Pattern pattern = Pattern.compile("^[0-9¥-]*$"); 
			        Matcher match = pattern.matcher(labelString);
			        if(match.find())
			        {
			        	WisperOutlinePageOutlineNode node;
			        	node = new WisperOutlinePageOutlineNode();
			        	node.label = labelString;
			        	node.desc = lineString.substring(period);
			        	node.region = region;
			        	nodes.add(node);
			        }
					
				}
				
			}
			catch(BadLocationException ble)
			{
				ble.printStackTrace();
			}
			
		}
		
		//フラットな状態から階層構造を構築(階層の深さごと整列)
		Collections.sort(nodes, new WisperOutlinePageOutlineNode.DepthComparator());
		
		//階層に整理
		for(int i = 0 ; i < nodes.size() ; i++)
		{
			createDepthNode(nodes.get(i),root,0);
		}
		
		return root;
		
	}
	
	private static void createDepthNode(WisperOutlinePageOutlineNode node,WisperOutlinePageOutlineNode parent,int depth)
	{
		
		String[] depthLabels;
		String[] parentLabels;
		
		//追加対象の階層を取得
		depthLabels = node.label.split("-");
		
		if(parent.label == null)
		{
			parentLabels = new String[0];
		}
		else
		{
			parentLabels = parent.label.split("-");
		}
		
		//お互いの階層を比較
		if((depthLabels.length - 1) == parentLabels.length)
		{
			parent.childs.add(node);
			node.parent = parent;
			return;
		}
		else
		{
			//階層番号に合うノードを検索
			for(int i = 0 ; i < parent.childs.size() ; i++)
			{
				WisperOutlinePageOutlineNode child = parent.childs.get(i);
				if(child.label.split("-")[depth].equals(depthLabels[depth]))
				{
					createDepthNode(node, child, depth+1);
					return;
				}
			}
			
		}
		
	}
	
	/**
	 * カーソル位置に対応するNodeを返却する
	 * @param root
	 * @return
	 */
	public static WisperOutlinePageOutlineNode getCaretNode(int caretOffset,WisperOutlinePageOutlineNode root)
	{
		ArrayList<WisperOutlinePageOutlineNode> nodes;
		nodes = root.totalChilds();
		for(int i = 0 ; i < nodes.size() ; i++)
		{
			WisperOutlinePageOutlineNode node = nodes.get(i);
			if((node.region.getOffset() <= caretOffset) && (caretOffset <= (node.region.getOffset() + node.region.getLength())))
			{
				return node;
			}
		}
		return null;
	}
	
}
