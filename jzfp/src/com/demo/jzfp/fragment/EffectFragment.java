package com.demo.jzfp.fragment;

import java.util.ArrayList;
import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.EffectAdapter;
import com.demo.jzfp.entity.Effect;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class EffectFragment extends Fragment{
	private EffectAdapter adapter,adapter1;
	private List<Effect> effects = new ArrayList<Effect>();
	private List<Effect> effects1 = new ArrayList<Effect>();
	private ListView lv_listview,lv_listview1;
	private String[] string = new String[]{"住房安全","医疗","义务教育","是否符合脱贫条件"};
	private String[] string1 = new String[]{"责任单位","负责人","联系电话","结对帮扶责任人","职务","联系电话"};
	private String[] string2 = new String[]{"炎陵镇财务局","财神爷","22034567","周密","科员","13908488899"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		for(int i=0;i<string.length;i++){
			Effect effect = new Effect();
			effect.setTitle(string[i]);
			effects.add(effect);
		}
		adapter = new EffectAdapter(getActivity(), effects);
		for(int i =0;i<string1.length;i++){
			Effect effect = new Effect();
			effect.setContent(string2[i]);
			effect.setTitle(string1[i]);
			effects1.add(effect);
		}
		adapter1 = new EffectAdapter(getActivity(), effects1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_effect, null);
		lv_listview = (ListView) view.findViewById(R.id.lv_listview);
		lv_listview1 = (ListView) view.findViewById(R.id.lv_listview1);
		lv_listview.setAdapter(adapter);
		lv_listview1.setAdapter(adapter1);
		
		return view;
	}
}
