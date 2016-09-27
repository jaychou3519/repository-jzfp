package com.demo.jzfp.fragment;

import java.util.Arrays;
import java.util.Calendar;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.TdataResult;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.view.DoubleDatePickerDialog;
import com.demo.jzfp.view.WheelView;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class EffectFragmentEdit extends Fragment implements OnClickListener {
	private EditText ed_person_money, ed_all_money, ed_safety, ed_medical, ed_education;
	private TdataResult tResult;
	private TextView tv_date, tv_poverty;
	private AlertDialog dialog;
	private String[] conditions = new String[] { "是", "否" };
	private String condition;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_effect_edit, null);
		ed_person_money = (EditText) view.findViewById(R.id.tv_person_money);
		ed_all_money = (EditText) view.findViewById(R.id.tv_all_money);
		tv_date = (TextView) view.findViewById(R.id.tv_date);
		ed_safety = (EditText) view.findViewById(R.id.ed_safety);
		ed_medical = (EditText) view.findViewById(R.id.ed_medical);
		ed_education = (EditText) view.findViewById(R.id.ed_education);
		tv_poverty = (TextView) view.findViewById(R.id.tv_poverty);

		return view;
	}

	public void setTdataResult(TdataResult tResult) {
		this.tResult = tResult;
		initData();
	}

	private void initData() {
		if (tResult == null)
			return;
		tv_date.setText(Tools.parseEmpty(tResult.getTpDate()) + "");
		ed_person_money.setText(Tools.parseEmpty(tResult.getJtsrRjsr()) + "");
		ed_all_money.setText(Tools.parseEmpty(tResult.getJtsrZsr()) + "");
		ed_safety.setText(Tools.parseEmpty(tResult.getAddressSafe()) + "");
		ed_medical.setText(Tools.parseEmpty(tResult.getJbshbzYl()) + "");
		ed_education.setText(Tools.parseEmpty(tResult.getJbshbzYwjy()) + "");
		String poverty = "是";
		if(tResult.getJffhtptj().equals("1")){
			poverty = "是";
		}
		if(tResult.getJffhtptj().equals("2")){
			poverty = "否";
		}
		tv_poverty.setText(Tools.parseEmpty(poverty) + "");
	}

	public TdataResult getTdataResult() {
		if (tResult == null) {
			tResult = new TdataResult();
		}
		tResult.setTpDate(tv_date.getText().toString().trim());
		tResult.setJtsrRjsr(ed_person_money.getText().toString().trim());
		tResult.setJtsrZsr(ed_all_money.getText().toString().trim());
		tResult.setAddressSafe(ed_safety.getText().toString().trim());
		tResult.setJbshbzYl(ed_medical.getText().toString().trim());
		tResult.setJbshbzYwjy(ed_education.getText().toString().trim());
		String poverty = "1";
		if(tv_poverty.getText().toString().trim().equals("是")){
			poverty = "1";
		}
		if(tv_poverty.getText().toString().trim().equals("否")){
			poverty = "2";
		}
		tResult.setJffhtptj(poverty);
		return tResult;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_date:
			Calendar c = Calendar.getInstance();
			// 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
			new DoubleDatePickerDialog(getActivity(), 0, new DoubleDatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
						int startDayOfMonth) {
					String textString = startYear + "-" + (startMonthOfYear + 1) + "-"+startMonthOfYear;
					tv_date.setText(textString);
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
			break;
		case R.id.tv_poverty:
			showWheelView("是否符合脱贫条件", conditions, 0);
			break;
		case R.id.txt_sure:
			if (TextUtils.isEmpty(condition))
				condition = conditions[1];
			tv_poverty.setText(condition);
			condition = "";
			dialog.dismiss();
			break;
		case R.id.txt_cancel:
			dialog.dismiss();
			break;
		default:
			break;
		}
	}
	

	/**
	 * 选择弹出窗
	 * 
	 * @param title
	 *            弹出窗标题
	 * @param strs
	 *            弹出窗选择内容
	 * @param table
	 *            弹出窗标记
	 */
	private void showWheelView(String title, String[] strs, final int table) {
		View outerView = LayoutInflater.from(getActivity()).inflate(R.layout.wheel_view, null);
		WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
		wv.setOffset(1);
		wv.setItems(Arrays.asList(strs));
		wv.setSeletion(1);
		wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				condition = item;
			}
		});
		dialog = new AlertDialog.Builder(getActivity()).setTitle(title).setView(outerView).show();

		TextView txtSure = (TextView) outerView.findViewById(R.id.txt_sure);
		TextView txtCancle = (TextView) outerView.findViewById(R.id.txt_cancel);
		txtSure.setOnClickListener(this);
		txtCancle.setOnClickListener(this);
	}

}
