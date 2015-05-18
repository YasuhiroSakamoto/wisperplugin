package wisperpluginproject.views;

import java.util.ArrayList;
import java.util.Comparator;

import org.eclipse.jface.text.IRegion;

public class WisperOutlinePageOutlineNode {

	//子ノード
	public ArrayList<WisperOutlinePageOutlineNode> childs;

	//親ノード
	public WisperOutlinePageOutlineNode parent;
	
	//対応範囲
	public IRegion region;
	
	//表示用タイトル文言
	public String label;
	
	//表示用説明文言
	public String desc;
	
	/**
	 * コンストラクタ
	 */
	public WisperOutlinePageOutlineNode() {
		childs = new ArrayList<WisperOutlinePageOutlineNode>();
		label = null;
		desc = null;
		region = null;
		parent = null;
	}

	/**
	 * 小階層すべての子を取得
	 * @return
	 */
	public ArrayList<WisperOutlinePageOutlineNode> totalChilds()
	{
		ArrayList<WisperOutlinePageOutlineNode> nodes = new ArrayList<WisperOutlinePageOutlineNode>();
		nodes.addAll(childs);
		for(int i = 0 ; i < childs.size() ; i++)
		{
			nodes.addAll(childs.get(i).totalChilds());
		}
		return nodes;
	}
	
	//階層構造での比較ルール
	public static class DepthComparator implements Comparator<WisperOutlinePageOutlineNode> {

	    public int compare(WisperOutlinePageOutlineNode a, WisperOutlinePageOutlineNode b) {
	    	
	    	//双方の階層を取得
	    	String[] aLabels = a.label.split("-");
	    	String[] bLabels = b.label.split("-");
			int aDepth = aLabels.length;
			int bDepth = bLabels.length;
			
			//階層に相違があれば、階層で比較
			if(aDepth > bDepth)
			{
				return 1;
			}
			else if(aDepth < bDepth)
			{
				return -1;
			}
			else
			{
				//階層が同じ場合は階層番号で比較
				for(int i = 0 ; i < aLabels.length ; i++)
				{
					String aLabel = aLabels[i];
					String bLabel = bLabels[i];
					int aLabelNum = Integer.parseInt(aLabel);
					int bLabelNum = Integer.parseInt(bLabel);
					
					if(aLabelNum > bLabelNum)
					{
						return 1;
					}
					else if(aLabelNum < bLabelNum)
					{
						return -1;
					}
					else
					{
						//階層番号が同じ場合は次の階層番号へ
					}
				}
			}
			
			//ここまで同じ場合は同一とみなす
			return 0;

	    }
	}
}
