package com.demo.jzfp.fragment;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.TdataResult;
import com.demo.jzfp.utils.Tools;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EffectFragmentEdit extends Fragment {
	private EditText ed_person_money, ed_all_money, ed_date,ed_safety,ed_medical,ed_education,ed_poverty;
	private TdataResult tResult;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_effect_edit, null);
		ed_person_money = (EditText) view.findViewById(R.id.tv_person_money);
		ed_all_money = (EditText) view.findViewById(R.id.tv_all_money);
		ed_date = (EditText) view.findViewById(R.id.tv_date);
		ed_safety = (EditText) view.findViewById(R.id.ed_safety);
		ed_medical = (EditText) view.findViewById(R.id.ed_medical);
		ed_education = (EditText) view.findViewById(R.id.ed_education);
		ed_poverty = (EditText) view.findViewById(R.id.ed_poverty);
		ed_date.setFocusable(true);
		ed_date.setSelection(ed_date.getText().toString().length());
		return view;
	}

	public void setTdataResult(TdataResult tResult) {
		this.tResult = tResult;
		initData();
	}

	private void initData() {
		if(tResult==null) return;
		ed_date.setText(Tools.parseEmpty(tResult.getTpDate()) + "");
		ed_person_money.setText(Tools.parseEmpty(tResult.getJtsrRjsr()) + "");
		ed_all_money.setText(Tools.parseEmpty(tResult.getJtsrZsr()) + "");
		ed_safety.setText(Tools.parseEmpty(tResult.getAddressSafe()) + "");
		ed_medical.setText(Tools.parseEmpty(tResult.getJbshbzYl()) + "");
		ed_education.setText(Tools.parseEmpty(tResult.getJbshbzYwjy()) + "");
		ed_poverty.setText(Tools.parseEmpty(tResult.getJffhtptj()) + "");
		
		ed_date.setSelection(ed_date.getText().toString().length());
	}
	
	private TdataResult getResult(){
		if(tResult==null){
			tResult = new TdataResult();
		}
		tResult.setJtsrRjsr(ed_person_money.getText().toString().trim());
		tResult.setJtsrZsr(ed_all_money.getText().toString().trim());
		tResult.setAddressSafe(ed_safety.getText().toString().trim());
		tResult.setTpDate(ed_date.getText().toString().trim());
		tResult.setJbshbzYl(ed_medical.getText().toString().trim());
		tResult.setJbshbzYwjy(ed_education.getText().toString().trim());
		tResult.setJffhtptj(ed_poverty.getText().toString().trim());
		return tResult;
	}
}
