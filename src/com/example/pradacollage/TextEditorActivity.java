package com.example.pradacollage;

import com.androidquery.AQuery;

import afzkl.development.mColorPicker.views.ColorPickerView;
import afzkl.development.mColorPicker.views.ColorPickerView.OnColorChangedListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class TextEditorActivity extends Activity implements OnColorChangedListener {
	
	private AQuery aq;
	private ColorPickerView cp;
	private int currentSelectColor;
	
	public final static int TYPE_NEW = 0;
	public final static int TYPE_UPDATE = 1;
	public final static String EXTRA_EDITOR_TYPE = "type";
	public final static String EXTRA_EDITOR_TEXT = "text";
	public final static String EXTRA_EDITOR_COLOR = "color";
	//public final static String EXTRA_EDITOR_BORDER = "border"; //TODO set the border 
	//public final static String EXTRA_EDITOR_FONT = "font";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_editor);
		//TODO check the extra ... if it's empty that means new a textview
		aq = new AQuery(this);
		cp = (ColorPickerView) aq.find(R.id.colorPickerView1).getView();
		cp.setOnColorChangedListener(this);
		aq.find(R.id.btnFinish).clicked(this, "clickFinish");
		switch(getIntent().getIntExtra(EXTRA_EDITOR_TYPE, TYPE_NEW)){
		case TYPE_NEW:
			// do nothing
			break;
		case TYPE_UPDATE:
			aq.find(R.id.editText1).text(getIntent().getStringExtra(EXTRA_EDITOR_TEXT));
			cp.setColor(getIntent().getIntExtra(EXTRA_EDITOR_COLOR,Color.BLACK));
			break;
		}
	}
	
	public void clickFinish(View button){
	    Bundle bundle = new Bundle();  
	    bundle.putString(EXTRA_EDITOR_TEXT, aq.find(R.id.editText1).getEditText().getText().toString());  
	    bundle.putInt(EXTRA_EDITOR_COLOR, currentSelectColor);
	    Intent intent = new Intent();  
	    intent.putExtras(bundle);  
	    setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void onColorChanged(int color) {
		currentSelectColor = color;
	}
}