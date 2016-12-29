package cn.sharesdk.share.demo;

import cn.sharesdk.share.demo.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class DerectShareWithoutEditActivity extends Activity implements OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_derect_share);

		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
		Platform[] platforms = ShareSDK.getPlatformList();
		for (Platform p : platforms) {
			Button btn = new Button(this);
			btn.setText(p.getName());
			btn.setTag(p);
			btn.setOnClickListener(this);
			layout.addView(btn, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		}
	}

	public void onClick(View v) {
		Object tag = v.getTag();
		if (tag != null) {
			Platform platform = (Platform) tag;
			MainActivity.showShare(this, platform.getName(), false);
		}
	}
}
