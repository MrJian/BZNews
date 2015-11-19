package com.bz.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.bz.activity.R;


public class FootView extends RelativeLayout {

	private Context context;

	public FootView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		View.inflate(context, R.layout.foot_tip, this);
	}

}
